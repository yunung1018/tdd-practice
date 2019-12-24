package tw.teddysoft.clean.usecase.card.delete.impl;

import tw.teddysoft.clean.domain.model.DomainEventBus;
import tw.teddysoft.clean.domain.model.DomainEventPublisher;
import tw.teddysoft.clean.domain.model.card.Card;
import tw.teddysoft.clean.domain.model.card.event.CardCreated;
import tw.teddysoft.clean.domain.model.card.event.CardDeleted;
import tw.teddysoft.clean.domain.model.kanbanboard.workflow.Workflow;
import tw.teddysoft.clean.usecase.card.CardRepository;
import tw.teddysoft.clean.usecase.card.create.CreateCardInput;
import tw.teddysoft.clean.usecase.card.create.CreateCardOutput;
import tw.teddysoft.clean.usecase.card.create.CreateCardUseCase;
import tw.teddysoft.clean.usecase.card.delete.DeleteCardInput;
import tw.teddysoft.clean.usecase.card.delete.DeleteCardOutput;
import tw.teddysoft.clean.usecase.card.delete.DeleteCardUseCase;
import tw.teddysoft.clean.usecase.kanbanboard.workflow.WorkflowRepository;

public class DeleteCardUseCaseImpl implements DeleteCardUseCase {

    private final CardRepository cardRepository;
    private final WorkflowRepository workflowRepository;
    private final DomainEventBus eventBus;

    public DeleteCardUseCaseImpl(CardRepository cardRepository, WorkflowRepository workflowRepository, DomainEventBus eventBus) {
        this.cardRepository = cardRepository;
        this.workflowRepository = workflowRepository;
        this.eventBus = eventBus;
    }


    @Override
    public void execute(DeleteCardInput input, DeleteCardOutput output) {

        Card card = cardRepository.findById(input.getCardId());
        cardRepository.remove(card);

//        DomainEventPublisher
//                .instance()
//                .publish(new CardDeleted(
//                        card.getId(),
//                        card.getName()));
        eventBus.post(new CardDeleted(card.getId(), card.getName(), input.getWorkflowId(), input.getLaneId()));


//        Workflow workflow = workflowRepository.findById(input.getWorkflowId());
//        workflow.uncommitCard(input.getCardId(), input.getLaneId());
//        workflowRepository.save(workflow);
    }

    public static DeleteCardInput createInput() {
        return new DeleteCardInputImpl();
    }

    private static class DeleteCardInputImpl implements DeleteCardInput {

        private String workflowId;
        private String laneId;
        private String cardId;

        @Override
        public DeleteCardInput setLaneId(String laneId) {
            this.laneId = laneId;
            return this;
        }

        @Override
        public DeleteCardInput setWorkflowId(String workflowId) {
            this.workflowId = workflowId;
            return this;
        }

        @Override
        public DeleteCardInput setCardId(String cardId) {
            this.cardId = cardId;
            return this;
        }

        @Override
        public String getLaneId() {
            return laneId;
        }

        @Override
        public String getWorkflowId() {
            return workflowId;
        }

        @Override
        public String getCardId() {
            return cardId;
        }
    }
}
