package tw.teddysoft.clean.domain.model.kanbanboard.board;

import org.junit.Before;
import org.junit.Test;
import tw.teddysoft.clean.domain.model.*;
import tw.teddysoft.clean.usecase.Context;
import tw.teddysoft.clean.usecase.TestContext;
import tw.teddysoft.clean.usecase.domainevent.handler.BoardEventHandler;
import tw.teddysoft.clean.usecase.domainevent.handler.WorkflowEventHandler;
import tw.teddysoft.clean.usecase.domainevent.handler.WorkspaceEventHandler;

import static org.assertj.core.api.Assertions.assertThat;

public class BoardTest {

    private TestContext context;
    private String workspaceId = Context.WORKSPACE_ID;

    private DomainEventBus eventBus;

    @Before
    public void setUp(){

        eventBus = new DomainEventBus();
        context = TestContext.newInstance();

        BoardEventHandler boardEventHandler = new BoardEventHandler(context.getWorkspaceRepository(), context.getWorkflowRepository(), eventBus);
        WorkflowEventHandler workflowEventHandler = new WorkflowEventHandler(context.getBoardRepository(), context.getWorkflowRepository(), eventBus);
        WorkspaceEventHandler workspaceEventHandler = new WorkspaceEventHandler(context.getWorkspaceRepository(), context.getWorkflowRepository(), eventBus);

        workspaceId = context.doCreateWorkspaceUseCase(Context.USER_ID, Context.WORKSPACE_NAME).getWorkspaceId();

        eventBus.register(boardEventHandler);
        eventBus.register(workflowEventHandler);
        eventBus.register(workspaceEventHandler);
    }


    @Test
    public void creating_board_publishes_BoardCreated_event() {
        Board board = new Board("Scrum Board", workspaceId);
        context.getBoardRepository().save(board);

        assertThat(board.getDomainEvents().size()).isEqualTo(1);
        assertThat(board.getDomainEvents().get(0).detail()).startsWith("BoardCreated");

        eventBus.postAll(board);
    }

//    //TODO
//    @Test
//    public void deleting_board_publishes_a_BoardDeleted_event() {
//        // how to delete a board?
//    }
}
