package tw.teddysoft.clean.domain.model.kanbanboard.workflow;

import tw.teddysoft.clean.domain.model.DomainEventPublisher;
import tw.teddysoft.clean.domain.model.Entity;
import tw.teddysoft.clean.domain.model.kanbanboard.stage.MiniStage;
import tw.teddysoft.clean.domain.model.kanbanboard.stage.event.StageCreated;
import tw.teddysoft.clean.domain.model.kanbanboard.workflow.event.WorkflowCreated;

import java.util.*;

public class Workflow extends Entity {

    private final String boardId;
    private final List<Lane> stageLanes;

    public Workflow(String name, String boardId){
        super(name);
        this.boardId = boardId;

        stageLanes = new LinkedList<>();

        DomainEventPublisher
                .instance()
                .publish(new WorkflowCreated(
                        this.boardId,
                        this.getId(),
                        this.getName()));
    }

    public String getBoardId(){
        return boardId;
    }

    public List<Lane> getStageLanes() {
        return Collections.unmodifiableList(stageLanes);
    }

    public void addStageLane(Lane lane) {
        stageLanes.add(lane);
    }

    public Lane findLaneById(String parentId) {
        for(Lane each : stageLanes){
            return findLaneById(each, parentId);
        }
        return null;
    }


    private Lane findLaneById(Lane each, String parentId) {

//        System.out.println(each.getName() + " id = " + each.getId());
//        System.out.println(each.getName() + " has sublane = " + each.hasSubLane());

        Lane result = null;

        if (parentId == each.getId()){
            result = each;
        }
        else if (each.hasSubLane()) {
            for (Lane next : each.getSubLanes()) {
//                System.out.println("==> " + next.getName());
                result = findLaneById(next, parentId);
            }
        }
        return result;
    }

    public void dumpLane() {
        for(Lane each : stageLanes){
            dumpLane(each, 0);
        }
    }


    private void dumpLane(Lane each, int tabs) {
//        System.out.println("Lane ==>" + each.getName() );
//        System.out.println("each.hasSubLane() ==>" + each.hasSubLane());

        System.out.printf(getTabs(tabs) + "%-20s %n", each.getName());
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

}
