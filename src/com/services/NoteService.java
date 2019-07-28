package com.services;

import com.dao.NoteDaoImpl;
import com.models.Note;

import java.util.List;
import java.util.ArrayList;

/**
 * Класс, реализующий слой бизнес-логики приложения
 * @author Stanislav Podgornov
 * @version 1.0
 */
public class NoteService {
    private NoteDaoImpl noteDao = new NoteDaoImpl();

    public NoteService() {}

    /**
     * Метод поиска заметки по ее идентификатору
     * @param id идентификатор заметки
     * @return объект заметки {@link Note}, либо <code>null</code>, если заметка с таким идентификатором не найдена
     */
    public Note findNote(int id) {
        return noteDao.findById(id);
    }

    /**
     * Метод сохранения заметки
     * @param note сохраняемая заметка
     */
    public void saveNote(Note note) {
        noteDao.save(note);
    }

    /**
     * Метод удаления заметки
     * @param note удаляемая заметка
     */
    public void deleteNote(Note note) {
        noteDao.delete(note);
    }

    /**
     * Метод обновления заметки
     * @param note обновляемая заметка
     */
    public void updateNote(Note note) {
        noteDao.update(note);
    }

    /**
     * Метод получения списка всех заметок
     * @return список всех заметок
     */
    public List<Note> findAllNotes() {
        return noteDao.findAll();
    }

    /**
     * Метод получения списка всех заметок, заголовок или текст которых содержит указанную подстроку
     * Регистр символов при поиске не учитывается
     * @param substring искомая подстрока
     * @return список заметок, удовлетворяющих условию
     */
    public List<Note> findNotes(String substring) {
        List<Note> allNotes = noteDao.findAll();
        ArrayList<Note> selectedNotes = new ArrayList<>();

        String prepSubstring = substring.toLowerCase();

        for (Note note : allNotes) {
            if (note.getText().toLowerCase().contains(prepSubstring) ||
                    (note.getTitle() != null && note.getTitle().toLowerCase().contains(prepSubstring))) {
                selectedNotes.add(note);
            }
        }

        return selectedNotes;
    }
}
