package ntut.csie.sslab.kanban.workflow.usecase.service;

import ntut.csie.sslab.ddd.usecase.cqrs.ExitCode;
import ntut.csie.sslab.kanban.workflow.entity.Workflow;
import ntut.csie.sslab.kanban.workflow.usecase.port.in.get.WorkflowsMapper;
import ntut.csie.sslab.kanban.workflow.usecase.port.out.WorkflowRepository;
import ntut.csie.sslab.kanban.workflow.usecase.port.in.get.GetWorkflowInput;
import ntut.csie.sslab.kanban.workflow.usecase.port.in.get.GetWorkflowOutput;
import ntut.csie.sslab.kanban.workflow.usecase.port.in.get.GetWorkflowUseCase;

public class GetWorkflowService implements GetWorkflowUseCase {
    private final WorkflowRepository workflowRepository;

    public GetWorkflowService(WorkflowRepository workflowRepository){
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
