package tw.teddysoft.clean.domain.model.kanbanboard.board;

import tw.teddysoft.clean.domain.model.AbstractDomainEvent;

public class BoardCreated extends AbstractDomainEvent {

    public BoardCreated(String id, String name) {
        super(id, name);
    }
}
