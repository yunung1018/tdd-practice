package ntut.csie.sslab.ddd.model;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public abstract class AggregateRoot<ID> implements Entity<ID> {
    protected final ID id;
    private final List<DomainEvent> domainEvents;
    protected boolean isDeleted;

    public AggregateRoot(ID id) {
        this.id = id;
        this.domainEvents = new CopyOnWriteArrayList<>();
    }

    public void addDomainEvent(DomainEvent domainEvent) {
        domainEvents.add(domainEvent);
    }

    public List<DomainEvent> getDomainEvents() {
        return Collections.unmodifiableList(domainEvents);
    }

    public void clearDomainEvents(){
        domainEvents.clear();
    }

    public ID getId(){
        return id;
    }
}
