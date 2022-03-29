package repository;

import java.util.List;

public interface GenericRepository<T,ID> {
    T save(T t);
    void update(T t);
    void delete(T t);
    void deleteById(ID id);
    List<T> findAll();
    T findById(ID id);
}
