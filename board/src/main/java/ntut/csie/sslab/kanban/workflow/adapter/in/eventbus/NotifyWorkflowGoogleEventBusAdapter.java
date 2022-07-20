package ntut.csie.sslab.kanban.workflow.adapter.in.eventbus.google;

import com.google.common.eventbus.Subscribe;
import ntut.csie.sslab.kanban.workflow.usecase.service.NotifyWorkflowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ntut.csie.sslab.kanban.card.entity.event.*;
@Component
public class NotifyWorkflowAdapter {

    private NotifyWorkflowService notifyWorkflow;

    @Autowired
    public NotifyWorkflowAdapter(NotifyWorkflowService notifyWorkflow) {
        this.notifyWorkflow = notifyWorkflow;
    }

    @Subscribe
    public void whenCardCreated(CardCreated event) {
        notifyWorkflow.whenCardCreated(event);
    }

    @Subscribe
    public void whenCardDeleted(CardDeleted event) {
        notifyWorkflow.whenCardDeleted(event);
    }

}
