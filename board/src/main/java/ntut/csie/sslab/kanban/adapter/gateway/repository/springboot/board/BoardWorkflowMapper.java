package ntut.csie.sslab.kanban.adapter.gateway.repository.springboot.board;

import ntut.csie.sslab.kanban.entity.model.board.CommittedWorkflow;

public class BoardWorkflowMapper {

    public CommittedWorkflowData transformToBoardWorkflowData(CommittedWorkflow committedWorkflow) {
        CommittedWorkflowData committedWorkflowData = new CommittedWorkflowData();
        committedWorkflowData.setWorkflowId(committedWorkflow.getWorkflowId());
        committedWorkflowData.setBoardId(committedWorkflow.getBoardId());
        committedWorkflowData.setOrder(committedWorkflow.getOrder());
        return committedWorkflowData;
    }
}
