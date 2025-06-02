package ntut.csie.sslab.kanban.workflow.usecase.port.in.create;
import ntut.csie.sslab.kanban.workflow.usecase.AddWorkflowOutput;
public interface AddWorkflowUseCase {
    AddWorkflowOutput execute(AddWorkflowInput input);
}