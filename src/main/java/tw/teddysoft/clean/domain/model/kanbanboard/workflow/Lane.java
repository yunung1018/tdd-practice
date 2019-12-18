package tw.teddysoft.clean.domain.model.kanbanboard.workflow;

import tw.teddysoft.clean.domain.model.Entity;

import java.util.*;

abstract public class Lane extends Entity {
    public static final String DEFAULT_NAME = "";

    private final String workflowId;
    private final List<Lane> sublanes;
    private final LaneOrientation orientation;
    private String title;
    private int wipLimit;

    Lane(String title, String workflowId, LaneOrientation orientation) {
        super("");
        this.title = title;
        this.workflowId = workflowId;
        this.orientation = orientation;
        wipLimit = 0;
        sublanes = new ArrayList<>();
    }

    Lane(String title, String workflowId, LaneOrientation orientation, int wipLimit) {
        this(title, workflowId, orientation);
        this.wipLimit = wipLimit;
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

    public void setTitle(String title){this.title = title;}

    public String getTitle(){return title;}

    public int getWipLimit() {
        return wipLimit;
    }

    public void setWipLimit(int wipLimit) {
        if (wipLimit < 0)
            throw new RuntimeException("WIP Limit cannot be negative.");

        this.wipLimit = wipLimit;
    }
}
