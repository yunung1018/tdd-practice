package tw.teddysoft.clean.domain.model.card.event;

import tw.teddysoft.clean.domain.model.AbstractDomainEvent;

public class CardCommitted extends AbstractDomainEvent {

    private final String cardId;

    public CardCommitted(String sourceId, String title, String cardId) {
        super(sourceId, title);
        this.cardId = cardId;
    }

    @Override
    public String detail() {
        String formatDate = String.format("occurredOn='%1$tY-%1$tm-%1$td %1$tH:%1$tM']", occurredOn());
        String format = String.format(
                "%s[Lane title='%s', Lane id='%s', Card id ='%s'] ",
                this.getClass().getSimpleName(),
                this.getSourceTitle(), this.getSourceId(),
                getCardId());
        return format + formatDate;
    }

    public String getCardId() {
        return cardId;
    }
}
