package tw.teddysoft.clean.usecase.card.create.impl;

import tw.teddysoft.clean.domain.model.card.Card;
import tw.teddysoft.clean.domain.model.kanbanboard.workflow.Lane;
import tw.teddysoft.clean.domain.model.kanbanboard.workflow.Workflow;
import tw.teddysoft.clean.usecase.card.CardRepository;
import tw.teddysoft.clean.usecase.card.create.CreateCardInput;
import tw.teddysoft.clean.usecase.card.create.CreateCardOutput;
import tw.teddysoft.clean.usecase.card.create.CreateCardUseCase;
import tw.teddysoft.clean.usecase.kanbanboard.workflow.WorkflowRepository;

public class CreateCardUseCaseImpl implements CreateCardUseCase {

    private final CardRepository cardRepository;
    private final WorkflowRepository workflowRepository;


    public CreateCardUseCaseImpl(CardRepository cardRepository, WorkflowRepository workflowRepository) {
        this.cardRepository = cardRepository;
        this.workflowRepository = workflowRepository;

    }


    @Override
    public void execute(CreateCardInput input, CreateCardOutput output) {
        Card card = new Card(input.getTitle());
        card.setWorkflowId(input.getWorkflowId());
        card.setLaneId(input.getLaneId());
        cardRepository.save(card);

        Workflow workflow = workflowRepository.findById(input.getWorkflowId());
        workflow.commitCard(card.getId(), input.getLaneId());
        workflowRepository.save(workflow);

        output.setId(card.getId());
    }

    public static CreateCardInput createInput() {
        return new CreateCardInputImpl();
    }

    private static class CreateCardInputImpl implements CreateCardInput {

        private String title;
        private String workflowId;
        private String laneId;

        @Override
        public void setTitle(String title) {
            this.title = title;
        }

        @Override
        public void setLaneId(String laneId) {
            this.laneId = laneId;
        }

        @Override
        public void setWorkflowId(String workflowId) {
            this.workflowId = workflowId;
        }

        @Override
        public String getTitle() {
            return title;
        }

        @Override
        public String getLaneId() {
            return laneId;
        }

        @Override
        public String getWorkflowId() {
            return workflowId;
        }
    }
}
