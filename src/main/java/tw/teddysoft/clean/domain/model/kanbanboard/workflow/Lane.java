package tw.teddysoft.clean.domain.model.kanbanboard.workflow;

import de.cronn.reflection.util.immutable.ReadOnly;
import tw.teddysoft.clean.domain.model.DomainEventPublisher;
import tw.teddysoft.clean.domain.model.Entity;
import tw.teddysoft.clean.domain.model.kanbanboard.stage.SwimLane;
import tw.teddysoft.clean.domain.model.kanbanboard.stage.event.MiniStageCreated;
import tw.teddysoft.clean.domain.model.kanbanboard.stage.event.SwimLaneDeleted;
import tw.teddysoft.clean.domain.model.kanbanboard.workflow.event.LaneCreated;

import java.util.*;

abstract public class Lane extends Entity {
    public static final String DEFAULT_NAME = "";

    private final String workflowId;
    private final List<Lane> sublanes;
    private final LaneOrientation orientation;

    Lane(String name, String workflowId, LaneOrientation orientation) {
        super(name);
        this.workflowId = workflowId;
        this.orientation = orientation;
        sublanes = new LinkedList<>();
    }

    public String getWorkflowId() {
        return workflowId;
    }

    public LaneOrientation getOrientation(){
        return orientation;
    }

    public List<Lane> getSubLanes(){
        return Collections.unmodifiableList(sublanes);
    }

    public boolean hasSubLane() {
        return 0 == sublanes.size() ? false : true;
    }

    public void addSubLane(Lane lane) {
        sublanes.add(lane);
    }
}
