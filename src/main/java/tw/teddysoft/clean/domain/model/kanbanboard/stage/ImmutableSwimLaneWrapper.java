package tw.teddysoft.clean.domain.model.kanbanboard.stage;

public class ImmutableSwimLaneWrapper extends SwimLane {

    private SwimLane real;

    ImmutableSwimLaneWrapper(SwimLane real) {
        super(real.getStageId(), real.getMiniStageId());
        this.real = real;
    }


}
