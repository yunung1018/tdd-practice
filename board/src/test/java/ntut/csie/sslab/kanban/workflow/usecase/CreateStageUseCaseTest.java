package ntut.csie.sslab.kanban.workflow.usecase;

import ntut.csie.sslab.ddd.usecase.cqrs.CqrsOutput;
import ntut.csie.sslab.ddd.usecase.cqrs.ExitCode;
import ntut.csie.sslab.kanban.workflow.entity.Workflow;
import ntut.csie.sslab.kanban.common.usecase.AbstractSpringBootJpaTest;
import ntut.csie.sslab.kanban.workflow.usecase.port.in.lane.stage.create.CreateStageInput;
import ntut.csie.sslab.kanban.workflow.usecase.port.in.lane.stage.create.CreateStageUseCase;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;


public class CreateStageUseCaseTest extends AbstractSpringBootJpaTest {

    @BeforeEach
    public void setUp() {
        super.setUp();

        teamId = "Team id for crate stage";
        boardName = "Board for create stage";
        userId = "User id for create stage";
        username = "Teddy Chen";
    }

    @Test
    public void should_succeed_when_create_root_stage() {

        String boardId = "board_id_for_create_stage";
        String workflowName = "first workflow";
        String workflowId = createWorkflow(boardId, workflowName, userId, username);

        Workflow workflow = workflowRepository.findById(workflowId).get();
        assertNotNull(workflow);
        assertEquals(workflowName, workflow.getName());
        assertEquals(0, workflow.getStages().size());

        CreateStageUseCase createStageUseCase = newCreateStageUseCase();
        CreateStageInput input = new CreateStageInput();

        input.setWorkflowId(workflowId);
        input.setParentId("-1");
        input.setName("todo");
        input.setWipLimit(2);
        input.setLaneType("Standard");
        input.setUserId(userId);
        input.setUsername(username);
        input.setBoardId(boardId);

        CqrsOutput output = createStageUseCase.execute(input);

        assertNotNull(output.getId());
        assertEquals(ExitCode.SUCCESS, output.getExitCode());

        workflow = workflowRepository.findById(workflowId).get();
        assertEquals(1 , workflow.getStages().size());
        assertNotNull(workflow.getStages().get(0).getId(), output.getId());
        Assertions.assertEquals(1, workflowRepository.findById(workflowId).get().getStages().size());
    }
}
