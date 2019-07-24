package com.dao;

import com.models.Note;
import com.util.HibernateSessionFactoryUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class NoteDaoImpl implements NoteDao {
    @Override
    public Note findById(int id) {
        return HibernateSessionFactoryUtil.getSessionFactory().openSession().get(Note.class, id);
    }

    @Override
    public void save(Note note) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.save(note);
        transaction.commit();
        session.close();
    }

    @Override
    public void update(Note note) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.update(note);
        transaction.commit();
        session.close();
    }

    @Override
    public void delete(Note note) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.delete(note);
        transaction.commit();
        session.close();
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Note> findAll() {
        return (List<Note>) HibernateSessionFactoryUtil.getSessionFactory().openSession().createQuery("From com.models.Note").list();
    }
}
