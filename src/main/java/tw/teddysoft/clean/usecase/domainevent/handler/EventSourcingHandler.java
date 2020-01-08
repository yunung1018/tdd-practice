package tw.teddysoft.clean.usecase.domainevent.handler;

import com.google.common.eventbus.Subscribe;
import tw.teddysoft.clean.domain.model.*;
import tw.teddysoft.clean.domain.usecase.repository.Repository;

public class EventSourcingHandler {

    private final Repository<DomainEvent> repository;

    public EventSourcingHandler(Repository<DomainEvent> repo){
        repository = repo;
    }

    @Subscribe
    public void handleEvent(DomainEvent domainEvent) {
//        System.out.println(domainEvent.detail());
        if(null != repository)
        {
//            repository.save(new PersistentDomainEvent(domainEvent));
            repository.save((AbstractDomainEvent) domainEvent);
        }
        else{
            System.err.println("DomainEventRepository instance is null. The Domain event is not stored." + domainEvent.detail());
        }
    }
}

