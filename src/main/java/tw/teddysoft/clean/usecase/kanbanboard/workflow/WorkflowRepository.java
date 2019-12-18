package tw.teddysoft.clean.usecase.kanbanboard.workflow;

import tw.teddysoft.clean.domain.model.kanbanboard.workflow.Workflow;

import java.util.List;

public interface WorkflowRepository {

    List<Workflow> findAll();

    Workflow findById(String id);

    Workflow findFirstByName(String name);

    Workflow save(Workflow arg);

    boolean remove(Workflow arg);

    List<Workflow> findByBoardId(String boardId);
}
