package tw.teddysoft.clean.domain.model.kanbanboard.old_stage.event;

import tw.teddysoft.clean.domain.model.AbstractDomainEvent;

public class SwimLaneCreated extends AbstractDomainEvent {

    public SwimLaneCreated(String id, String name) {
        super(id, name);
    }
}
