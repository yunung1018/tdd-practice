package ntut.csie.sslab.kanban.usecase.eventhandler;

import ntut.csie.sslab.ddd.adapter.presenter.cqrs.CqrsCommandPresenter;
import ntut.csie.sslab.kanban.entity.model.board.Board;
import ntut.csie.sslab.kanban.usecase.AbstractSpringBootJpaTest;
import ntut.csie.sslab.kanban.usecase.board.create.CreateBoardInput;
import ntut.csie.sslab.kanban.usecase.board.create.CreateBoardUseCase;
import ntut.csie.sslab.kanban.usecase.workflow.create.CreateWorkflowInput;
import ntut.csie.sslab.kanban.usecase.workflow.create.CreateWorkflowUseCase;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class NotifyBoardTest extends AbstractSpringBootJpaTest {

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
        CreateBoardInput input = createBoardUseCase.newInput();
        CqrsCommandPresenter output = CqrsCommandPresenter.newInstance();
        input.setTeamId(teamId);
        input.setName(boardName);
        input.setUserId(userId);

        createBoardUseCase.execute(input, output);

        return output.getId();
    }

    private String create_a_workflow(String boardId) {

        CreateWorkflowUseCase createWorkflowUseCase = newCreateWorkflowUseCase();
        CreateWorkflowInput input = createWorkflowUseCase.newInput();
        CqrsCommandPresenter output = CqrsCommandPresenter.newInstance();
        input.setBoardId(boardId);
        input.setWorkflowName("workflow");
        input.setUserId(userId);
        input.setUsername(username);

        createWorkflowUseCase.execute(input, output);

        return output.getId();
    }
}
