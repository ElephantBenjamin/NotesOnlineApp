package com.dao;

import com.models.Note;

import java.util.List;

public interface NoteDao {

    Note findById(int id);

    void save(Note note);

    void update(Note note);

    void delete(Note note);

    List<Note> findAll();
}
