package ntut.csie.sslab.kanban.workflow.usecase.service;

import ntut.csie.sslab.kanban.workflow.usecase.AddWorkflowOutput;
import ntut.csie.sslab.kanban.workflow.usecase.port.in.create.AddWorkflowInput;
import ntut.csie.sslab.kanban.workflow.usecase.port.in.create.AddWorkflowUseCase;

public class AddWorkflowService implements AddWorkflowUseCase {
    public AddWorkflowOutput execute(AddWorkflowInput input) {
        return new AddWorkflowOutput("workflow-id-1", input.boardId, input.workflowName);
    }
}