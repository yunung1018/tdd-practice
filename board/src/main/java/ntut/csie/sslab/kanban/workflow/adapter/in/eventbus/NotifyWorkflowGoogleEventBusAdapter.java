package ntut.csie.sslab.kanban.workflow.adapter.in.eventbus;

import com.google.common.eventbus.Subscribe;
import ntut.csie.sslab.kanban.workflow.usecase.port.in.notify.NotifyWorkflow;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ntut.csie.sslab.kanban.card.entity.event.*;
@Component
public class NotifyWorkflowGoogleEventBusAdapter {
    private final NotifyWorkflow notifyWorkflow;
    @Autowired
    public NotifyWorkflowGoogleEventBusAdapter(NotifyWorkflow notifyWorkflow) {
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
