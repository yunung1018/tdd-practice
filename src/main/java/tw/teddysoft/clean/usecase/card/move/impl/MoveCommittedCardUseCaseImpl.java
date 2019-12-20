package tw.teddysoft.clean.usecase.card.move.impl;

import tw.teddysoft.clean.usecase.kanbanboard.stage.StageRepository;
import tw.teddysoft.clean.usecase.card.CardRepository;
import tw.teddysoft.clean.usecase.card.move.MoveCommittedCardInput;
import tw.teddysoft.clean.usecase.card.move.MoveCommittedCardOutput;
import tw.teddysoft.clean.usecase.card.move.MoveCommittedCardUseCase;

public class MoveCommittedCardUseCaseImpl implements MoveCommittedCardUseCase {

    private StageRepository workflowRepository;
    private CardRepository cardRepository;

    public MoveCommittedCardUseCaseImpl(StageRepository workflowRepository,
                                        CardRepository cardRepository) {
        this.workflowRepository = workflowRepository;
        this.cardRepository = cardRepository;
    }

    @Override
    public void execute(MoveCommittedCardInput input, MoveCommittedCardOutput output) {

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


    public static MoveCommittedCardInput createInput() {
        return new MoveCommittedCardInputImpl();
    }

    private static class MoveCommittedCardInputImpl implements MoveCommittedCardInput {
        private String stageId;
        private String miniStageId;
        private String swimLaneId;
        private String workItemId;

        @Override
        public void setWorkItemId(String id) {
            workItemId = id;
        }

        @Override
        public void setToStageId(String id) {
            stageId = id;
        }

        @Override
        public void setToMiniStageId(String id) {
            miniStageId = id;
        }

        @Override
        public void setToSwimLaneId(String id) {
            swimLaneId = id;
        }

        @Override
        public String getCardId() {
            return workItemId;
        }

        @Override
        public String getToStageId() {
            return stageId;
        }

        @Override
        public String getToMiniStageId() {
            return miniStageId;
        }

        @Override
        public String getToSwimLaneId() {
            return swimLaneId;
        }
    }

}
