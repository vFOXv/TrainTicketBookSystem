package dao;


import java.io.IOException;

/**
 * Generic DAO class that contains common DAO methods
 *
 * @author Evgeniy Lukashik
 */

public interface GenericDAO<T> {

    T getEntityById(Long id);

    void saveEntity(T entity) throws IOException;

    void updateEntity(T entity);

    void removeEntity(T entity);

}
