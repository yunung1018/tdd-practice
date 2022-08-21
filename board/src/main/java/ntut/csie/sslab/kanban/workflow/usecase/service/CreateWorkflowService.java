package ntut.csie.sslab.kanban.workflow.usecase.service;

import ntut.csie.sslab.ddd.usecase.DomainEventBus;
import ntut.csie.sslab.ddd.usecase.cqrs.CqrsOutput;
import ntut.csie.sslab.ddd.usecase.cqrs.ExitCode;
import ntut.csie.sslab.kanban.workflow.entity.Workflow;
import ntut.csie.sslab.kanban.workflow.entity.WorkflowBuilder;
import ntut.csie.sslab.kanban.workflow.usecase.port.out.WorkflowRepository;
import ntut.csie.sslab.kanban.workflow.usecase.port.in.create.CreateWorkflowInput;
import ntut.csie.sslab.kanban.workflow.usecase.port.in.create.CreateWorkflowUseCase;

public class CreateWorkflowService implements CreateWorkflowUseCase {
    private final WorkflowRepository workflowRepository;
    private final DomainEventBus domainEventBus;

    public CreateWorkflowService(WorkflowRepository workflowRepository, DomainEventBus domainEventBus) {
        this.workflowRepository = workflowRepository;
        this.domainEventBus = domainEventBus;
    }

    @Override
    public CqrsOutput execute(CreateWorkflowInput input) {
        Workflow workflow = WorkflowBuilder.newInstance()
                            .boardId(input.getBoardId())
                            .name(input.getName())
                            .userId(input.getUserId())
                            .username(input.getUsername())
                            .build();

        workflowRepository.save(workflow);
        domainEventBus.postAll(workflow);

        return CqrsOutput.create().setId(workflow.getWorkflowId()).setExitCode(ExitCode.SUCCESS);
    }
}
