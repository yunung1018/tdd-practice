package tw.teddysoft.clean.domain.model;

import tw.teddysoft.clean.domain.common.DateProvider;

import java.util.Date;
import java.util.UUID;

public class AssociationDomainEvent implements DomainEvent {

    private final String id;
    private final Date occurredOn;
    private final String fromId;
    private final String toId;

    private static final long serialVersionUID = 1L;

    public AssociationDomainEvent(String fromId, String toId) {
        super();
        this.id = UUID.randomUUID().toString();
        this.occurredOn = DateProvider.now();
        this.fromId = fromId;
        this.toId = toId;
    }

    @Override
    public int eventVersion() {
        return EventVersion.NUMBER;
    }

    @Override
    public Date occurredOn() {
        return occurredOn;
    }

    @Override
    public String detail() {
        String formatDate = String.format("occurredOn='%1$tY-%1$tm-%1$td %1$tH:%1$tM']", occurredOn());
        String format = String.format(
                "%s[from id='%s', to id='%s'] ",
                this.getClass().getSimpleName(),
                this.getFromId(), this.getToId());
        return format + formatDate;
    }

    public String getId() {
        return id;
    }

    protected String getFromId() {
        return fromId;
    }

    protected String getToId() {
        return toId;
    }
}
