package tw.teddysoft.clean.usecase.domainevent.sourcing;

import tw.teddysoft.clean.domain.model.DomainEvent;
import tw.teddysoft.clean.domain.model.DomainEventSubscriber;
import tw.teddysoft.clean.domain.model.PersistentDomainEvent;
import tw.teddysoft.clean.usecase.domainevent.DomainEventRepository;

public class EventSourcingSubscriber implements DomainEventSubscriber<DomainEvent> {

    private DomainEventRepository<PersistentDomainEvent> repository;

    public EventSourcingSubscriber(DomainEventRepository<PersistentDomainEvent> repo){
        repository = repo;
    }

    public Class<DomainEvent> subscribedToEventType() {
        return DomainEvent.class;
    }

    @Override
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
