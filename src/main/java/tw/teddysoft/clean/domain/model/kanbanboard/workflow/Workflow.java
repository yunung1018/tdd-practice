package tw.teddysoft.clean.domain.model.kanbanboard.workflow;

import tw.teddysoft.clean.domain.model.DomainEventPublisher;
import tw.teddysoft.clean.domain.model.Entity;
import tw.teddysoft.clean.domain.model.kanbanboard.workflow.event.WorkflowCreated;

import java.util.*;

public class Workflow extends Entity {

    private final String boardId;
    private final List<Lane> stages;

    public Workflow(String name, String boardId){
        super(name);
        this.boardId = boardId;
        stages = new ArrayList<>();

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
        return stage;
    }

    public Lane addMinistage(String parentId, String title){
        Lane parent = findLaneById(parentId);
        if (null == parent)
            throw new RuntimeException("Cannot find the parent lane '" + parentId + "' to add the ministage '" + title + "' under it.");

        Lane ministage = LaneBuilder.getInstance()
                .title(title)
                .workflowId(getId())
                .ministage()
                .build();

        parent.addSubLane(ministage);

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
        return swimlane;
    }


    public Lane findLaneById(String parentId) {
        Lane result = null;
        for(Lane each : stages){
            if ( (result = findLaneById(each, parentId)) != null)
                return result;
        }
        return result;
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
