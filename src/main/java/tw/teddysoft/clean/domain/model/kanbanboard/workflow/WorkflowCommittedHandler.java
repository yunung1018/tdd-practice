package tw.teddysoft.clean.domain.model.kanbanboard.workflow;

import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;
import tw.teddysoft.clean.domain.model.DomainEvent;
import tw.teddysoft.clean.domain.model.DomainEventSubscriber;
import tw.teddysoft.clean.domain.model.Entity;
import tw.teddysoft.clean.domain.model.kanbanboard.board.Board;
import tw.teddysoft.clean.domain.model.kanbanboard.workflow.event.WorkflowCreated;
import tw.teddysoft.clean.usecase.kanbanboard.board.BoardRepository;

import java.util.ArrayList;
import java.util.List;

public class WorkflowCommittedHandler implements DomainEventSubscriber<WorkflowCreated> {

    private BoardRepository boardRepository;
    private EventBus eventBus;

    public WorkflowCommittedHandler(BoardRepository boardRepository, EventBus eventBus){
        this.boardRepository = boardRepository;
        this.eventBus = eventBus;
    }

    public Class<WorkflowCreated> subscribedToEventType() {
        return WorkflowCreated.class;
    }

    @Subscribe
    @Override
    public void handleEvent(WorkflowCreated domainEvent) {

        System.out.println("WorkflowCommittedHandler ++++++++++++++++++++++>");


        System.out.println("WorkflowCommitted ======> doming nothing");
    }

    public void postAll(Entity entity, EventBus eventBus){
        System.out.println("postAll 3 : event size =  " + entity.getDomainEvents().size());
        List<DomainEvent> events = new ArrayList(entity.getDomainEvents());
        entity.clearDomainEvents();

        for(DomainEvent each : events){
            System.out.println("event : " + each.detail());
//            entity.removeDomainEvent(each);
            eventBus.post(each);
        }
        events.clear();
    }

//    public void postAll(Entity entity, EventBus eventBus){
//        for(DomainEvent each : entity.getDomainEvents()){
//            entity.removeDomainEvent(each);
//            eventBus.post(each);
//        }
////        entity.clearDomainEvents();
//    }
}
