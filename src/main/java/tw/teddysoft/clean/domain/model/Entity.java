package tw.teddysoft.clean.domain.model;

import java.io.Serializable;
import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;

public abstract class Entity implements Serializable {

    private static final long serialVersionUID = 1L;

    protected String name;
    protected String id;

    private final List<DomainEvent> domainEvents;

    public Entity(String name) {
        this.name = name;
        id = UUID.randomUUID().toString();
        domainEvents = new CopyOnWriteArrayList<>();

//        domainEvents = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name){
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void addDomainEvent(DomainEvent event){
        domainEvents.add(event);
    }

    public List<DomainEvent> getDomainEvents(){
        return Collections.unmodifiableList(domainEvents);
    }

    public void clearDomainEvents(){
        domainEvents.clear();
    }

    public void removeDomainEvent(DomainEvent event){
        domainEvents.remove(event);
    }

}
