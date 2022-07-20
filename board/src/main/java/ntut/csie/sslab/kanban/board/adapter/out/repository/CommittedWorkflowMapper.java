package ntut.csie.sslab.kanban.board.adapter.out;

import ntut.csie.sslab.kanban.board.entity.CommittedWorkflow;

import java.util.ArrayList;
import java.util.List;

public class CommittedWorkflowMapper {

    public static CommittedWorkflowData toData(CommittedWorkflow committedWorkflow){
        CommittedWorkflowData data = new CommittedWorkflowData(
                committedWorkflow.getBoardId(),
                committedWorkflow.getWorkflowId(),
                committedWorkflow.getOrder()
        );
        return data;
    }

    public static List<CommittedWorkflowData> toData(List<CommittedWorkflow> committedWorkflows){
        List<CommittedWorkflowData> datas = new ArrayList<>();
        committedWorkflows.forEach( x -> datas.add(toData(x)));
        return datas;
    }

}
