package persistence.repository;

import java.util.Collection;

public interface Repository<T> {
    void add(T item);
    void update(T item);
    void delete(T item);

    Collection<T> selectAll();
    Collection<T> query(Specification specification);
}
