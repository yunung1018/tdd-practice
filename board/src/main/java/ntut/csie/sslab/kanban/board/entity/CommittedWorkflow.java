package ntut.csie.sslab.kanban.board.entity;

import ntut.csie.sslab.ddd.model.ValueObject;

public class CommittedWorkflow implements ValueObject {
    private final String boardId;
    private final String workflowId;
    private final int order;

    public CommittedWorkflow(String boardId, String workflowId, int order) {
        this.boardId = boardId;
        this.workflowId = workflowId;
        this.order = order;
    }

    public String getBoardId() {
        return boardId;
    }

    public String getWorkflowId() {
        return workflowId;
    }

    public int getOrder() {
        return order;
    }

}
