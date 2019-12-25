package tw.teddysoft.clean.adapter.gateway.kanbanboard;


import tw.teddysoft.clean.domain.model.Entity;
import tw.teddysoft.clean.domain.usecase.GenericRepository;

import java.util.ArrayList;
import java.util.List;

public class GenericInMemoryRepository<T extends Entity> implements GenericRepository<T> {

    private List<T> aggregates;

    public GenericInMemoryRepository(){
        aggregates = new ArrayList<T>();
    }

    @Override
    public List<T> findAll() {
        return aggregates;
    }

    @Override
    public T findById(String id) {
        for(T each : aggregates){
            if (each.getId().equalsIgnoreCase(id))
                return each;
        }
        throw new RuntimeException("Cannot find object with id : " + id);
    }

    @Override
    public T findFirstByName(String name) {
        for(T each : aggregates){
            if (each.getName().equals(name))
                return each;
        }
        throw new RuntimeException("Cannot find object with name : " + name);
    }

    @Override
    public T save(T arg) {
        if (aggregates.contains(arg))
            return arg;
        else if (aggregates.add(arg))
            return arg;
        else
            return null;
    }

    @Override
    public boolean remove(T arg) {
        return aggregates.remove(arg);
    }

}
