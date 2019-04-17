package tw.teddysoft.clean.domain.model.workitem.event;

import tw.teddysoft.clean.domain.model.AbstractDomainEvent;

public class WorkItemCreated extends AbstractDomainEvent {

    public WorkItemCreated(String id, String name) {
        super(id, name);
    }
}
