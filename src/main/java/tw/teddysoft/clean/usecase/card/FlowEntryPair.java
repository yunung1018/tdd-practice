package tw.teddysoft.clean.usecase.card;

import tw.teddysoft.clean.domain.model.FlowEvent;

import java.util.Date;
import java.util.HashMap;

public class FlowEntryPair {

    private final String cardId;
    private final String workflowId;
    private final String laneId;
    private final Date occurredOnOfCommitted;
    private final Date occurredOnOfUncommitted;
    private final CycleTime cycleTime;

    // key = work item id
    private HashMap<String, FlowEntryPair> flowMeasurements;

    public FlowEntryPair(FlowEvent committed, FlowEvent uncommitted) {

        assert committed.getLeanId() == uncommitted.getLeanId();
        assert committed.getWorkflowId() == uncommitted.getWorkflowId();
        assert committed.getCardId() == uncommitted.getCardId();
        assert committed.occurredOn().before(uncommitted.occurredOn());

        workflowId = committed.getWorkflowId();
        laneId = committed.getLeanId();
        cardId = committed.getCardId();
        occurredOnOfCommitted = committed.occurredOn();
        occurredOnOfUncommitted = uncommitted.occurredOn();

        long diff = (occurredOnOfUncommitted.getTime() - occurredOnOfCommitted.getTime()) / 1000;
        long diffDays = diff/(24*3600);
        long diffHours = diff %(24*3600)/3600;
        long diffMinutes = diff % 3600/60;
        long diffSeconds = diff % 60/60;;

        cycleTime = new CycleTime(diffDays, diffHours, diffMinutes, diffMinutes, diffSeconds);


    }

    public String getCardId() {
        return cardId;
    }

    public String getWorkflowId() {
        return workflowId;
    }

    public String getLaneId() {
        return laneId;
    }

    public Date getOccurredOnOfCommitted() {
        return occurredOnOfCommitted;
    }

    public Date getOccurredOnOfUncommitted() {
        return occurredOnOfUncommitted;
    }

    public CycleTime getCycleTime() {
        return cycleTime;
    }
}
