package tw.teddysoft.clean.usecase.card;

import tw.teddysoft.clean.domain.model.FlowEvent;

import java.util.Date;
import java.util.HashMap;

public class FlowEntryPair {

    private final String stageId;
    private final String miniStageId;
    private final String swimLaneId;
    private final String workItemId;
    private final Date occurredOnOfMovedIn;
    private final Date occurredOnOfMovedOut;
    private final CycleTime cycleTime;

    // key = work item id
    private HashMap<String, FlowEntryPair> flowMeasurements;


    public FlowEntryPair(FlowEvent movedIn, FlowEvent movedOut) {

        assert movedIn.getSwimLaneId() == movedOut.getSwimLaneId();
        assert movedIn.getMiniStageId() == movedOut.getMiniStageId();
        assert movedIn.getSwimLaneId() == movedOut.getSwimLaneId();
        assert movedIn.getWorkItemId() == movedOut.getWorkItemId();
        assert movedIn.occurredOn().before(movedOut.occurredOn());

        stageId = movedIn.getStageId();
        miniStageId = movedIn.getMiniStageId();
        swimLaneId = movedIn.getSwimLaneId();
        workItemId = movedIn.getWorkItemId();
        occurredOnOfMovedIn = movedIn.occurredOn();
        occurredOnOfMovedOut = movedOut.occurredOn();

        long diff = (occurredOnOfMovedOut.getTime() - occurredOnOfMovedIn.getTime()) / 1000;
        long diffDays = diff/(24*3600);
        long diffHours = diff %(24*3600)/3600;
        long diffMinutes = diff % 3600/60;
        long diffSeconds = diff % 60/60;;

        cycleTime = new CycleTime(diffDays, diffHours, diffMinutes, diffMinutes, diffSeconds);

    }

    public String getWorkItemId() {
        return workItemId;
    }

    public CycleTime getCycleTime() {
        return cycleTime;
    }

    public String getStageId() {
        return stageId;
    }

    public String getMiniStageId() {
        return miniStageId;
    }

    public String getSwimLaneId() {
        return swimLaneId;
    }
}
