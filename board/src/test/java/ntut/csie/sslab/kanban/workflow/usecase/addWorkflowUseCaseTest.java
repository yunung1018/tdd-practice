package ntut.csie.sslab.kanban.workflow.usecase;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import ntut.csie.sslab.kanban.workflow.usecase.port.in.create.AddWorkflowInput;
import ntut.csie.sslab.kanban.workflow.usecase.service.AddWorkflowService;
import ntut.csie.sslab.kanban.workflow.usecase.port.in.create.AddWorkflowUseCase;
public class addWorkflowUseCaseTest {

    @Test 
    void should_create_workflow() {
        // Given
        AddWorkflowUseCase useCase = new AddWorkflowService();
        AddWorkflowInput input = new AddWorkflowInput("board-id-1", "New Workflow");

        // When
        AddWorkflowOutput output = useCase.execute(input);

        // Then
        assertEquals("workflow-id-1", output.getWorkflowId());
        assertEquals("board-id-1", output.getBoardId());
        assertEquals("New Workflow", output.getWorkflowName());
    }
}