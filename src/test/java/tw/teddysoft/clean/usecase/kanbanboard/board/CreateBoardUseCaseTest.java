package tw.teddysoft.clean.usecase.kanbanboard.board;

import org.junit.Before;
import org.junit.Test;
import tw.teddysoft.clean.adapter.gateway.kanbanboard.InMemoryBoardRepository;
import tw.teddysoft.clean.adapter.gateway.kanbanboard.InMemoryWorkspaceRepository;
import tw.teddysoft.clean.adapter.presenter.kanbanboard.board.SingleBoardPresenter;
import tw.teddysoft.clean.domain.model.AbstractDomainEventTest;
import tw.teddysoft.clean.usecase.kanbanboard.board.create.CreateBoardInput;
import tw.teddysoft.clean.usecase.kanbanboard.board.create.CreateBoardOutput;
import tw.teddysoft.clean.usecase.kanbanboard.board.create.CreateBoardUseCase;
import tw.teddysoft.clean.usecase.kanbanboard.board.create.impl.CreateBoardUseCaseImpl;
import tw.teddysoft.clean.usecase.kanbanboard.workspace.CreateWorkspaceTest;
import tw.teddysoft.clean.usecase.kanbanboard.workspace.WorkspaceRepository;
import tw.teddysoft.clean.usecase.kanbanboard.workspace.create.CreateWorkspaceOutput;
import tw.teddysoft.clean.usecase.kanbanboard.workspace.create.CreateWorkspaceUseCase;
import tw.teddysoft.clean.usecase.kanbanboard.workspace.create.impl.CreateWorkspaceUseCaseImpl;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.*;

public class CreateBoardUseCaseTest extends AbstractDomainEventTest {

    public static final String BOARD_NAME = "ScrumBoard";

    private WorkspaceRepository workspaceRepository;
    private String workspaceId;

    @Before
    public void setUpDefaultWorkspace(){
        super.setUp();

        workspaceRepository = new InMemoryWorkspaceRepository();
        workspaceId = createWorkspace(workspaceRepository);

        assertThat(storedSubscriber.expectedResults.size()).isEqualTo(1);
        assertThat(storedSubscriber.expectedResults.get(0)).startsWith("WorkspaceCreated");
        storedSubscriber.expectedResults.clear();
    }

    @Test
    public void create_a_board_should_commit_it_to_a_workspace() {

        BoardRepository boardRepository = new InMemoryBoardRepository();
        CreateBoardOutput output = doAddBoardUseCase(workspaceId, BOARD_NAME, workspaceRepository, boardRepository);

        assertNotNull(output.getBoardId());
        assertNotNull(boardRepository.findById(output.getBoardId()));
        assertEquals(workspaceId, boardRepository.findById(output.getBoardId()).getWorkspaceId());
        assertEquals(BOARD_NAME, output.getBoardName());
        assertEquals(1, boardRepository.findAll().size());
        assertEquals(BOARD_NAME, boardRepository.findAll().get(0).getName());
        assertTrue(output.toString().startsWith("Board Name: ScrumBoard; Board Id: "));

        assertThat(storedSubscriber.expectedResults.size()).isEqualTo(2);
        assertThat(storedSubscriber.expectedResults.get(0)).startsWith("BoardCreated");
        assertThat(storedSubscriber.expectedResults.get(1)).startsWith("BoardCommitted");

    }

    public static CreateBoardOutput doAddBoardUseCase(
            String workspaceId,
            String boardName,
            WorkspaceRepository workspaceRepository,
            BoardRepository boardRepository){

        CreateBoardInput input = CreateBoardUseCaseImpl.createInput();
        CreateBoardOutput output = new SingleBoardPresenter();
        input.setWorkspaceId(workspaceId);
        input.setBoardName(boardName);

        CreateBoardUseCase addBoardUC = new CreateBoardUseCaseImpl(workspaceRepository, boardRepository);
        addBoardUC.execute(input, output);

        return output;
    }


    private String createWorkspace(WorkspaceRepository workspaceRepository){
        CreateWorkspaceUseCase createWorkspaceUC = new CreateWorkspaceUseCaseImpl(workspaceRepository);
        CreateWorkspaceOutput output = CreateWorkspaceTest.doCreateWorkspaceUseCase(workspaceRepository, CreateWorkspaceTest.USER_ID, CreateWorkspaceTest.WORKSPACE_NAME);
        return output.getWorkspaceId();
    }

}
