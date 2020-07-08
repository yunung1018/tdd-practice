package tw.teddysoft.clean.domain.model.kanbanboard.board;

import org.junit.Before;
import org.junit.Test;
import tw.teddysoft.clean.domain.model.*;
import tw.teddysoft.clean.usecase.Context;
import tw.teddysoft.clean.usecase.TestContext;
import tw.teddysoft.clean.usecase.domainevent.handler.NotifyWorkflow;
import tw.teddysoft.clean.usecase.domainevent.handler.NotifyBoard;
import tw.teddysoft.clean.usecase.domainevent.handler.NotifyWorkspace;

import static org.assertj.core.api.Assertions.assertThat;

public class BoardTest {

    private TestContext context;
    private String workspaceId = Context.WORKSPACE_ID;

    private DomainEventBus eventBus;

    @Before
    public void setUp(){

        eventBus = new DomainEventBus();
        context = TestContext.newInstance();

        NotifyWorkflow notifyWorkflow = new NotifyWorkflow(context.getWorkflowRepository(), eventBus);
        NotifyBoard notifyBoard = new NotifyBoard(context.getBoardRepository(), eventBus);
        NotifyWorkspace notifyWorkspace = new NotifyWorkspace(context.getWorkspaceRepository(), context.getWorkflowRepository(), eventBus);

        workspaceId = context.doCreateWorkspaceUseCase(Context.USER_ID, Context.WORKSPACE_NAME).getWorkspaceId();

        eventBus.register(notifyWorkflow);
        eventBus.register(notifyBoard);
        eventBus.register(notifyWorkspace);
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
