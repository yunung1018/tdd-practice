package tw.teddysoft.clean.usecase.domainevent.sourcing.impl;

import tw.teddysoft.clean.domain.model.DomainEventPublisher;
import tw.teddysoft.clean.domain.model.PersistentDomainEvent;
import tw.teddysoft.clean.usecase.domainevent.*;
import tw.teddysoft.clean.usecase.domainevent.sourcing.EventSourcingSubscriber;
import tw.teddysoft.clean.usecase.domainevent.sourcing.RegisterEventSourcingSubscriberInput;
import tw.teddysoft.clean.usecase.domainevent.sourcing.RegisterEventSourcingSubscriberOutput;
import tw.teddysoft.clean.usecase.domainevent.sourcing.RegisterEventSourcingSubscriberUseCase;

public class RegisterEventSourcingSubscriberUseCaseImpl implements RegisterEventSourcingSubscriberUseCase {

    private DomainEventRepository<PersistentDomainEvent> repository;

    public RegisterEventSourcingSubscriberUseCaseImpl(DomainEventRepository<PersistentDomainEvent>  repository){
        this.repository = repository;
    }

    @Override
    public RegisterEventSourcingSubscriberInput createInput(){
        return new RegisterEventSourcingSubscriberInputImpl();
    }

    @Override
    public void execute(RegisterEventSourcingSubscriberInput input, RegisterEventSourcingSubscriberOutput output) {

        EventSourcingSubscriber subscriber =
                new EventSourcingSubscriber(repository);

//        System.err.println("DomainEventPublisher.instance() = " + DomainEventPublisher.instance());

//        DomainEventPublisher.instance().reset();
        DomainEventPublisher.instance().subscribe(subscriber);
    }

    private static class RegisterEventSourcingSubscriberInputImpl implements RegisterEventSourcingSubscriberInput {
    }

}
