package com.services;

import com.dao.NoteDaoImpl;
import com.models.Note;

import java.util.List;

public class NoteService {
    private NoteDaoImpl noteDao = new NoteDaoImpl();

    public NoteService() {}

    public Note findNote(int id) {
        return noteDao.findById(id);
    }

    public void saveNote(Note note) {
        noteDao.save(note);
    }

    public void deleteNote(Note note) {
        noteDao.delete(note);
    }

    public void updateNote(Note note) {
        noteDao.update(note);
    }

    public List<Note> findAllNotes() {
        return noteDao.findAll();
    }
}
