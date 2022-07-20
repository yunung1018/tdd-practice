package ntut.csie.sslab.kanban.usecase.workflow;

import ntut.csie.sslab.ddd.adapter.presenter.cqrs.CqrsCommandPresenter;
import ntut.csie.sslab.ddd.model.common.DateProvider;
import ntut.csie.sslab.ddd.usecase.cqrs.CqrsCommandOutput;
import ntut.csie.sslab.ddd.usecase.cqrs.ExitCode;
import ntut.csie.sslab.kanban.board.entity.Board;
import ntut.csie.sslab.kanban.usecase.AbstractSpringBootJpaTest;
import ntut.csie.sslab.kanban.workflow.usecase.port.in.create.CreateWorkflowInput;
import ntut.csie.sslab.kanban.workflow.usecase.port.in.create.CreateWorkflowUseCase;
import ntut.csie.sslab.kanban.workflow.entity.Workflow;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;


public class CreateWorkflowUseCaseTest extends AbstractSpringBootJpaTest {

    @BeforeEach
    public void setUp() {
        super.setUp();
        teamId = "team_id_for_create_workflow_use_case_" + DateProvider.now();
        userId = "user_id_888";
        username = "Jack";
        boardName = "DevOps board";
    }

    @Test
    public void should_succeed_when_create_workflow() {

        String boardId = createBoard(teamId, boardName, userId);
        Board board= boardRepository.findById(boardId).get();
        CreateWorkflowUseCase createWorkflowUseCase = newCreateWorkflowUseCase();

        CreateWorkflowInput createWorkflowInput = new CreateWorkflowInput();
        CqrsCommandPresenter createWorkflowOutput = CqrsCommandPresenter.newInstance();

        createWorkflowInput.setName("workflow");
        createWorkflowInput.setBoardId(boardId);
        createWorkflowInput.setUserId(userId);
        createWorkflowInput.setUsername(username);

        CqrsCommandOutput output = createWorkflowUseCase.execute(createWorkflowInput);

        assertNotNull(output.getId());
        assertEquals(ExitCode.SUCCESS, output.getExitCode());

        Workflow workflow = workflowRepository.findById(output.getId()).get();
        assertEquals("workflow", workflow.getName());
        assertEquals(boardId, workflow.getBoardId());
        assertEquals(0, workflow.getStages().size());
    }
}
