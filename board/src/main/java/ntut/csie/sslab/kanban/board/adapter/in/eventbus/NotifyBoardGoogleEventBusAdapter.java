package ntut.csie.sslab.kanban.board.adapter.in.eventbus.google;

import com.google.common.eventbus.Subscribe;
import ntut.csie.sslab.kanban.eventhandler.NotifyBoard;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ntut.csie.sslab.kanban.workflow.entity.event.*;



@Component
public class NotifyBoardAdapter {

    private NotifyBoard notifyBoard;

    @Autowired
    public NotifyBoardAdapter(NotifyBoard notifyBoard) {
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
