package tw.teddysoft.clean.usecase.domainevent.sourcing;

import com.google.common.eventbus.Subscribe;
import tw.teddysoft.clean.domain.model.AbstractDomainEvent;
import tw.teddysoft.clean.domain.model.DomainEvent;
import tw.teddysoft.clean.domain.usecase.repository.Repository;

public class EventSourcingSubscriber {

    private Repository<DomainEvent> repository;

    public EventSourcingSubscriber(Repository repo){
        repository = repo;
    }

    public Class<DomainEvent> subscribedToEventType() {
        return DomainEvent.class;
    }

    @Subscribe
    public void handleEvent(DomainEvent domainEvent) {
//        System.out.println(domainEvent.detail());

        if(null != repository)
        {
            repository.save((AbstractDomainEvent) domainEvent);
        }
        else{
            System.err.println("DomainEventRepository instance is null. The Domain event is not stored." + domainEvent.detail());
        }
    }
}
