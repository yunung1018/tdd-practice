package ntut.csie.sslab.kanban.workflow.usecase.service;

import ntut.csie.sslab.ddd.usecase.DomainEventBus;
import ntut.csie.sslab.kanban.workflow.entity.Workflow;
import ntut.csie.sslab.kanban.workflow.entity.event.CardMoved;
import ntut.csie.sslab.kanban.workflow.usecase.port.in.notify.NotifyWorkflow;
import ntut.csie.sslab.kanban.workflow.usecase.port.out.WorkflowRepository;
import ntut.csie.sslab.kanban.card.entity.event.CardCreated;
import ntut.csie.sslab.kanban.card.entity.event.CardDeleted;

public class NotifyWorkflowService implements NotifyWorkflow {
    private final WorkflowRepository workflowRepository;
    private final DomainEventBus domainEventBus;
    public NotifyWorkflowService(WorkflowRepository workflowRepository,
                                 DomainEventBus domainEventBus) {
        this.workflowRepository = workflowRepository;
        this.domainEventBus = domainEventBus;
    }

    @Override
    public void whenCardCreated(CardCreated cardCreated) {
        Workflow workflow = workflowRepository.findById(cardCreated.workflowId()).get();
        int order = workflow.getLaneById(cardCreated.laneId()).get().getCommittedCards().size();
        workflow.commitCardInLane(
                                cardCreated.cardId(),
                                cardCreated.laneId(),
                                order,
                                cardCreated.userId(),
                                cardCreated.username(),
                                cardCreated.boardId());

        workflowRepository.save(workflow);
        domainEventBus.postAll(workflow);
    }

    @Override
    public void whenCardDeleted(CardDeleted cardDeleted) {
        Workflow workflow = workflowRepository.findById(cardDeleted.workflowId()).get();
        workflow.uncommitCardInLane(
                cardDeleted.cardId(),
                cardDeleted.laneId(),
                cardDeleted.userId(),
                cardDeleted.username(),
                cardDeleted.boardId()
        );

        workflowRepository.save(workflow);
        domainEventBus.postAll(workflow);
    }

    @Override
    public void whenCardMoved(CardMoved cardMoved) {
        Workflow workflow = workflowRepository.findById(cardMoved.workflowId()).get();
        workflow.uncommitCardInLane(
                cardMoved.cardId(),
                cardMoved.originalLaneId(),
                cardMoved.userId(),
                cardMoved.username(),
                cardMoved.boardId());
        workflow.commitCardInLane(
                cardMoved.cardId(),
                cardMoved.newLaneId(),
                cardMoved.order(),
                cardMoved.userId(),
                cardMoved.username(),
                cardMoved.boardId());

        workflowRepository.save(workflow);
        domainEventBus.postAll(workflow);
    }
}
