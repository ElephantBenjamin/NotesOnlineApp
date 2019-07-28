package com;

import com.models.Note;
import com.services.NoteService;
import com.vaadin.icons.VaadinIcons;
import com.vaadin.server.VaadinRequest;
import com.vaadin.shared.ui.ContentMode;
import com.vaadin.ui.*;

import java.util.List;

/**
 * Класс, реализующий интерфейс приложения
 * @author Stanislav Podgornov
 * @version 1.0
 * @see UI
 */
public class NotesOnlineApp extends UI {

    /**
     * Метод отображения окна создания новой заметки
     * Описывает интерфейс создаваемого окна и выводит окно на экран
     */
    private void showNewNoteWindow() {
        Window newNoteWindow = new Window(Constants.NotesOnlineApp.NEW_NOTE_WINDOW_NAME);
        VerticalLayout windowContent = new VerticalLayout();
        newNoteWindow.setContent(windowContent);
        newNoteWindow.setModal(true);
        newNoteWindow.setClosable(true);
        newNoteWindow.setDraggable(true);
        newNoteWindow.setResizable(true);

        windowContent.addComponent(new Label(Constants.NotesOnlineApp.TITLE_LABEL));

        // Поле для ввода заголовка
        TextField titleTextField = new TextField();
        titleTextField.setPlaceholder(Constants.NotesOnlineApp.TITLE_TEXT_FIELD_PLACEHOLDER);
        titleTextField.setMaxLength(256);
        windowContent.addComponent(titleTextField);

        windowContent.addComponent(new Label(Constants.NotesOnlineApp.TITLE_NOTE_TEXT));

        // Поле для ввода текста заметки
        TextArea noteTextArea = new TextArea();
        noteTextArea.setRows(10);
        noteTextArea.setSizeFull();
        windowContent.addComponent(noteTextArea);

        // Кнопка сохранения заметки
        // При нажатии заметка сохраняется в базу данных, окно закрывается и обновляется поисковая выдача
        Button saveNoteButton = new Button(Constants.NotesOnlineApp.SAVE_NOTE_BUTTON_NAME);
        saveNoteButton.addClickListener(event -> {
            if (noteTextArea.getValue().equals(Constants.EMPTY_STRING)) {
                Notification.show(Constants.NotesOnlineApp.ERROR_EMPTY_NOTE_TEXT,
                        Notification.Type.ERROR_MESSAGE);
                return;
            }

            /* Если заголовок не указан, то заметка сохраняется со значением null в заголовке */
            String title = titleTextField.getValue();
            if (title.equals(Constants.EMPTY_STRING))
                title = null;
            Note note = new Note(title, noteTextArea.getValue());

            try {
                NoteService noteService = new NoteService();
                noteService.saveNote(note);
            } catch (Exception e) {
                Notification.show(Constants.NotesOnlineApp.ERROR_WITH_SAVE_NOTE,
                        Notification.Type.ERROR_MESSAGE);
                return;
            }

            Notification.show(Constants.NotesOnlineApp.NOTIFICATION_SUCCESSFULLY_SAVE);
            newNoteWindow.close();
            updateSearchResults(null);
        });
        windowContent.addComponent(saveNoteButton);

        addWindow(newNoteWindow);
    }

    /**
     * Метод инициализации панели поиска (панель в верхней части страницы, содержащая строку поиска, кнопку поиска и
     * кнопку создания новой заметки)
     * @param mainLayout макет (Layout), в который будет помещена панель поиска
     * @see Layout
     */
    private void initSearchPanel(Layout mainLayout) {
        Panel searchPanel = new Panel();
        searchPanel.setStyleName("borderless");

        HorizontalLayout searchLayout = new HorizontalLayout();
        searchPanel.setContent(searchLayout);
        mainLayout.addComponent(searchPanel);

        // Строка поиска
        TextField searchField = new TextField();
        searchField.setPlaceholder(Constants.NotesOnlineApp.SEARCH_FIELD_PLACEHOLDER);
        searchLayout.addComponent(searchField);

        // Кнопка поиска заметок
        // При нажатии обновляется поисковая выдача
        Button searchButton = new Button();
        searchButton.setIcon(VaadinIcons.SEARCH);
        searchButton.addClickListener(event -> updateSearchResults(searchField.getValue()));
        searchLayout.addComponent(searchButton);

        // Кнопка добавления новой заметки
        // При нажатии появляется модальное окно добавления новой заметки
        Button addNoteButton = new Button(Constants.NotesOnlineApp.ADD_NOTE_BUTTON_NAME);
        addNoteButton.setIcon(VaadinIcons.PLUS);
        addNoteButton.addClickListener(event -> showNewNoteWindow());
        searchLayout.addComponent(addNoteButton);
    }

