package tw.teddysoft.clean.usecase.domainevent.handler;

import com.google.common.eventbus.Subscribe;
import tw.teddysoft.clean.domain.model.*;
import tw.teddysoft.clean.usecase.domainevent.DomainEventRepository;

public class EventSourcingHandler {

    private final DomainEventRepository<PersistentDomainEvent> repository;

    public EventSourcingHandler(DomainEventRepository<PersistentDomainEvent> repo){
        repository = repo;
    }

    @Subscribe
    public void handleEvent(DomainEvent domainEvent) {
//        System.out.println(domainEvent.detail());
        if(null != repository)
        {
            repository.save(new PersistentDomainEvent(domainEvent));
        }
        else{
            System.err.println("DomainEventRepository instance is null. The Domain event is not stored." + domainEvent.detail());
        }
    }
}

