package com;

/**
 * Класс, содержащий константы для проекта
 * @author Stanislav Podgornov
 * @version 1.0
 */
public final class Constants {
    private Constants() {}

    public static final String EMPTY_STRING = "";
    public static final String OPEN_B_TAG = "<b>";
    public static final String CLOSE_B_TAG = "</b>";

    /**
     * Класс, содержащий коснтанты для класса com.NotesOnlineApp
     */
    public final class NotesOnlineApp {
        private NotesOnlineApp() {}

        public static final String NEW_NOTE_WINDOW_NAME = "CreateNewNote";
        public static final String TITLE_LABEL = "Title:";
        public static final String TITLE_TEXT_FIELD_PLACEHOLDER = "Untitled";
        public static final String TITLE_TEXT_LABEL = "Untitled";
        public static final String TITLE_NOTE_TEXT = "Note text:";
        public static final String SEARCH_FIELD_PLACEHOLDER = "Search";
        public static final String SAVE_NOTE_BUTTON_NAME = "Save this note";
        public static final String ADD_NOTE_BUTTON_NAME = "New note";
        public static final String DELETE_NOTE_BUTTON_NAME = "Delete note";

        public static final String ERROR_EMPTY_NOTE_TEXT = "Enter note text!";
        public static final String ERROR_WITH_SAVE_NOTE = "Can't save note, some error appears!";
        public static final String ERROR_WITH_GET_NOTES = "Can't get notes, some error appears!";
        public static final String ERROR_WITH_DELETE_NOTE = "Can't delete note, some error appears!";

        public static final String NOTIFICATION_SUCCESSFULLY_SAVE = "Note successfully save!";
        public static final String NOTIFICATION_SUCCESSFULLY_DELETE = "Delete successful!";
    }

}
