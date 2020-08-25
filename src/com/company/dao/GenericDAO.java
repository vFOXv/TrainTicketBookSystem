package com.company.dao;




/**
 * Generic DAO class that contains common DAO methods
 *
 * @author Evgeniy Lukashik
 */

public interface GenericDAO<T> {

    T getEntityById(Long id);

    void saveEntity(T entity);

    void updateEntity(T entity);

    void removeEntity(T entity);

}
