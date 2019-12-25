package tw.teddysoft.clean.usecase.card.delete.impl;

import tw.teddysoft.clean.domain.model.DomainEventBus;
import tw.teddysoft.clean.domain.model.card.Card;
import tw.teddysoft.clean.domain.model.card.event.CardDeleted;
import tw.teddysoft.clean.usecase.card.CardRepository;
import tw.teddysoft.clean.usecase.card.delete.DeleteCardInput;
import tw.teddysoft.clean.usecase.card.delete.DeleteCardOutput;
import tw.teddysoft.clean.usecase.card.delete.DeleteCardUseCase;

public class DeleteCardUseCaseImpl implements DeleteCardUseCase {

    private final CardRepository cardRepository;
    private final DomainEventBus eventBus;

    public DeleteCardUseCaseImpl(CardRepository cardRepository, DomainEventBus eventBus) {
        this.cardRepository = cardRepository;
        this.eventBus = eventBus;
    }


    @Override
    public void execute(DeleteCardInput input, DeleteCardOutput output) {

        Card card = cardRepository.findById(input.getCardId());
        if (cardRepository.remove(card))
            eventBus.post(new CardDeleted(card.getId(), card.getName(), input.getWorkflowId(), input.getLaneId()));
        else
            throw new RuntimeException("Delete card failed, id = '" + input.getCardId() + "'");
    }

    @Override
    public DeleteCardInput createInput() {
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
