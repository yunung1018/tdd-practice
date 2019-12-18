package tw.teddysoft.clean.domain.model.kanbanboard.workflow;

public class LaneBuilder {

    private String title;
    private String workflowId;
    private LaneOrientation orientation = LaneOrientation.VERTICAL;
    private boolean isStage = false;

    private LaneBuilder(){};

    public static LaneBuilder getInstance(){
        return new LaneBuilder();
    }

    public LaneBuilder title(String title) {
        this.title = title;
        return this;
    }

    public LaneBuilder workflowId(String workflowId) {
        this.workflowId = workflowId;
        return this;
    }

    public LaneBuilder ministage() {
        isStage = false;
        orientation = LaneOrientation.VERTICAL;
        return this;
    }

    public LaneBuilder swimlane() {
        isStage = false;
        orientation = LaneOrientation.HORIZONTAL;
        return this;
    }

    public LaneBuilder stage(){
        isStage = true;
        orientation = LaneOrientation.VERTICAL;
        return this;
    }

    public Lane build(){
        if (isStage)
            return new Stage(title,workflowId);
        else if (isVertical())
            return new Mninstage(title,workflowId);
        else
            return new Swimlane(title,workflowId);
    }

    private boolean isVertical(){
        return LaneOrientation.VERTICAL == orientation ? true : false;
    }
}
