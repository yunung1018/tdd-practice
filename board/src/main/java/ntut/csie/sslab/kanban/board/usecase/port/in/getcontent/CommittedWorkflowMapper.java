package ntut.csie.sslab.kanban.board.usecase.port.in;

import ntut.csie.sslab.kanban.board.entity.CommittedWorkflow;

import java.util.ArrayList;
import java.util.List;

public class CommittedWorkflowMapper {

    public static CommittedWorkflowDto toDto(CommittedWorkflow committedWorkflow){
       CommittedWorkflowDto committedWorkflowDto = new CommittedWorkflowDto();
       committedWorkflowDto.setBoardId(committedWorkflow.getBoardId());
       committedWorkflowDto.setWorkflowId(committedWorkflow.getWorkflowId());
       committedWorkflowDto.setOrder(committedWorkflow.getOrder());
       return committedWorkflowDto;
    }
    public static List<CommittedWorkflowDto> toDto(List<CommittedWorkflow> committedWorkflows){
        List<CommittedWorkflowDto> committedWorkflowDtos = new ArrayList<>();
        for(CommittedWorkflow committedWorkflow :committedWorkflows){
            committedWorkflowDtos.add(toDto(committedWorkflow));
        }
        return committedWorkflowDtos;
    }

}
