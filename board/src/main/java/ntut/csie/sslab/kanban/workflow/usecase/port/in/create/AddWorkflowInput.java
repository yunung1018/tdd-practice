package ntut.csie.sslab.kanban.workflow.usecase.port.in.create;
public class AddWorkflowInput {
    public final String boardId;
    public final String workflowName;

    public AddWorkflowInput(String boardId, String workflowName) {
        this.boardId = boardId;
        this.workflowName = workflowName;
    }
}