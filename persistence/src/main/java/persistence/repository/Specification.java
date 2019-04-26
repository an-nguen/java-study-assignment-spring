package persistence.repository;

public interface Specification<T> {
    boolean specified(T item);
}
