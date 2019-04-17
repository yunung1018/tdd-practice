package tw.teddysoft.clean.domain.model.kanbanboard.stage;

import tw.teddysoft.clean.domain.model.DomainEventPublisher;
import tw.teddysoft.clean.domain.model.Entity;
import tw.teddysoft.clean.domain.model.kanbanboard.stage.event.MiniStageCreated;
import tw.teddysoft.clean.domain.model.kanbanboard.stage.event.SwimLaneDeleted;

import java.util.ArrayList;
import java.util.List;

public class MiniStage extends Entity {

    public static final String DEFAULT_NAME = "";

    private String stageId;
    private List<SwimLane> swimLanes;
//
//    MiniStage(String stageId){
//        this(DEFAULT_NAME, stageId);
//    }

    MiniStage(String name, String stageId) {
        super(name);
        this.stageId = stageId;
        swimLanes = new ArrayList<SwimLane>();

        DomainEventPublisher
                .instance()
                .publish(new MiniStageCreated(
                        this.getId(),
                        this.getName()));

        addDefaultSwimLane();
    }

    public SwimLane createSwimLane() {
        SwimLane result = new SwimLane(stageId, this.getId());
        swimLanes.add(result);
        return result;
    }


    public String getStageId() {
        return stageId;
    }

    public int getTotalNumberOfSwimLane() {
        return swimLanes.size();
    }

    public SwimLane getSwimLaneByIndex(int index) {
        return swimLanes.get(index);
    }

    public List<SwimLane> getSwimLanes(){
        return swimLanes;
    }

    public SwimLane getDefaultSwimLane(){
        return swimLanes.get(0);
    }

    public SwimLane getSwimLaneById(String id) {
        for (SwimLane each : swimLanes) {
            if (each.getId().equalsIgnoreCase(id))
                return each;
        }
        return null;
    }

    public boolean isSwimLaneExist(String id){
        for (SwimLane each : swimLanes) {
            if (each.getId().equalsIgnoreCase(id))
                return true;
        }
        return false;
    }

    public void deleteAllSwimLane() {
        for (SwimLane each : swimLanes) {
            DomainEventPublisher
                    .instance()
                    .publish(new SwimLaneDeleted(
                            each.getId(),
                            each.getName()));
        }
        swimLanes.clear();
    }

    private boolean addDefaultSwimLane() {
        return swimLanes.add(new SwimLane(this.getStageId(), this.getId()));
    }


}
