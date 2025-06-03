package ntut.csie.sslab.kanban.workflow.usecase;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import ntut.csie.sslab.kanban.workflow.entity.Workflow1;
import ntut.csie.sslab.kanban.workflow.usecase.AddWorkflowOutput;
import ntut.csie.sslab.kanban.workflow.usecase.port.in.create.AddWorkflowInput;
import ntut.csie.sslab.kanban.workflow.usecase.service.AddWorkflowService;
import ntut.csie.sslab.kanban.workflow.usecase.port.in.create.AddWorkflowUseCase;
import ntut.csie.sslab.kanban.workflow.usecase.port.out.WorkflowRepository1;
public class addWorkflowUseCaseTest {

    @Test 
    void should_create_workflow() {
        // d
        AddWorkflowInput input = new AddWorkflowInput("board-id-1", "New Workflow", "workflow-id-1");
        // input.setWorkflowId("workflow-id-1");
        // input.setWorkflowName("New Workflow");
        // input.setBoardId("board-id-1");
        WorkflowRepository1 repository = new WorkflowRepository1();
        AddWorkflowUseCase useCase = new AddWorkflowUseCase(repository);
        
        AddWorkflowOutput output = useCase.execute(input);
        // When
        Workflow1 savedWorkflow = repository.findBYId(output.getId());
        // Then
        assertEquals("workflow-id-1", output.getId());
        assertEquals("board-id-1", output.getBoardId());
        assertEquals("New Workflow", output.getWorkflowName());
    }
}