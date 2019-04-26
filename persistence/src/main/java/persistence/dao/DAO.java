package persistence.dao;

import java.util.Collection;

public interface DAO<T> {
    void create(T item);
    void update(T item);
    void delete(T item);

    Collection<T> getAll();
}
