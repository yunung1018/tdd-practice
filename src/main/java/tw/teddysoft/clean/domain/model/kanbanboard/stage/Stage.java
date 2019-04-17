package tw.teddysoft.clean.domain.model.kanbanboard.stage;

import tw.teddysoft.clean.domain.model.DomainEventPublisher;
import tw.teddysoft.clean.domain.model.Entity;
import tw.teddysoft.clean.domain.model.kanbanboard.WipLimitExceedException;
import tw.teddysoft.clean.domain.model.kanbanboard.stage.event.MiniStageDeleted;
import tw.teddysoft.clean.domain.model.kanbanboard.stage.event.StageCreated;

import java.util.ArrayList;
import java.util.List;

public class Stage extends Entity {

    private String boardId;
    private List<MiniStage> miniStages;

    public Stage(String name, String boardId){
        super(name);
        this.boardId = boardId;
        miniStages = new ArrayList<MiniStage>();

        DomainEventPublisher
                .instance()
                .publish(new StageCreated(
                        this.getId(),
                        this.getName()));

        addDefaultMiniStage();
    }

    public String getBoardId() {
        return boardId;
    }

    public MiniStage createMiniStage(String miniStageName) {
        MiniStage result = new MiniStage(miniStageName, this.getId());
        miniStages.add(result);
        return result;
    }

    public boolean deleteMiniStage(MiniStage miniStage) {

        if(miniStages.contains(miniStage)){
            miniStage.deleteAllSwimLane();
        }

        if (miniStages.remove(miniStage)){
            DomainEventPublisher
                    .instance()
                    .publish(new MiniStageDeleted(
                            miniStage.getId(),
                            miniStage.getName()));
            return true;
        }

        return false;
    }


    public List<MiniStage> getMiniStages() {
        return miniStages;
    }


    public MiniStage getMiniStageByIndex(int index) {
        return miniStages.get(index);
    }

    public MiniStage getMiniStageById(String id) {

        for (MiniStage each : miniStages) {
            if (each.getId().equalsIgnoreCase(id)) {
                return each;
            }
        }
        throw new RuntimeException("MiniStage with id: " + id + " not found.");
    }

    public void commitWorkItemToSwimLaneById(
            String swimLaneId,
            String workItemId)
            throws WipLimitExceedException {

            SwimLane swimLane = getSwimLaneById(swimLaneId);
            swimLane.commitWorkItemById(workItemId);
    }

    public void uncommitWorkItemFromSwimLaneById(
            String swimLaneId,
            String workItemId) {

        SwimLane swimLane = getSwimLaneById(swimLaneId);
        swimLane.uncommitWorkItemById(workItemId);
    }


    public SwimLane getSwimLaneById(String id){
        for (MiniStage each : miniStages) {
            if (null != each.getSwimLaneById(id))
                return each.getSwimLaneById(id);
        }

        throw new RuntimeException("SwimLane with id: " + id + " not found.");
    }

    public boolean isSwimLaneExist(String id){
        for (MiniStage each : miniStages) {
            if(true == each.isSwimLaneExist(id)){
                return true;
            }
        }
        return false;
    }

    public SwimLane getDefaultSwimLaneOfMiniStage(){
        return miniStages.get(0).getDefaultSwimLane();
    }

    public MiniStage getDefaultMiniStage(){
        return miniStages.get(0);
    }


    private void addDefaultMiniStage() {
        miniStages.add(new MiniStage("", this.getId()) );
    }

}
