package tw.teddysoft.clean.usecase.domainevent.handler;

import com.google.common.eventbus.Subscribe;
import tw.teddysoft.clean.domain.model.DomainEventBus;
import tw.teddysoft.clean.domain.model.FlowEvent;
import tw.teddysoft.clean.usecase.domainevent.DomainEventRepository;
import tw.teddysoft.clean.usecase.domainevent.FlowEventRepository;


public class FlowEventHandler {

    private FlowEventRepository repository;
    private DomainEventBus eventBus;

    public FlowEventHandler(FlowEventRepository repository, DomainEventBus eventBus){
        this.repository = repository;
        this.eventBus = eventBus;
    }


    @Subscribe
    public void handleEvent(FlowEvent event) {
        System.out.println(" -----------> FlowEventHandler");

//        if(event instanceof FlowEvent){
            if(null != repository)
            {
                repository.save((FlowEvent) event);
            }
            else{
                System.err.println("DomainEventRepository instance is null. The Domain event is not stored." + event.detail());
            }
//        }
    }

}
