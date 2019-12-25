package tw.teddysoft.clean.domain.model.kanbanboard.board;

import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;
import tw.teddysoft.clean.domain.model.*;
import tw.teddysoft.clean.domain.model.kanbanboard.board.event.BoardCreated;
import tw.teddysoft.clean.domain.model.kanbanboard.workflow.Workflow;
import tw.teddysoft.clean.domain.model.kanbanboard.workspace.Workspace;
import tw.teddysoft.clean.usecase.kanbanboard.workflow.WorkflowRepository;
import tw.teddysoft.clean.usecase.workspace.WorkspaceRepository;

import java.util.ArrayList;
import java.util.List;

public class BoardCreatedHandler implements DomainEventSubscriber<BoardCreated> {

    private WorkflowRepository workflowRepository;
    private WorkspaceRepository workspaceRepository;
    private EventBus eventBus;

    public BoardCreatedHandler(WorkspaceRepository workspaceRepository, WorkflowRepository workflowRepository, EventBus eventBus){
        this.workspaceRepository = workspaceRepository;
        this.workflowRepository = workflowRepository;
        this.eventBus = eventBus;
    }

    public Class<BoardCreated> subscribedToEventType() {
        return BoardCreated.class;
    }

    @Subscribe
    @Override
    public void handleEvent(BoardCreated domainEvent) {

        Workspace workspace = workspaceRepository.findById(domainEvent.getEntity().getWorkspaceId());
        workspace.commitBoard(domainEvent.getEntity().getId());
        System.out.println("    ======> the board is committed to its workspace");
        postAll(workspace, eventBus);


        Workflow workflow = new Workflow("Default Workflow", domainEvent.getEntity().getId());
        workflowRepository.save(workflow);
        System.out.println("    ======> A default workflow created and saved");

        postAll(workflow, eventBus);

//        workspace.getDomainEvents().stream().forEach(event -> eventBus.post(event));
//        workspace.clearDomainEvents();

//        postAll(workflow, eventBus);

//        workflow.getDomainEvents().stream().forEach(event -> eventBus.post(event));
//        workflow.clearDomainEvents();


//        DomainEventPublisher.instance().publishAll(workflow);
    }

    public void postAll(Entity entity, EventBus eventBus){
        System.out.println("postAll 1 : event size =  " + entity.getDomainEvents().size());
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
//
//        System.out.println("postAll 1 : event size =  " + entity.getDomainEvents().size());
//
//        List<DomainEvent> events = new ArrayList(entity.getDomainEvents());
//        entity.clearDomainEvents();
//
//        for(DomainEvent each : events){
//            System.out.println("event : " + each.detail());
//            entity.removeDomainEvent(each);
//            eventBus.post(each);
//        }
////        entity.clearDomainEvents();
//    }
}
