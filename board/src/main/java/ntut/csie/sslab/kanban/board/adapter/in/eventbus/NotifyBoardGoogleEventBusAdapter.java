package ntut.csie.sslab.kanban.board.adapter.in.eventbus;

import com.google.common.eventbus.Subscribe;
import ntut.csie.sslab.kanban.board.usecase.port.in.notify.NotifyBoard;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ntut.csie.sslab.kanban.workflow.entity.event.*;



@Component
public class NotifyBoardGoogleEventBusAdapter {

    private final NotifyBoard notifyBoard;

    @Autowired
    public NotifyBoardGoogleEventBusAdapter(NotifyBoard notifyBoard) {
        this.notifyBoard = notifyBoard;
    }

    @Subscribe
    public void whenWorkflowCreated(WorkflowCreated workflowCreated) {
        notifyBoard.whenWorkflowCreated(workflowCreated);
    }

    @Subscribe
    public void whenWorkflowDeleted(WorkflowDeleted workflowDeleted) {
        notifyBoard.whenWorkflowDeleted(workflowDeleted);
    }

}
