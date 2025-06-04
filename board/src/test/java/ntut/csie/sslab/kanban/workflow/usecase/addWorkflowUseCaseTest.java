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
        AddWorkflowInput input = new AddWorkflowInput();
        input.setWorkflowId("workflow-id-1");
        input.setWorkflowName("New Workflow");
        input.setBoardId("board-id-1");
        WorkflowRepository1 repository = new WorkflowRepository1();
        AddWorkflowUseCase useCase = new AddWorkflowUseCase(repository);
        
        AddWorkflowOutput output = useCase.execute(input);
        // When
        Workflow1 savedWorkflow = repository.findById(output.getId());
        // Then
        assertNotNull(savedWorkflow, "Workflow should be saved and found in repository"); // 確保物件不是 null
        assertEquals("workflow-id-1", savedWorkflow.getId());
        assertEquals("board-id-1", savedWorkflow.getBoardId()); 
    }
}