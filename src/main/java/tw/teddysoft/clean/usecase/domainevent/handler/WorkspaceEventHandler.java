package tw.teddysoft.clean.usecase.domainevent.handler;

import com.google.common.eventbus.Subscribe;
import tw.teddysoft.clean.domain.model.DomainEventBus;
import tw.teddysoft.clean.domain.model.kanbanboard.workflow.Workflow;
import tw.teddysoft.clean.domain.model.kanbanboard.workspace.Workspace;
import tw.teddysoft.clean.domain.model.kanbanboard.workspace.event.BoardCommitted;
import tw.teddysoft.clean.domain.usecase.repository.Repository;


public class WorkspaceEventHandler {

    private Repository<Workflow> workflowRepository;
    private Repository<Workspace> workspaceRepository;
    private DomainEventBus eventBus;

    public WorkspaceEventHandler(Repository<Workspace> workspaceRepository, Repository<Workflow> workflowRepository, DomainEventBus eventBus){
        this.workspaceRepository = workspaceRepository;
        this.workflowRepository = workflowRepository;
        this.eventBus = eventBus;
    }

    @Subscribe
    public void handleEvent(BoardCommitted domainEvent) {
        System.out.println("WorkspaceEventHandler, event = " + domainEvent.detail());
    }

}
