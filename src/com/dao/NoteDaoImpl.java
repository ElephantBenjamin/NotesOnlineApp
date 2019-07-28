package com.dao;

import com.models.Note;
import com.util.HibernateSessionFactoryUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

/**
 * Класс, реализующий слой доступа к данным
 * @author Stanislav Podgornov
 * @version 1.0
 * @see com.dao.INoteDao
 */
public class NoteDaoImpl implements INoteDao {
    /**
     * Метод получения объекта из базы данных по его идентификатору
     * @param id идентификатор объекта
     * @return объект, либо <code>null</code>, если не удается найти объект с таким идентификатором
     */
    @Override
    public Note findById(int id) {
        return HibernateSessionFactoryUtil.getSessionFactory().openSession().get(Note.class, id);
    }

    /**
     * Метод сохранения объекта в базу данных
     * @param note сохраняемый объект
     */
    @Override
    public void save(Note note) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.save(note);
        transaction.commit();
        session.close();
    }

    /**
     * Метод обновления существующего объекта в базе данных
     * @param note обновляемый объект
     */
    @Override
    public void update(Note note) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.update(note);
        transaction.commit();
        session.close();
    }

    /**
     * Метод удаления существующего объекта из базы данных
     * @param note удаляемый объект
     */
    @Override
    public void delete(Note note) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.delete(note);
        transaction.commit();
        session.close();
    }

    /**
     * Метод получения всех объектов из базы данных
     * @return список объектов
     */
    @Override
    @SuppressWarnings("unchecked")
    public List<Note> findAll() {
        return (List<Note>) HibernateSessionFactoryUtil.getSessionFactory().openSession().createQuery("From com.models.Note").list();
    }
}
