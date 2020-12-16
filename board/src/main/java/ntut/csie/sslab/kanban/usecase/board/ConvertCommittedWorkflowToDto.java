package ntut.csie.sslab.kanban.usecase.board;

import ntut.csie.sslab.kanban.entity.model.board.CommittedWorkflow;

import java.util.ArrayList;
import java.util.List;

public class ConvertCommittedWorkflowToDto {
    public static CommittedWorkflowDto transform(CommittedWorkflow committedWorkflow){
       CommittedWorkflowDto committedWorkflowDto = new CommittedWorkflowDto();
       committedWorkflowDto.setBoardId(committedWorkflow.getBoardId());
       committedWorkflowDto.setWorkflowId(committedWorkflow.getWorkflowId());
       committedWorkflowDto.setOrder(committedWorkflow.getOrder());
       return committedWorkflowDto;
    }
    public static List<CommittedWorkflowDto> transform(List<CommittedWorkflow> committedWorkflows){
        List<CommittedWorkflowDto> committedWorkflowDtos = new ArrayList<>();
        for(CommittedWorkflow committedWorkflow :committedWorkflows){
            committedWorkflowDtos.add(transform(committedWorkflow));
        }
        return committedWorkflowDtos;
    }

}
