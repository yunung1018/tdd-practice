package ntut.csie.sslab.kanban.board.adapter.out.repository;

import ntut.csie.sslab.kanban.board.entity.CommittedWorkflow;

public class BoardWorkflowMapper {

    public CommittedWorkflowData transformToBoardWorkflowData(CommittedWorkflow committedWorkflow) {
        CommittedWorkflowData committedWorkflowData = new CommittedWorkflowData();
        committedWorkflowData.setWorkflowId(committedWorkflow.getWorkflowId());
        committedWorkflowData.setBoardId(committedWorkflow.getBoardId());
        committedWorkflowData.setOrder(committedWorkflow.getOrder());
        return committedWorkflowData;
    }
}
