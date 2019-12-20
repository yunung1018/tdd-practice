package tw.teddysoft.clean.domain.model;

import tw.teddysoft.clean.domain.common.DateProvider;

import java.util.Date;
import java.util.UUID;

public class AbstractDomainEvent implements DomainEvent {

    private final Date occurredOn;
    private final String sourceId;
    private final String sourceTitle;
    private final String id;

    private static final long serialVersionUID = 1L;

    public AbstractDomainEvent(String sourceId, String sourceTitle) {
        super();
        this.id = UUID.randomUUID().toString();
        this.sourceId = sourceId;
        this.occurredOn = DateProvider.now();
        this.sourceTitle = sourceTitle;
    }

//        this.occurredOn = new Date();

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
                "%s[Title='%s', id='%s'] ",
                this.getClass().getSimpleName(),
                this.getSourceTitle(), this.getSourceId());
        return format + formatDate;
    }

    public String getSourceId() {
        return sourceId;
    }

    public String getSourceTitle() {
        return sourceTitle;
    }

    public String getId() {
        return id;
    }
}
