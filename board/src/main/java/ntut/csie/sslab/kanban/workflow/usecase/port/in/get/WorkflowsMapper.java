package ntut.csie.sslab.kanban.workflow.usecase.port.in.get;

import ntut.csie.sslab.kanban.workflow.entity.Lane;
import ntut.csie.sslab.kanban.workflow.entity.Workflow;

import java.util.ArrayList;
import java.util.List;

public class WorkflowsMapper {
    public static WorkflowDto toDto(Workflow workflow){
        WorkflowDto dto = new WorkflowDto();
        dto.setWorkflowId(workflow.getWorkflowId());
        dto.setBoardId(workflow.getBoardId());
        dto.setName(workflow.getName());
        List<LaneDto> stageModels = new ArrayList<>();
        for(Lane stage : workflow.getStages()){
            LaneDto stageModel = LaneMapper.transform(stage);
            stageModels.add(stageModel);
        }
        dto.setLanes(stageModels);
        return dto;
    }


    public static List<WorkflowDto> toDto(List<Workflow> workflows){
        List<WorkflowDto> workflowDtos = new ArrayList<>();
        for(Workflow workflow : workflows) {
            workflowDtos.add(toDto(workflow));
        }

        return workflowDtos;
    }
}
