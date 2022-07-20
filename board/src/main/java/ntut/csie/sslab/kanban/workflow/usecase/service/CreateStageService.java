package ntut.csie.sslab.kanban.workflow.usecase.lane.stage.create.impl;

import ntut.csie.sslab.ddd.usecase.DomainEventBus;
import ntut.csie.sslab.ddd.usecase.cqrs.CqrsCommandOutput;
import ntut.csie.sslab.ddd.usecase.cqrs.ExitCode;
import ntut.csie.sslab.kanban.workflow.entity.LaneType;
import ntut.csie.sslab.kanban.workflow.entity.Workflow;
import ntut.csie.sslab.kanban.common.ClientBoardContentMightExpire;
import ntut.csie.sslab.kanban.workflow.usecase.lane.stage.create.in.CreateStageInput;
import ntut.csie.sslab.kanban.workflow.usecase.lane.stage.create.in.CreateStageUseCase;
import ntut.csie.sslab.kanban.workflow.usecase.WorkflowRepository;

public class CreateStageUseCaseImpl implements CreateStageUseCase {
    private final WorkflowRepository workflowRepository;
    private final DomainEventBus domainEventBus;

    public CreateStageUseCaseImpl(WorkflowRepository workflowRepository,
                                  DomainEventBus domainEventBus) {

        this.workflowRepository = workflowRepository;
        this.domainEventBus = domainEventBus;
    }

    @Override
    public CqrsCommandOutput execute(CreateStageInput input) {
        Workflow workflow= workflowRepository.findById(input.getWorkflowId()).orElse(null);
        CqrsCommandOutput output = CqrsCommandOutput.create();

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
