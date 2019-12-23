package tw.teddysoft.clean.domain.model.kanbanboard.board;

import tw.teddysoft.clean.domain.model.DomainEventPublisher;
import tw.teddysoft.clean.domain.model.Entity;

import java.util.*;

public class Board extends Entity {

    private String workspaceId;
    private Set<CommittedWorkflow> committedWorkflows;

    public Board(String name, String workspaceId) {
        super(name);
        this.workspaceId = workspaceId;
        committedWorkflows = new HashSet<>();

        DomainEventPublisher
                .instance()
                .publish(new BoardCreated(
                        this.getId(),
                        this.getName()));
    }

    public void commitWorkflow(String workflowId){
        CommittedWorkflow committedWorkflow = new CommittedWorkflow(this.getId(), workflowId);
        committedWorkflow.setOrdering(committedWorkflows.size() + 1);
        committedWorkflows.add(committedWorkflow);
    }

//    public Stage createStage(String stageName) {
//        Stage stage = new Stage(stageName, this.getId());
//        return stage;
//    }

//    public void addStage(Stage stage) {
//        BoardStage boardStage = new BoardStage(this.getId(), stage.getId());
//        boardStage.setOrdering(boardStages.size() + 1);
//        boardStages.add(boardStage);
//    }

    public Set<CommittedWorkflow> getCommittedWorkflow() {
        return Collections.unmodifiableSet(committedWorkflows);
    }

    public int getWorkflowOrderingById(String workflowId){
        for (CommittedWorkflow each : committedWorkflows){
            if (each.getWorkflowId().equalsIgnoreCase(workflowId)){
                return each.getOrdering();
            }
        }
        return 0;
    }

    public void reorderBoardStage(String stageId, int oldOrdering, int newOrdering) {
        if(isMoveToSameOrdering(newOrdering, oldOrdering))
            return;

        for(CommittedWorkflow each : committedWorkflows){
            if(each.getOrdering() == oldOrdering){
                each.setOrdering(newOrdering);
                continue;
            }
            if (isMoveForward(newOrdering, oldOrdering)){
                if (inForwardFreeZone(newOrdering, oldOrdering, each))
                    continue;
                if(inForwardAffectedZone(newOrdering, oldOrdering, each))
                    each.moveBackward();
            }else {
                // move backward
                if (inBackwardFreeZone(newOrdering, oldOrdering, each)){
                    continue;
                }
                if(inBackwardAffectedZone(newOrdering, oldOrdering, each)){
                    each.moveForward();
                }
            }
        }
    }


    private boolean inForwardAffectedZone(int newOrdering, int oldOrdering, CommittedWorkflow each){
        return each.getOrdering() >= newOrdering && each.getOrdering() < oldOrdering;
    }

    private boolean inBackwardAffectedZone(int newOrdering, int oldOrdering, CommittedWorkflow each){
        return each.getOrdering() > oldOrdering && each.getOrdering() <= newOrdering;
    }


    private boolean inForwardFreeZone(int newOrdering, int oldOrdering, CommittedWorkflow each) {
        return each.getOrdering() < newOrdering || each.getOrdering() > oldOrdering;
    }

    private boolean inBackwardFreeZone(int newOrdering, int oldOrdering, CommittedWorkflow each){
        return (each.getOrdering() < oldOrdering || each.getOrdering() > newOrdering);
    }

    private boolean isMoveToSameOrdering(int newOrdering, int oldOrdering) {
        return newOrdering == oldOrdering;
    }

    private boolean isMoveForward(int newOrdering, int oldOrdering){
        return newOrdering < oldOrdering ? true : false;
    }

    public String dump() {
        StringBuilder sb = new StringBuilder();
        for (CommittedWorkflow each : committedWorkflows) {
            sb.append("Workflow");
            sb.append(String.format("[ordering]=%-16s", each.getOrdering()));
                sb.append(String.format("[workflow id]=%-40s", each.getWorkflowId()));
//                sb.append(String.format("[board id]=%-40s", each.getBoardId()));
                sb.append("\n");
        }

        System.out.println("Dump Committed Workflows => \n" + sb.toString());

        return sb.toString();
    }

    public String getWorkspaceId() {
        return workspaceId;
    }
}
