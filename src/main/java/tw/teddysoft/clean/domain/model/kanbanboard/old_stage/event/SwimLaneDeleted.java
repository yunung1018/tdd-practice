package tw.teddysoft.clean.domain.model.kanbanboard.old_stage.event;

import tw.teddysoft.clean.domain.model.AbstractDomainEvent;

public class SwimLaneDeleted extends AbstractDomainEvent {

    public SwimLaneDeleted(String id, String name) {
        super(id, name);
    }
}
