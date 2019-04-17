package tw.teddysoft.clean.domain.model.kanbanboard.stage.event;

import tw.teddysoft.clean.domain.model.AbstractDomainEvent;

public class MiniStageCreated extends AbstractDomainEvent {

    public MiniStageCreated(String id, String name) {
        super(id, name);
    }
}
