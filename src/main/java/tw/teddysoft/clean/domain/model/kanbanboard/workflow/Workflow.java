package tw.teddysoft.clean.domain.model.kanbanboard.workflow;

import tw.teddysoft.clean.domain.model.DomainEventPublisher;
import tw.teddysoft.clean.domain.model.Entity;
import tw.teddysoft.clean.domain.model.card.event.CardCommitted;
import tw.teddysoft.clean.domain.model.card.event.CardUncommitted;
import tw.teddysoft.clean.domain.model.kanbanboard.WipLimitExceedException;
import tw.teddysoft.clean.domain.model.kanbanboard.workflow.event.WorkflowCreated;

import java.util.*;

public class Workflow extends Entity {

    private final String boardId;
    private final List<Lane> stages;
    private String title;

    public Workflow(String title, String boardId){
        super("");
        this.boardId = boardId;
        this.title = title;

        stages = new ArrayList<>();

        DomainEventPublisher
                .instance()
                .publish(new WorkflowCreated(
                        this.boardId,
                        this.getId(),
                        this.getTitle()));
    }

    public String getBoardId(){
        return boardId;
    }

    public List<Lane> getStages() {
        return Collections.unmodifiableList(stages);
    }

    public Lane addStage(String title) {
        Lane stage = LaneBuilder.getInstance()
                .title(title)
                .workflowId(getId())
                .stage()
                .build();
        stages.add(stage);

        //TODO return a readonly version of stage
        return stage;
    }

    public Lane addStage(String parentId, String title){
        Lane parent = findLaneById(parentId);
        if (null == parent)
            throw new RuntimeException("Cannot find the parent lane '" + parentId + "' to add the ministage '" + title + "' under it.");

        Lane ministage = LaneBuilder.getInstance()
                .title(title)
                .workflowId(getId())
                .stage()
                .build();

        parent.addSubLane(ministage);

        //TODO return a readonly version of ministage
        return ministage;
    }

    public Lane addSwimlane(String parentId, String title){
        Lane parent = findLaneById(parentId);
        if (null == parent)
            throw new RuntimeException("Cannot find the parent lane '" + parentId + "' to add the swimlane '" + title + "' under it.");

        Lane swimlane = LaneBuilder.getInstance()
                .title(title)
                .workflowId(getId())
                .swimlane()
                .build();

        parent.addSubLane(swimlane);

        //TODO return a readonly version of swimlane
        return swimlane;
    }

    public void commitCard(String cardId, String laneId) {
        Lane toLane = findLaneById(laneId);
        if (null == toLane)
            throw new RuntimeException("Cannot commit a card to a non-existing land '" + laneId + "'");

        toLane.commitCard(cardId);

        DomainEventPublisher
                .instance()
                .publish(new CardCommitted(
                        this.getId(),
                        toLane.getId(),
                        cardId,
                        toLane.getTitle()));
    }

    public void uncommitCard(String cardId, String laneId) {
        Lane lane = findLaneById(laneId);
        if (null == lane)
            throw new RuntimeException("Cannot commit a card to be uncommitted from a non-existing land '" + laneId + "'");

        if (!lane.isCardCommitted(cardId))
            throw new RuntimeException("Cannot uncommit card '" + cardId + "' + which does not exist in land '" + laneId + "'");

        lane.uncommitCard(cardId);
        DomainEventPublisher
                .instance()
                .publish(new CardUncommitted(
                        this.getId(),
                        lane.getId(),
                        cardId,
                        lane.getTitle()));
    }

    public void moveCard(String cardId, String fromLaneId, String toLandId) {

        Lane fromLane = findLaneById(fromLaneId);
        if (null == fromLane)
            throw new RuntimeException("Cannot uncommit a card from a non-existing land '" + fromLaneId + "'");

        Lane toLane = findLaneById(toLandId);
        if (null == toLane)
            throw new RuntimeException("Cannot commit a card to a non-existing land '" + toLane + "'");

        if (!fromLane.isCardCommitted(cardId))
            throw new RuntimeException("Cannot move card '" + cardId + "' which does not belong to lane '" + fromLaneId + "'");

        fromLane.uncommitCard(cardId);
        toLane.commitCard(cardId);

        DomainEventPublisher
                .instance()
                .publish(new CardUncommitted(
                        this.getId(),
                        fromLane.getId(),
                        cardId,
                        fromLane.getTitle()));

        DomainEventPublisher
                .instance()
                .publish(new CardCommitted(
                        this.getId(),
                        toLane.getId(),
                        cardId,
                        toLane.getTitle()));
    }


    public void dumpLane() {
        for(Lane each : stages){
            dumpLane(each, 0);
        }
    }

    public int getTotalLaneSize() {
        int result = 0;
        for(Lane each : stages){
            result += getTotalLaneSize(each);
        }
        return result;
    }


    public Lane findLaneByCardId(String cardId){
        Lane result = null;
        for(Lane each : stages){
            if ( (result = findLaneByCardId(each, cardId)) != null)
                return result;
        }
        return result;
    }

    private Lane findLaneByCardId(Lane each, String cardId) {
        Lane result = null;

        if (each.isCardCommitted(cardId)){
            result = each;
        }
        else if (each.hasSubLane()) {
            for (Lane next : each.getSubLanes()) {
                result = findLaneById(next, cardId);
            }
        }
        return result;
    }

    public Lane findLaneById(String laneId) {
        Lane result = null;
        for(Lane each : stages){
            if ( (result = findLaneById(each, laneId)) != null)
                return result;
        }
        return result;
    }

    private Lane findLaneById(Lane each, String parentId) {

//        System.out.println(each.getTitle() + " id = " + each.getId());
//        System.out.println(each.getTitle() + " has sublane = " + each.hasSubLane());

        Lane result = null;

        if (parentId == each.getId()){
            result = each;
        }
        else if (each.hasSubLane()) {
            for (Lane next : each.getSubLanes()) {
//                System.out.println("==> " + next.getTitle());
                result = findLaneById(next, parentId);
            }
        }
        return result;
    }

    private int getTotalLaneSize(Lane each) {
        int result = 1;
        if (each.hasSubLane()) {
            for (Lane next : each.getSubLanes()) {
                result += getTotalLaneSize(next);
            }
        }
        return result;
    }


    private void dumpLane(Lane each, int tabs) {
//        System.out.println("Lane ==>" + each.getTitle() );
//        System.out.println("each.hasSubLane() ==>" + each.hasSubLane());
        System.out.printf(getTabs(tabs) + "%-20s %n", each.getTitle());
        if (each.hasSubLane()) {
            for (Lane next : each.getSubLanes()) {
                dumpLane(next, tabs+1);
            }
        }
    }

    private String getTabs(int tabs){
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < tabs; i++){
            sb.append("\t");
        }
        return sb.toString();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    //    public void moveCard(String cardId, String toLandId) {
//        Lane toLane = findLaneById(toLandId);
//        if (null == toLane)
//            throw new RuntimeException("Cannot commit a card to a non-existing land '" + toLandId + "'");
//
//        if (isCardPresented(cardId)){
//            Lane fromLane = findLaneByCardId(cardId);
//            fromLane.uncommitCard(cardId);
//        }
//        else{
//            toLane.commitCard(cardId);
//        }
//
//        // 在這裡發 domain event
//    }

//    public boolean isCardPresented(String cardId){
//        return false;
//    }
}
