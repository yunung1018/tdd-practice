package tw.teddysoft.clean.usecase.kanbanboard.workspace;

import org.junit.Before;
import org.junit.Test;
import tw.teddysoft.clean.adapter.gateway.kanbanboard.InMemoryBoardRepository;
import tw.teddysoft.clean.adapter.gateway.kanbanboard.InMemoryWorkspaceRepository;
import tw.teddysoft.clean.adapter.presenter.kanbanboard.board.SingleBoardPresenter;
import tw.teddysoft.clean.adapter.presenter.kanbanboard.workspace.SingleWorkspacePresenter;
import tw.teddysoft.clean.usecase.kanbanboard.board.BoardRepository;
import tw.teddysoft.clean.usecase.kanbanboard.board.add.AddBoardInput;
import tw.teddysoft.clean.usecase.kanbanboard.board.add.AddBoardOutput;
import tw.teddysoft.clean.usecase.kanbanboard.board.add.AddBoardUseCase;
import tw.teddysoft.clean.usecase.kanbanboard.board.add.impl.AddBoardUseCaseImpl;
import tw.teddysoft.clean.usecase.kanbanboard.workflow.create.CreateWorkflowUseCase;
import tw.teddysoft.clean.usecase.kanbanboard.workspace.create.CreateWorkspaceInput;
import tw.teddysoft.clean.usecase.kanbanboard.workspace.create.CreateWorkspaceOutput;
import tw.teddysoft.clean.usecase.kanbanboard.workspace.create.CreateWorkspaceUseCase;
import tw.teddysoft.clean.usecase.kanbanboard.workspace.create.impl.CreateWorkspaceUseCaseImpl;

import static org.junit.Assert.*;

public class CreateWorkspaceTest {

    @Before
    public void setUp(){
    }

    @Test
    public void add_a_workspace() {

        WorkspaceRepository repository = new InMemoryWorkspaceRepository();

        CreateWorkspaceInput input = CreateWorkspaceUseCaseImpl.createInput();
        CreateWorkspaceOutput output = new SingleWorkspacePresenter();
        input.setUserId("000-5487");
        input.setWorkspaceName("Teddy's Workspace");

        CreateWorkspaceUseCase createWorkspaceUC = new CreateWorkspaceUseCaseImpl(repository);
        createWorkspaceUC.execute(input, output);

        assertNotNull(output.getWorkspaceId());
        assertNotNull(repository.findById(output.getWorkspaceId()));
        assertEquals(input.getUserId(), repository.findById(output.getWorkspaceId()).getUserId());
        assertEquals(1, repository.findAll().size());
        assertEquals("Teddy's Workspace", repository.findAll().get(0).getName());
    }



}
