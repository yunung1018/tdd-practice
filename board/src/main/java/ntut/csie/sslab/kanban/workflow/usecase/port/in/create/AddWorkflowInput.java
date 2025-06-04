package ntut.csie.sslab.kanban.workflow.usecase.port.in.create;
public class AddWorkflowInput {
    public  String boardId;
    public  String workflowName;
    private String workflowId; // This field is not used in the original code, but added for consistency
    public AddWorkflowInput() {
        
    }
    public AddWorkflowInput(String boardId, String workflowName, String workflowId) {
        this.boardId = boardId;
        this.workflowName = workflowName;
        this.workflowId = workflowId; // This field is not used in the original code, but added for consistency
    }

    public void setBoardId(String boardId) {
        this.boardId = boardId;
    }
    public void setWorkflowName(String workflowName) {
        this.workflowName = workflowName;
    }
    public void setWorkflowId(String workflowId) {
        this.workflowId = workflowId; // This setter is not used in the original code, but added for consistency
    }
    public String getBoardId() {
        return boardId;
    }
    public String getWorkflowName() {
        return workflowName;
    }
    public String getWorkflowId() {
        return workflowId; // This getter is not used in the original code, but added for consistency
    }
    // Removed getWorkflowId() since workflowId is not defined
}