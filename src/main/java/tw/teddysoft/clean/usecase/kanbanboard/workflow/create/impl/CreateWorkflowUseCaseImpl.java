package tw.teddysoft.clean.usecase.kanbanboard.workflow.create.impl;

import tw.teddysoft.clean.domain.model.kanbanboard.workflow.Workflow;
import tw.teddysoft.clean.usecase.kanbanboard.workflow.WorkflowRepository;
import tw.teddysoft.clean.usecase.kanbanboard.workflow.create.CreateWorkflowInput;
import tw.teddysoft.clean.usecase.kanbanboard.workflow.create.CreateWorkflowOutput;
import tw.teddysoft.clean.usecase.kanbanboard.workflow.create.CreateWorkflowUseCase;

public class CreateWorkflowUseCaseImpl implements CreateWorkflowUseCase {

    private WorkflowRepository repository;

    public CreateWorkflowUseCaseImpl(WorkflowRepository repository) {
        this.repository = repository;
    }


    public static CreateWorkflowInput createInput(){
        return new CreateWorkflowInputImpl();
    }

    @Override
    public void execute(CreateWorkflowInput input, CreateWorkflowOutput output) {

        Workflow workflow = new Workflow(input.getWorkflowName(), input.getBoardId());

        repository.save(workflow);

        output.setWorkflowId(workflow.getId());
        output.setWorkflowName(workflow.getTitle());
    }


    private static class CreateWorkflowInputImpl implements CreateWorkflowInput{

        private String workflowName;
        private String boardId;

        public String getWorkflowName() {
            return workflowName;
        }

        public void setWorkflowName(String workflowName) {
            this.workflowName = workflowName;
        }

        public String getBoardId() {
            return boardId;
        }

        public void setBoardId(String boardId) {
            this.boardId = boardId;
        }
    }

}
