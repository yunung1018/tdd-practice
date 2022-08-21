package ntut.csie.sslab.kanban.workflow.usecase.service;

import ntut.csie.sslab.ddd.usecase.DomainEventBus;
import ntut.csie.sslab.ddd.usecase.cqrs.CqrsOutput;
import ntut.csie.sslab.ddd.usecase.cqrs.ExitCode;
import ntut.csie.sslab.kanban.workflow.entity.LaneType;
import ntut.csie.sslab.kanban.workflow.entity.Workflow;
import ntut.csie.sslab.kanban.common.ClientBoardContentMightExpire;
import ntut.csie.sslab.kanban.workflow.usecase.port.in.lane.stage.create.CreateStageInput;
import ntut.csie.sslab.kanban.workflow.usecase.port.in.lane.stage.create.CreateStageUseCase;
import ntut.csie.sslab.kanban.workflow.usecase.port.out.WorkflowRepository;

public class CreateStageService implements CreateStageUseCase {
    private final WorkflowRepository workflowRepository;
    private final DomainEventBus domainEventBus;

    public CreateStageService(WorkflowRepository workflowRepository,
                              DomainEventBus domainEventBus) {

        this.workflowRepository = workflowRepository;
        this.domainEventBus = domainEventBus;
    }

    @Override
    public CqrsOutput execute(CreateStageInput input) {
        Workflow workflow= workflowRepository.findById(input.getWorkflowId()).orElse(null);
        CqrsOutput output = CqrsOutput.create();

        if (null == workflow){
            output.setId(input.getWorkflowId())
                    .setExitCode(ExitCode.FAILURE)
                    .setMessage("Create stage failed: workflow not found, workflow id = " + input.getWorkflowId());
            domainEventBus.post(new ClientBoardContentMightExpire(input.getBoardId()));
            return output;
        }

       String stageId= workflow.createStage(input.getName(),
                                             input.getWipLimit(),
                                             LaneType.valueOf(input.getLaneType()),
                                             input.getParentId(),
                                             input.getUserId(),
                                             input.getUsername(),
                                             input.getBoardId());

        workflowRepository.save(workflow);
        domainEventBus.postAll(workflow);

        return output.setId(stageId).setExitCode(ExitCode.SUCCESS);
    }
}
