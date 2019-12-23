package tw.teddysoft.clean.usecase.kanbanboard.workspace;

import org.junit.Before;
import org.junit.Test;
import tw.teddysoft.clean.adapter.gateway.kanbanboard.InMemoryWorkspaceRepository;
import tw.teddysoft.clean.adapter.presenter.kanbanboard.workspace.SingleWorkspacePresenter;
import tw.teddysoft.clean.usecase.kanbanboard.workspace.create.CreateWorkspaceInput;
import tw.teddysoft.clean.usecase.kanbanboard.workspace.create.CreateWorkspaceOutput;
import tw.teddysoft.clean.usecase.kanbanboard.workspace.create.CreateWorkspaceUseCase;
import tw.teddysoft.clean.usecase.kanbanboard.workspace.create.impl.CreateWorkspaceUseCaseImpl;

import static org.junit.Assert.*;

public class CreateWorkspaceTest {

    public static final String USER_ID = "USER_007";
    public static final String WORKSPACE_NAME = "Teddy's Workspace";

    @Before
    public void setUp(){
    }

    @Test
    public void add_a_workspace() {

        WorkspaceRepository repository = new InMemoryWorkspaceRepository();
        CreateWorkspaceOutput output = doCreateWorkspaceUseCase(repository, USER_ID, WORKSPACE_NAME);

        assertNotNull(output.getWorkspaceId());
        assertNotNull(repository.findById(output.getWorkspaceId()));
        assertEquals(USER_ID, repository.findById(output.getWorkspaceId()).getUserId());
        assertEquals(1, repository.findAll().size());
        assertEquals(WORKSPACE_NAME, repository.findAll().get(0).getName());
    }


    public static CreateWorkspaceOutput doCreateWorkspaceUseCase(WorkspaceRepository repository, String userId, String workspaceName){

        CreateWorkspaceInput input = CreateWorkspaceUseCaseImpl.createInput();
        CreateWorkspaceOutput output = new SingleWorkspacePresenter();
        input.setUserId(userId);
        input.setWorkspaceName(workspaceName);

        CreateWorkspaceUseCase createWorkspaceUC = new CreateWorkspaceUseCaseImpl(repository);
        createWorkspaceUC.execute(input, output);

        return output;
    }


}
