package tw.teddysoft.clean.usecase.domainevent.flow;

import com.google.common.eventbus.Subscribe;
import tw.teddysoft.clean.domain.model.*;
import tw.teddysoft.clean.domain.usecase.repository.Repository;

public class FlowEventSubscriber{

    private Repository<FlowEvent> repository;

    public FlowEventSubscriber(Repository repo){
        repository = repo;
    }

    public Class<DomainEvent> subscribedToEventType() {
        return DomainEvent.class;
    }

    @Subscribe
    public void handleEvent(DomainEvent domainEvent) {

//        if(domainEvent instanceof WorkItemMovedIn || domainEvent instanceof WorkItemMovedOut){
        if(domainEvent instanceof FlowEvent){
            if(null != repository)
            {
//                PersistentFlowEvent event = new PersistentFlowEvent();
//
//                if (domainEvent instanceof WorkItemMovedOut)
//                    event.setType(WorkItemMovedOut.class);
//                else (domainEvent instanceof WorkItemMovedIn)
//                    event.setType(WorkItemMovedIn.class);
//                event.setStageId(((FlowEvent) domainEvent).getStageId());
//                event.setMiniStageId(((FlowEvent) domainEvent).getMiniStageId());
//                event.setSwimLaneId(((FlowEvent) domainEvent).getSwimLaneId());
//                event.setWorkItemId(((FlowEvent) domainEvent).getWorkItemId());

                repository.save((FlowEvent) domainEvent);
            }
            else{
                System.err.println("DomainEventRepository instance is null. The Domain event is not stored." + domainEvent.detail());
            }
        }
//        System.out.println(domainEvent.detail());
    }
}