    /**
     * Метод для вывода результатов поиска заметок
     * @param mainLayout макет (Layout), в который будут помещены результаты поиска
     * @param substring подстрока, по которой осуществляется поиск (<code>null</code> для вывода всех заметок)
     */
    private void buildSearchResultsPage(Layout mainLayout, String substring) {
        VerticalLayout searchResultsLayout = new VerticalLayout();
        mainLayout.addComponent(searchResultsLayout);

        // Поиск заметок в базе данных
        NoteService noteService = new NoteService();
        List<Note> notes;
        if (substring != null) {
            try {
                notes = noteService.findNotes(substring);
            } catch (Exception e) {
                Notification.show(Constants.NotesOnlineApp.ERROR_WITH_GET_NOTES,
                        Notification.Type.ERROR_MESSAGE);
                return;
            }
        } else {
            try {
                notes = noteService.findAllNotes();
            } catch (Exception e) {
                Notification.show(Constants.NotesOnlineApp.ERROR_WITH_GET_NOTES,
                        Notification.Type.ERROR_MESSAGE);
                return;
            }
        }

        // Форматирование и отображение результатов поиска
        for (Note note : notes) {
            Panel notePanel = new Panel();
            notePanel.setStyleName("borderless");
            searchResultsLayout.addComponent(notePanel);

            VerticalLayout notePanelLayout = new VerticalLayout();
            notePanel.setContent(notePanelLayout);

            // Заголовок заметки (выделяется жирным) и текст заметки
            Label titleLabel = new Label(Constants.OPEN_B_TAG +
                    Constants.NotesOnlineApp.TITLE_TEXT_LABEL + Constants.CLOSE_B_TAG);
            if (note.getTitle() != null)
                titleLabel.setValue(Constants.OPEN_B_TAG + note.getTitle() + Constants.CLOSE_B_TAG);
            titleLabel.setContentMode(ContentMode.HTML);
            Label textLabel = new Label(note.getText());
            notePanelLayout.addComponent(titleLabel);
            notePanelLayout.addComponent(textLabel);

            // Кнопка удаления заметки
            // При нажатии заметка удаляется из базы данных, а результаты поиска обновляются
            Button deleteNoteButton = new Button(Constants.NotesOnlineApp.DELETE_NOTE_BUTTON_NAME);
            deleteNoteButton.setIcon(VaadinIcons.TRASH);
            deleteNoteButton.addClickListener(event -> {
                try {
                    noteService.deleteNote(note);
                } catch (Exception e) {
                    Notification.show(Constants.NotesOnlineApp.ERROR_WITH_DELETE_NOTE,
                            Notification.Type.ERROR_MESSAGE);
                    return;
                }
                Notification.show(Constants.NotesOnlineApp.NOTIFICATION_SUCCESSFULLY_DELETE);
                updateSearchResults(null);
            });
            notePanelLayout.addComponent(deleteNoteButton);
        }
    }

    /**
     * Метод обновления результатов поиска
     * @param substring подстрока, по которой осуществляется поиск
     */
    private void updateSearchResults(String substring) {
        VerticalLayout mainLayout = (VerticalLayout)getContent();
        mainLayout.removeComponent(mainLayout.getComponent(1));

        buildSearchResultsPage(mainLayout, substring);
    }

    /**
     * Метод инициализации пользовательского интерфейса
     * Инициализирует весь необходимый пользователю интерфейс на момент открытия страницы
     * Вызывается автоматически при запуске приложения
     * @param request запрос
     * @see UI#init(VaadinRequest)
     */
    @Override
    public void init(VaadinRequest request) {
        VerticalLayout layout = new VerticalLayout();
        setContent(layout);

        initSearchPanel(layout);

        buildSearchResultsPage(layout, null);
    }
}
