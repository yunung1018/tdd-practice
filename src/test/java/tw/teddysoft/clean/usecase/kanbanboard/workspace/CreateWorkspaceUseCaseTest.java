package tw.teddysoft.clean.usecase.kanbanboard.workspace;

import org.junit.Before;
import org.junit.Test;
import tw.teddysoft.clean.adapter.gateway.kanbanboard.InMemoryAggregateRootRepositoryPeer;
import tw.teddysoft.clean.adapter.presenter.kanbanboard.workspace.SingleWorkspacePresenter;
import tw.teddysoft.clean.domain.usecase.UseCase;
import tw.teddysoft.clean.usecase.TestContext;
import tw.teddysoft.clean.usecase.kanbanboard.workspace.create.CreateWorkspaceInput;
import tw.teddysoft.clean.usecase.kanbanboard.workspace.create.CreateWorkspaceOutput;
import tw.teddysoft.clean.usecase.kanbanboard.workspace.create.CreateWorkspaceUseCase;

import static org.junit.Assert.*;

public class CreateWorkspaceUseCaseTest {

    private TestContext context;
    public static final String USER_ID = "USER_007";
    public static final String WORKSPACE_NAME = "Teddy's Workspace";

    @Before
    public void setUp(){
        context = TestContext.newInstance();
        context.registerAllEventHandler();
    }

    @Test
    public void add_a_workspace() {

        CreateWorkspaceOutput output = context.doCreateWorkspaceUseCase(USER_ID, WORKSPACE_NAME);

        assertNotNull(output.getWorkspaceId());
        assertNotNull(context.getWorkspaceRepository().findById(output.getWorkspaceId()));
        assertEquals(USER_ID, context.getWorkspaceRepository().findById(output.getWorkspaceId()).getUserId());
        assertEquals(1, context.getWorkspaceRepository().findAll().size());
        assertEquals(WORKSPACE_NAME, context.getWorkspaceRepository().findAll().get(0).getName());
    }

}
