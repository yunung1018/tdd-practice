package tw.teddysoft.clean.usecase.domainevent.handler;

import com.google.common.eventbus.Subscribe;
import tw.teddysoft.clean.domain.model.DomainEventBus;
import tw.teddysoft.clean.domain.model.kanbanboard.workspace.event.BoardCommitted;
import tw.teddysoft.clean.usecase.kanbanboard.workflow.WorkflowRepository;
import tw.teddysoft.clean.usecase.workspace.WorkspaceRepository;


public class WorkspaceEventHandler {

    private WorkflowRepository workflowRepository;
    private WorkspaceRepository workspaceRepository;
    private DomainEventBus eventBus;

    public WorkspaceEventHandler(WorkspaceRepository workspaceRepository, WorkflowRepository workflowRepository, DomainEventBus eventBus){
        this.workspaceRepository = workspaceRepository;
        this.workflowRepository = workflowRepository;
        this.eventBus = eventBus;
    }

    @Subscribe
    public void handleEvent(BoardCommitted domainEvent) {
        System.out.println("WorkspaceEventHandler, event = " + domainEvent.detail());
    }

}
