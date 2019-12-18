package tw.teddysoft.clean.domain.model.kanbanboard.workflow;

public class LaneBuilder {

    private String name;
    private String workflowId;
    private LaneOrientation orientation = LaneOrientation.VERTICAL;
    private boolean isStage = false;

    private LaneBuilder(){};

    public static LaneBuilder getInstance(){
        return new LaneBuilder();
    }

    public LaneBuilder name(String name) {
        this.name = name;
        return this;
    }

    public LaneBuilder workflowId(String workflowId) {
        this.workflowId = workflowId;
        return this;
    }

    public LaneBuilder vertical() {
        orientation = LaneOrientation.VERTICAL;
        return this;
    }

    public LaneBuilder horizontal() {
        orientation = LaneOrientation.HORIZONTAL;
        return this;
    }

    public LaneBuilder stage(){
        isStage = true;
        return this;
    }

    public LaneBuilder nonStage(){
        isStage = false;
        return this;
    }

    public Lane build(){
        if (isStage)
            return new StageLane(name,workflowId);
        else if (isVertical())
            return new MninStageLane(name,workflowId);
        else
            return new SwimLane(name,workflowId);
    }


    private boolean isVertical(){
        return LaneOrientation.VERTICAL == orientation ? true : false;
    }
}
