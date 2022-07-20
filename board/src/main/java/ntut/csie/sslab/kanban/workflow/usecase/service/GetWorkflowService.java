package ntut.csie.sslab.kanban.workflow.usecase.get.impl;

import ntut.csie.sslab.ddd.usecase.cqrs.ExitCode;
import ntut.csie.sslab.kanban.workflow.entity.Workflow;
import ntut.csie.sslab.kanban.workflow.usecase.WorkflowsMapper;
import ntut.csie.sslab.kanban.workflow.usecase.WorkflowRepository;
import ntut.csie.sslab.kanban.workflow.usecase.get.in.GetWorkflowInput;
import ntut.csie.sslab.kanban.workflow.usecase.get.in.GetWorkflowOutput;
import ntut.csie.sslab.kanban.workflow.usecase.get.in.GetWorkflowUseCase;

public class GetWorkflowUseCaseImpl implements GetWorkflowUseCase {
    private final WorkflowRepository workflowRepository;

    public GetWorkflowUseCaseImpl(WorkflowRepository workflowRepository){
        this.workflowRepository= workflowRepository;
    }
    @Override
    public GetWorkflowOutput execute(GetWorkflowInput input) {
        Workflow workflow= workflowRepository.findById(input.getWorkflowId()).orElse(null);
        GetWorkflowOutput output = new GetWorkflowOutput();

        if (null == workflow){
            output.setExitCode(ExitCode.FAILURE)
                    .setMessage("Get workflow failed: workflow not found, workflow id = " + input.getWorkflowId());
            return output;
        }

        output.setWorkflowDto(WorkflowsMapper.toDto(workflow));
        return output;
    }
}
