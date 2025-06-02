package ntut.csie.sslab.kanban.workflow.usecase;

public class AddWorkflowOutput {
    private String id;
    private String boardId;
    private String workflowName;

    public AddWorkflowOutput(String id, String boardId, String workflowName) {
        this.id = id;
        this.boardId = boardId;
        this.workflowName = workflowName;
    }
    public String getWorkflowId() {
        return id;
    }
    public String getBoardId() {
        return boardId;
    }
    public String getWorkflowName() {
        return workflowName;
    }
}
