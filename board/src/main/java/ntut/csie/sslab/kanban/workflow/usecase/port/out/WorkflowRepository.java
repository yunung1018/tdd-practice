package ntut.csie.sslab.kanban.workflow.usecase.port.out;

import ntut.csie.sslab.kanban.workflow.entity.Workflow;
import ntut.csie.sslab.ddd.usecase.AbstractRepository;

import java.util.List;

public interface WorkflowRepository extends AbstractRepository<Workflow, String> {

    List<Workflow> getWorkflowsByBoardId(String boardId);

}
