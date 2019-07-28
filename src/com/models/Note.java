package com.models;

import javax.persistence.*;


/**
 * Класс реализует модель данных заметок
 * @author Stanislav Podgornov
 * @version 1.0
 */
@Entity
@Table (name = "notes")
public class Note {

    /** Уникальный идентификатор заметки */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    /** Заголовок заметки */
    @Column(name = "title")
    private String title;

    /** Текст заметки */
    @Column(name = "text")
    private String text;

    public Note() {
    }

    /**
     * Создает заметку
     * @param title заголовок заметки
     * @param text текст заметки
     */
    public Note(String title, String text) {
        this.title = title;
        this.text = text;
    }

    /**
     * Метод получения идентификатора заметки
     * @return идентификатор заметки
     */
    public int getId() {
        return id;
    }

    /**
     * Метод получения заголовка заметки
     * @return заголовок заметки
     */
    public String getTitle() {
        return title;
    }

    /**
     * Метод изменения заголовка заметки
     * @param title новый заголовок заметки
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Метод получения текста заметки
     * @return текст заметки
     */
    public String getText() {
        return text;
    }

    /**
     * Функция изменения текста заметки
     * @param text новый текст заметки
     */
    public void setText(String text) {
        this.text = text;
    }

}
