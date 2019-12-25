package tw.teddysoft.clean.domain.usecase;

import java.util.List;

public interface GenericRepository<T> {
    List<T> findAll();
    T findById(String id);
    T findFirstByName(String name);
    T save(T arg);
    boolean remove(T arg);
}
