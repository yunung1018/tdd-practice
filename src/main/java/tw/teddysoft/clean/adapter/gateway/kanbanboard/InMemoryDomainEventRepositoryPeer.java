package tw.teddysoft.clean.adapter.gateway.kanbanboard;


import tw.teddysoft.clean.domain.model.AggregateRoot;
import tw.teddysoft.clean.domain.model.DomainEvent;
import tw.teddysoft.clean.domain.usecase.repository.RepositoryPeer;

import java.util.ArrayList;
import java.util.List;

public class InMemoryDomainEventRepositoryPeer<T extends DomainEvent> implements RepositoryPeer<T> {

    private final List<T> entities;

    public InMemoryDomainEventRepositoryPeer(){
        entities = new ArrayList<T>();
    }

    @Override
    public List<T> findAll() {
        return entities;
    }

    @Override
    public T findById(String id) {
        throw new RuntimeException("Domain event repository does not support findById query");
    }

    @Override
    public T findFirstByName(String name) {
        throw new RuntimeException("Domain event repository does not support findFirstByName query");
    }

    @Override
    public T save(T arg) {
        if (entities.contains(arg))
            return arg;
        else if (entities.add(arg))
            return arg;
        else
            return null;
    }

    @Override
    public boolean remove(T arg) {
        return entities.remove(arg);
    }

}
