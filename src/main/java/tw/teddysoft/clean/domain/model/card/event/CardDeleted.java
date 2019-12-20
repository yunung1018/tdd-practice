package tw.teddysoft.clean.domain.model.card.event;

import tw.teddysoft.clean.domain.model.AbstractDomainEvent;

public class CardDeleted extends AbstractDomainEvent {

    private String title;

    public CardDeleted(String id, String title) {
        super(id, "");
        this.title = title;
    }
}
