package tw.teddysoft.clean.domain.model.kanbanboard.workflow;

import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;
import tw.teddysoft.clean.domain.model.DomainEvent;
import tw.teddysoft.clean.domain.model.DomainEventPublisher;
import tw.teddysoft.clean.domain.model.DomainEventSubscriber;
import tw.teddysoft.clean.domain.model.Entity;
import tw.teddysoft.clean.domain.model.kanbanboard.board.Board;
import tw.teddysoft.clean.domain.model.kanbanboard.board.event.BoardCreated;
import tw.teddysoft.clean.domain.model.kanbanboard.workflow.event.WorkflowCreated;
import tw.teddysoft.clean.domain.model.kanbanboard.workspace.Workspace;
import tw.teddysoft.clean.usecase.kanbanboard.board.BoardRepository;
import tw.teddysoft.clean.usecase.kanbanboard.workflow.WorkflowRepository;

import java.util.ArrayList;
import java.util.List;

public class WorkflowCreatedHandler implements DomainEventSubscriber<WorkflowCreated> {

    private BoardRepository boardRepository;
    private EventBus eventBus;

    public WorkflowCreatedHandler(BoardRepository boardRepository, EventBus eventBus){
        this.boardRepository = boardRepository;
        this.eventBus = eventBus;
    }

    public Class<WorkflowCreated> subscribedToEventType() {
        return WorkflowCreated.class;
    }

    @Subscribe
    @Override
    public void handleEvent(WorkflowCreated domainEvent) {

        System.out.println("WorkflowCreatedHandler ******************>");

        Board board = boardRepository.findById(domainEvent.getEntity().getBoardId());
        System.out.println("Board domain event size  = " + board.getDomainEvents().size());
//        board.clearDomainEvents();
//        System.out.println("Clear Board domain event");
//        System.out.println("Board domain event size  = " + board.getDomainEvents().size());

        board.commitWorkflow(domainEvent.getEntity().getId());
        boardRepository.save(board);
        System.out.println("    ======> commit the workflow to its board");

        System.out.println("Board domain event size  = " + board.getDomainEvents().size());


//        eventBus.post(board.getDomainEvents().iterator().next());
        postAll(board, eventBus);

//        DomainEventPublisher.instance().publishAll(board);

    }

    public void postAll(Entity entity, EventBus eventBus){
        System.out.println("postAll 2 : event size =  " + entity.getDomainEvents().size());
        List<DomainEvent> events = new ArrayList(entity.getDomainEvents());
        entity.clearDomainEvents();

        for(DomainEvent each : events){
            System.out.println("event : " + each.detail());
//            entity.removeDomainEvent(each);
            eventBus.post(each);
        }
        events.clear();
    }
//
//    public void postAll(Entity entity, EventBus eventBus){
//
//        System.out.println("postAll 2 : event size =  " + entity.getDomainEvents().size());
//
//        for(DomainEvent each : entity.getDomainEvents()){
//            System.out.println("event : " + each.detail());
//            entity.removeDomainEvent(each);
//            eventBus.post(each);
//        }
////        entity.clearDomainEvents();
//    }
}
