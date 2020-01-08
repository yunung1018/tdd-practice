package tw.teddysoft.clean.usecase.domainevent.handler;

import com.google.common.eventbus.Subscribe;
import tw.teddysoft.clean.domain.model.DomainEventBus;
import tw.teddysoft.clean.domain.model.FlowEvent;
import tw.teddysoft.clean.domain.usecase.repository.Repository;


public class FlowEventHandler {

    private Repository<FlowEvent> repository;
    private DomainEventBus eventBus;

    public FlowEventHandler(Repository<FlowEvent> repository, DomainEventBus eventBus){
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
