package com.dao;

import com.models.Note;

import java.util.List;

/**
 * Интерфейс, описывающий слой доступа к данным
 * @author Stanislav Podgornov
 * @version 1.0
 */
public interface INoteDao {

    /**
     * Метод получения объекта по идентификатору
     * @param id идентификатор объекта
     * @return объект или <code>null</code>, если получить объект не удается
     */
    Note findById(int id);

    /**
     * Метод сохранения объекта
     * @param note сохраняемый объект
     */
    void save(Note note);

    /**
     * Метод обновления существующего объекта
     * @param note обновляемый объект
     */
    void update(Note note);

    /**
     * Метод удаления существующего объекта
     * @param note удаляемый объект
     */
    void delete(Note note);

    /**
     * Метод получения всех объектов
     * @return список, содержащий все объекты
     */
    List<Note> findAll();
}
