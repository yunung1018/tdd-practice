package tw.teddysoft.clean.usecase.domainevent.handler;

import com.google.common.eventbus.Subscribe;
import tw.teddysoft.clean.domain.model.DomainEvent;
import tw.teddysoft.clean.domain.model.DomainEventBus;
import tw.teddysoft.clean.domain.model.FlowEvent;
import tw.teddysoft.clean.domain.model.kanbanboard.board.event.BoardCreated;
import tw.teddysoft.clean.domain.model.kanbanboard.board.event.WorkflowCommitted;
import tw.teddysoft.clean.domain.model.kanbanboard.workflow.Workflow;
import tw.teddysoft.clean.domain.model.kanbanboard.workspace.Workspace;
import tw.teddysoft.clean.usecase.domainevent.DomainEventRepository;
import tw.teddysoft.clean.usecase.kanbanboard.workflow.WorkflowRepository;
import tw.teddysoft.clean.usecase.kanbanboard.workspace.WorkspaceRepository;


public class FlowEventHandler {

    private DomainEventRepository<FlowEvent> repository;
    private DomainEventBus eventBus;

    public FlowEventHandler(DomainEventRepository<FlowEvent> repository, DomainEventBus eventBus){
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
