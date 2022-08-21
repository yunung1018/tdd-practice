package ntut.csie.sslab.kanban.workflow.usecase.service;

import ntut.csie.sslab.ddd.usecase.DomainEventBus;
import ntut.csie.sslab.ddd.usecase.cqrs.CqrsOutput;
import ntut.csie.sslab.ddd.usecase.cqrs.ExitCode;
import ntut.csie.sslab.kanban.workflow.entity.Workflow;
import ntut.csie.sslab.kanban.common.ClientBoardContentMightExpire;
import ntut.csie.sslab.kanban.workflow.usecase.port.in.lane.rename.RenameLaneInput;
import ntut.csie.sslab.kanban.workflow.usecase.port.in.lane.rename.RenameLaneUseCase;
import ntut.csie.sslab.kanban.workflow.usecase.port.out.WorkflowRepository;

public class RenameLaneService implements RenameLaneUseCase {

    private WorkflowRepository workflowRepository;
    private DomainEventBus domainEventBus;

    public RenameLaneService(WorkflowRepository workflowRepository, DomainEventBus domainEventBus) {
        this.workflowRepository = workflowRepository;
        this.domainEventBus = domainEventBus;
    }

    @Override
    public CqrsOutput execute(RenameLaneInput input) {
        Workflow workflow= workflowRepository.findById(input.getWorkflowId()).orElse(null);
        CqrsOutput output = CqrsOutput.create();

        if (null == workflow){
            output.setId(input.getWorkflowId())
                    .setExitCode(ExitCode.FAILURE)
                    .setMessage("Rename lane failed: workflow not found, workflow id = " + input.getWorkflowId());
            domainEventBus.post(new ClientBoardContentMightExpire(input.getBoardId()));
            return output;
        }
        workflow.renameLane(input.getLaneId(), input.getNewName(), input.getUserId(), input.getUsername());

        workflowRepository.save(workflow);
        domainEventBus.postAll(workflow);

        return output.setId(input.getLaneId()).setExitCode(ExitCode.SUCCESS);
    }
}
