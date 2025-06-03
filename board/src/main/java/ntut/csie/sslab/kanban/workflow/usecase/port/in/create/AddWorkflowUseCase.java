package ntut.csie.sslab.kanban.workflow.usecase.port.in.create;
import ntut.csie.sslab.kanban.workflow.entity.Workflow1;
import ntut.csie.sslab.kanban.workflow.entity.event.WorkflowRenamed;
import ntut.csie.sslab.kanban.workflow.usecase.AddWorkflowOutput;
import ntut.csie.sslab.kanban.workflow.usecase.port.out.WorkflowRepository1;
public class AddWorkflowUseCase {

    
    private WorkflowRepository1 workflowRepository;

    public AddWorkflowUseCase(WorkflowRepository1 workflowRepository) {
        this.workflowRepository = workflowRepository;
    }
    public AddWorkflowOutput execute(AddWorkflowInput input) {
        Workflow1 workflow = new Workflow1(input.getWorkflowId(), input.getWorkflowName(), input.getBoardId());
        workflowRepository.save(workflow);
        return new AddWorkflowOutput(workflow.getId(), workflow.getBoardId(), workflow.getName());
    }
}