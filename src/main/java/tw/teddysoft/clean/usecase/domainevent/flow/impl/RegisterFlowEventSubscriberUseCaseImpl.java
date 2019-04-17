package tw.teddysoft.clean.usecase.domainevent.flow.impl;

import tw.teddysoft.clean.domain.model.DomainEventPublisher;
import tw.teddysoft.clean.domain.model.FlowEvent;
import tw.teddysoft.clean.domain.model.PersistentDomainEvent;
import tw.teddysoft.clean.usecase.domainevent.DomainEventRepository;
import tw.teddysoft.clean.usecase.domainevent.flow.FlowEventSubscriber;
import tw.teddysoft.clean.usecase.domainevent.flow.RegisterFlowEventSubscriberInput;
import tw.teddysoft.clean.usecase.domainevent.flow.RegisterFlowEventSubscriberOutput;
import tw.teddysoft.clean.usecase.domainevent.flow.RegisterFlowEventSubscriberUseCase;
import tw.teddysoft.clean.usecase.domainevent.sourcing.EventSourcingSubscriber;

public class RegisterFlowEventSubscriberUseCaseImpl implements RegisterFlowEventSubscriberUseCase {

    private DomainEventRepository<FlowEvent> repository;

    public RegisterFlowEventSubscriberUseCaseImpl(DomainEventRepository<FlowEvent> repository){
        this.repository = repository;
    }

    public static RegisterFlowEventSubscriberInput createInput(){
        return new RegisterFlowEventSubscriberInputImpl();
    }

    @Override
    public void execute(RegisterFlowEventSubscriberInput input, RegisterFlowEventSubscriberOutput output) {
        FlowEventSubscriber subscriber =
                new FlowEventSubscriber(repository);

//        DomainEventPublisher.instance().reset();
        DomainEventPublisher.instance().subscribe(subscriber);
    }

    private static class RegisterFlowEventSubscriberInputImpl implements RegisterFlowEventSubscriberInput {
    }

}
