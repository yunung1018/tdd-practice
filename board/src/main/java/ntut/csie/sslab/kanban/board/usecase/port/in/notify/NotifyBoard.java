package ntut.csie.sslab.kanban.board.usecase.port.in.notify;

import ntut.csie.sslab.ddd.usecase.DomainEventBus;
import ntut.csie.sslab.kanban.board.entity.Board;
import ntut.csie.sslab.kanban.board.usecase.port.out.BoardRepository;
import ntut.csie.sslab.kanban.workflow.entity.event.WorkflowCreated;
import ntut.csie.sslab.kanban.workflow.entity.event.WorkflowDeleted;

import java.util.Optional;

public interface NotifyBoard {

    void whenWorkflowCreated(WorkflowCreated workflowCreated);

    void whenWorkflowDeleted(WorkflowDeleted workflowDeleted);

}
