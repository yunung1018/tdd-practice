package tw.teddysoft.clean.usecase.card.move.impl;

import tw.teddysoft.clean.domain.model.card.Card;
import tw.teddysoft.clean.domain.model.kanbanboard.workflow.Workflow;
import tw.teddysoft.clean.usecase.card.CardRepository;
import tw.teddysoft.clean.usecase.card.move.MoveCardInput;
import tw.teddysoft.clean.usecase.card.move.MoveCardOutput;
import tw.teddysoft.clean.usecase.card.move.MoveCardUseCase;
import tw.teddysoft.clean.usecase.kanbanboard.workflow.WorkflowRepository;

public class MoveCardUseCaseImpl implements MoveCardUseCase {

    private WorkflowRepository workflowRepository;
    private CardRepository cardRepository;

    public MoveCardUseCaseImpl(CardRepository cardRepository,
                               WorkflowRepository workflowRepository){
        this.workflowRepository = workflowRepository;
        this.cardRepository = cardRepository;
    }

    @Override
    public void execute(MoveCardInput input, MoveCardOutput output) {

        Workflow workflow = workflowRepository.findById(input.getWorkflowId());
        workflow.moveCard(input.getCardId(), input.getFromLaneId(), input.getToLaneId());
        workflowRepository.save(workflow);
    }


    @Override
    public MoveCardInput createInput() {
        return new MoveCardInputImpl();
    }

    private static class MoveCardInputImpl implements MoveCardInput {

        private String fromLandId;
        private String workflowId;
        private String toLaneId;
        private String cardId;

        @Override
        public MoveCardInput setCardId(String id) {
            cardId = id;
            return this;
        }

        @Override
        public MoveCardInput setWorkflowId(String id) {
            workflowId = id;
            return this;
        }

        @Override
        public MoveCardInput setToLaneId(String id) {
            toLaneId = id;
            return this;
        }

        @Override
        public MoveCardInput setFromLaneId(String id) {
            fromLandId = id;
            return this;
        }

        @Override
        public String getCardId() {
            return cardId;
        }

        @Override
        public String getWorkflowId() {
            return workflowId;
        }

        @Override
        public String getToLaneId() {
            return toLaneId;
        }

        @Override
        public String getFromLaneId() {
            return fromLandId;
        }

    }

}
