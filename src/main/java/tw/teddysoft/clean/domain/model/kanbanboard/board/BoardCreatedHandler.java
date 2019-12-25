package tw.teddysoft.clean.domain.model.kanbanboard.board;

import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;
import tw.teddysoft.clean.domain.model.*;
import tw.teddysoft.clean.domain.model.kanbanboard.board.event.BoardCreated;
import tw.teddysoft.clean.domain.model.kanbanboard.workflow.Workflow;
import tw.teddysoft.clean.domain.model.kanbanboard.workspace.Workspace;
import tw.teddysoft.clean.usecase.kanbanboard.workflow.WorkflowRepository;
import tw.teddysoft.clean.usecase.kanbanboard.workspace.WorkspaceRepository;

import java.util.ArrayList;
import java.util.List;

public class BoardCreatedHandler {

    private WorkflowRepository workflowRepository;
    private WorkspaceRepository workspaceRepository;
    private DomainEventBus eventBus;

    public BoardCreatedHandler(WorkspaceRepository workspaceRepository, WorkflowRepository workflowRepository, DomainEventBus eventBus){
        this.workspaceRepository = workspaceRepository;
        this.workflowRepository = workflowRepository;
        this.eventBus = eventBus;
    }

    public Class<BoardCreated> subscribedToEventType() {
        return BoardCreated.class;
    }

    @Subscribe
    public void handleEvent(BoardCreated domainEvent) {

        Workspace workspace = workspaceRepository.findById(domainEvent.getEntity().getWorkspaceId());
        workspace.commitBoard(domainEvent.getEntity().getId());
        System.out.println("    ======> the board is committed to its workspace");
        eventBus.postAll(workspace);

        Workflow workflow = new Workflow("Default Workflow", domainEvent.getEntity().getId());
        workflowRepository.save(workflow);
        System.out.println("    ======> A default workflow created and saved");
        eventBus.postAll(workflow);
    }

}
