package ntut.csie.sslab.kanban.usecase.eventhandler;

import ntut.csie.sslab.ddd.adapter.presenter.cqrs.CqrsCommandPresenter;
import ntut.csie.sslab.ddd.adapter.presenter.CommonViewModel;
import ntut.csie.sslab.kanban.board.entity.Board;
import ntut.csie.sslab.kanban.usecase.AbstractSpringBootJpaTest;
import ntut.csie.sslab.kanban.board.usecase.port.in.create.CreateBoardInput;
import ntut.csie.sslab.kanban.board.usecase.port.in.create.CreateBoardUseCase;
import ntut.csie.sslab.kanban.workflow.usecase.port.in.create.CreateWorkflowInput;
import ntut.csie.sslab.kanban.workflow.usecase.port.in.create.CreateWorkflowUseCase;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class NotifyBoardServiceTest extends AbstractSpringBootJpaTest {

    @BeforeEach
    @Override
    public void setUp() {
        super.setUp();
        domainEventBus.register(notifyBoardAdapter);
    }

    @Test
    public void create_a_workflow_commits_itself_to_its_board(){

        String boardId = create_a_board();
        String workflowId = create_a_workflow(boardId);

        Board board = boardRepository.findById(boardId).get();
        assertEquals(1, board.getCommittedWorkflows().size());
        assertTrue(board.getCommittedWorkflow(workflowId).isPresent());
    }


    private String create_a_board() {
        CreateBoardUseCase createBoardUseCase = newCreateBoardUseCase();
        CreateBoardInput input = new CreateBoardInput();
        CqrsCommandPresenter output = CqrsCommandPresenter.newInstance();
        input.setTeamId(teamId);
        input.setName(boardName);
        input.setUserId(userId);

        CommonViewModel viewModel = CqrsCommandPresenter.newInstance().buildViewModel(createBoardUseCase.execute(input));

        return viewModel.getId();
    }

    private String create_a_workflow(String boardId) {

        CreateWorkflowUseCase createWorkflowUseCase = newCreateWorkflowUseCase();
        CreateWorkflowInput input = new CreateWorkflowInput();
        CqrsCommandPresenter output = CqrsCommandPresenter.newInstance();
        input.setBoardId(boardId);
        input.setName("workflow");
        input.setUserId(userId);
        input.setUsername(username);

        CommonViewModel viewModel = CqrsCommandPresenter.newInstance().buildViewModel(createWorkflowUseCase.execute(input));

        return viewModel.getId();
    }
}
