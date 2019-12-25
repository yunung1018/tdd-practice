package tw.teddysoft.clean.domain.model.kanbanboard.old_stage;

import de.cronn.reflection.util.immutable.ImmutableProxy;
import tw.teddysoft.clean.domain.model.Entity;
import tw.teddysoft.clean.domain.model.kanbanboard.WipLimitExceedException;
import tw.teddysoft.clean.domain.model.kanbanboard.old_stage.event.MiniStageDeleted;
import tw.teddysoft.clean.domain.model.kanbanboard.old_stage.event.StageCreated;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class Stage extends Entity {

    private final String boardId;
    private final List<MiniStage> miniStages;

    public Stage(String name, String boardId){
        super(name);
        this.boardId = boardId;
        miniStages = new ArrayList<MiniStage>();
        addDefaultMiniStage();
    }

    public String getBoardId() {
        return boardId;
    }

    public MiniStage createMiniStage(String miniStageName) {
        MiniStage result = new MiniStage(miniStageName, this.getId());
        miniStages.add(result);
        return getImmutableMiniStage(result);
    }

    public boolean deleteMiniStage(MiniStage miniStage) {

        if(miniStages.contains(miniStage)){
            this.doGetMiniStageById(miniStage.getId()).deleteAllSwimLane();
        }

        if (miniStages.remove(miniStage)){
            return true;
        }

        return false;
    }

    public List<MiniStage> getMiniStages() {
        return getImmutableMiniStages();
    }

    public MiniStage getMiniStageById(String id) {
        return getImmutableMiniStage(doGetMiniStageById(id));
    }

    public void commitWorkItemToSwimLaneById(
            String swimLaneId,
            String workItemId)
            throws WipLimitExceedException {

            doGetSwimLaneById(swimLaneId).commitWorkItemById(workItemId);
    }

    public void uncommitWorkItemFromSwimLaneById(
            String swimLaneId,
            String workItemId) {

            doGetSwimLaneById(swimLaneId).uncommitWorkItemById(workItemId);
    }


    public SwimLane getSwimLaneById(String id){
        return getImmutableSwimLane(doGetSwimLaneById(id));
    }

    public boolean isSwimLaneExist(String id){
        for (MiniStage each : miniStages) {
            if(true == each.isSwimLaneExist(id)){
                return true;
            }
        }
        return false;
    }


    public void updateMiniStageName(String miniStageId, String miniStageName) {
        this.doGetMiniStageById(miniStageId).setName(miniStageName);
    }


    public MiniStage getDefaultMiniStage(){
        return this.getImmutableMiniStage(miniStages.get(0));
    }


    public SwimLane getDefaultSwimLaneOfDefaultMiniStage(){
        return getImmutableSwimLane(miniStages.get(0).getDefaultSwimLane());
    }


    public SwimLane createSwimLaneForMiniStage(String miniStageId) {
        return getImmutableSwimLane(doGetMiniStageById(miniStageId).createSwimLane());
    }


    public void setSwimLaneWip(String swimlaneId, int wip) {
        this.doGetSwimLaneById(swimlaneId).setWipLimit(wip);
    }


    private void addDefaultMiniStage() {
        miniStages.add(new MiniStage("", this.getId()) );
    }

    private List<MiniStage> getImmutableMiniStages(){
        List<MiniStage> results = new LinkedList<>();
        for(MiniStage miniStage : miniStages){
            results.add(ImmutableProxy.create(miniStage));
        }

        return Collections.unmodifiableList(results);
    }

    private MiniStage getImmutableMiniStage(MiniStage original){
        return ImmutableProxy.create(original);
    }

    private SwimLane getImmutableSwimLane(SwimLane original){
        return ImmutableProxy.create(original);
    }

    private MiniStage doGetMiniStageById(String id) {
        for (MiniStage each : miniStages) {
            if (each.getId().equalsIgnoreCase(id)) {
                return each;
            }
        }
        throw new RuntimeException("MiniStage with id: " + id + " not found.");
    }


    private SwimLane doGetSwimLaneById(String id){
        for (MiniStage each : miniStages) {
            if (null != each.getSwimLaneById(id))
                return each.getSwimLaneById(id);
        }

        throw new RuntimeException("SwimLane id " + id + " not found.");
    }

}
