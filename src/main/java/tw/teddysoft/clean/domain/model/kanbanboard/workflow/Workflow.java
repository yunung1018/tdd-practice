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

//    public void addLane(String laneName, String workflowId, String parentId, LaneOrientation orientation) {
//
//        if ((parentId == null) && orientation == LaneOrientation.HORIZONTAL)
//            throw new RuntimeException("Cannot add horizontal lane at the top of a workflow.");
//
//        if  ((parentId == null) && orientation == LaneOrientation.VERTICAL){
//            addTopLevelVerticalLane();
//            return;
//        }
//
//    }

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

        if (parentId == each.getId())
            return each;
        else if (each.hasSubLane()) {
            for (Lane next : each.getSubLanes()) {
                return findLaneById(next, parentId);
            }
        }

        return null;
    }
}
