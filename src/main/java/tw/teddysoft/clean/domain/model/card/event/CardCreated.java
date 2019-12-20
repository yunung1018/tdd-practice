package tw.teddysoft.clean.domain.model.card.event;

import tw.teddysoft.clean.domain.model.AbstractDomainEvent;

public class CardCreated extends AbstractDomainEvent {

    public CardCreated(String id, String title) {
        super(id, title);
    }
}
