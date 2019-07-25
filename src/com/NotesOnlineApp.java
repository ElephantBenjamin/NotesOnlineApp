package com;

import com.models.Note;
import com.services.NoteService;
import com.vaadin.icons.VaadinIcons;
import com.vaadin.server.VaadinRequest;
import com.vaadin.shared.ui.ContentMode;
import com.vaadin.ui.*;

public class NotesOnlineApp extends UI {

    private void initSearchPanel(Layout mainLayout) {
        Panel searchPanel = new Panel();
        searchPanel.setStyleName("borderless");

        TextField searchField = new TextField();
        searchField.setPlaceholder("Search");

        Button searchButton = new Button();
        searchButton.setIcon(VaadinIcons.SEARCH);

        Button addNoteButton = new Button("New note");
        addNoteButton.setIcon(VaadinIcons.PLUS);

        HorizontalLayout searchLayout = new HorizontalLayout();
        searchLayout.addComponent(searchField);
        searchLayout.addComponent(searchButton);
        searchLayout.addComponent(addNoteButton);
        searchPanel.setContent(searchLayout);
        mainLayout.addComponent(searchPanel);
    }

    @Override
    public void init(VaadinRequest request) {
        VerticalLayout layout = new VerticalLayout();
        setContent(layout);

        initSearchPanel(layout);

        NoteService noteService = new NoteService();
        for (Note note : noteService.findAllNotes()) {
            HorizontalLayout noteLayout = new HorizontalLayout();
            Panel notePanel = new Panel();
            VerticalLayout notePanelLayout = new VerticalLayout();
            Label titleLabel = new Label("<b>Untitled</b>");
            if (note.getTitle() != null)
                titleLabel.setValue("<b>" + note.getTitle() + "</b>");
            titleLabel.setContentMode(ContentMode.HTML);
            Label textLabel = new Label(note.getText());
            notePanelLayout.addComponent(titleLabel);
            notePanelLayout.addComponent(textLabel);
            notePanel.setContent(notePanelLayout);
            noteLayout.addComponent(notePanel);

            Button deleteNoteButton = new Button();
            deleteNoteButton.setIcon(VaadinIcons.TRASH);
            noteLayout.addComponent(deleteNoteButton);
            layout.addComponent(noteLayout);
        }

    }
}
