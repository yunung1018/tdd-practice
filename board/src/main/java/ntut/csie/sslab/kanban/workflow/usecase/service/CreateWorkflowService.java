package ntut.csie.sslab.kanban.workflow.usecase.create.impl;

import ntut.csie.sslab.ddd.usecase.DomainEventBus;
import ntut.csie.sslab.ddd.usecase.cqrs.CqrsCommandOutput;
import ntut.csie.sslab.ddd.usecase.cqrs.ExitCode;
import ntut.csie.sslab.kanban.workflow.entity.Workflow;
import ntut.csie.sslab.kanban.workflow.entity.WorkflowBuilder;
import ntut.csie.sslab.kanban.workflow.usecase.WorkflowRepository;
import ntut.csie.sslab.kanban.workflow.usecase.create.in.CreateWorkflowInput;
import ntut.csie.sslab.kanban.workflow.usecase.create.in.CreateWorkflowUseCase;

public class CreateWorkflowUseCaseImpl implements CreateWorkflowUseCase {
    private final WorkflowRepository workflowRepository;
    private final DomainEventBus domainEventBus;

    public CreateWorkflowUseCaseImpl(WorkflowRepository workflowRepository, DomainEventBus domainEventBus) {
        this.workflowRepository = workflowRepository;
        this.domainEventBus = domainEventBus;
    }

    @Override
    public CqrsCommandOutput execute(CreateWorkflowInput input) {
        Workflow workflow = WorkflowBuilder.newInstance()
                            .boardId(input.getBoardId())
                            .name(input.getName())
                            .userId(input.getUserId())
                            .username(input.getUsername())
                            .build();

        workflowRepository.save(workflow);
        domainEventBus.postAll(workflow);

        return CqrsCommandOutput.create().setId(workflow.getWorkflowId()).setExitCode(ExitCode.SUCCESS);
    }
}
