package tw.teddysoft.clean.usecase.card.commit.impl;

import tw.teddysoft.clean.domain.model.card.Card;
import tw.teddysoft.clean.domain.model.kanbanboard.workflow.Workflow;
import tw.teddysoft.clean.usecase.card.CardRepository;
import tw.teddysoft.clean.usecase.card.commit.CommitCardInput;
import tw.teddysoft.clean.usecase.card.commit.CommitCardOutput;
import tw.teddysoft.clean.usecase.card.commit.CommitCardUseCase;
import tw.teddysoft.clean.usecase.kanbanboard.workflow.WorkflowRepository;

public class CommitCardUseCaseImpl implements CommitCardUseCase {

    private WorkflowRepository workflowRepository;
    private CardRepository cardRepository;

    public CommitCardUseCaseImpl(WorkflowRepository workflowRepository,
                                 CardRepository cardRepository) {
        this.workflowRepository = workflowRepository;
        this.cardRepository = cardRepository;
    }

    @Override
    public void execute(CommitCardInput input, CommitCardOutput output) {

        Workflow workflow = workflowRepository.findById(input.getWorkflowId());
        workflow.commitCard(input.getCardId(), input.getLaneId());

        Card card = cardRepository.findById(input.getCardId());
        card.moveTo(input.getWorkflowId(), input.getLaneId());

        workflowRepository.save(workflow);
        cardRepository.save(card);



//        Card card = cardRepository.findById(input.getCardId());
//
//        Stage fromStage = workflowRepository.findById(card.getStageId());
//        Stage toStage = workflowRepository.findById(input.getToStageId());
//        SwimLane fromSwimLane = fromStage.getSwimLaneById(card.getSwimLaneId());
//        SwimLane toSwimLane = toStage.getSwimLaneById(input.getToSwimLaneId());
//
//        try {
//            fromStage.uncommitWorkItemFromSwimLaneById(fromSwimLane.getId(), input.getCardId());
//            toStage.commitWorkItemToSwimLaneById(input.getToSwimLaneId(), input.getCardId());
////            toSwimLane.commitWorkItemById(input.getWorkItemId());
//
//            card.moveTo(toSwimLane.getStageId(), toSwimLane.getMiniStageId(), toSwimLane.getId());
//
//            cardRepository.save(card);
//            workflowRepository.save(fromStage);
//            workflowRepository.save(toStage);
//
//        } catch (WipLimitExceedException e) {
//            throw new RuntimeException(e.getMessage());
//        }
    }


    public static CommitCardInput createInput() {
        return new CommitCardInputImpl();
    }

    private static class CommitCardInputImpl implements CommitCardInput {
        private String workflowId;
        private String laneId;
        private String cardId;

        @Override
        public String getWorkflowId() {
            return workflowId;
        }

        @Override
        public CommitCardInput setWorkflowId(String workflowId) {
            this.workflowId = workflowId;
            return this;
        }

        @Override
        public String getLaneId() {
            return laneId;
        }

        @Override
        public CommitCardInput setLaneId(String laneId) {
            this.laneId = laneId;
            return this;
        }

        @Override
        public String getCardId() {
            return cardId;
        }

        @Override
        public CommitCardInput setCardId(String cardId) {
            this.cardId = cardId;
            return this;
        }
    }

}
