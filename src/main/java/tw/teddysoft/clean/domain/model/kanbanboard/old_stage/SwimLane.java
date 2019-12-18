package tw.teddysoft.clean.domain.model.kanbanboard.old_stage;

import tw.teddysoft.clean.domain.model.DomainEventPublisher;
import tw.teddysoft.clean.domain.model.Entity;
import tw.teddysoft.clean.domain.model.kanbanboard.WipLimitExceedException;
import tw.teddysoft.clean.domain.model.kanbanboard.old_stage.event.SwimLaneCreated;
import tw.teddysoft.clean.domain.model.workitem.event.WorkItemMovedIn;
import tw.teddysoft.clean.domain.model.workitem.event.WorkItemMovedOut;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SwimLane extends Entity {
    public static final String DEFAULT_NAME = "";
    public static final int NO_WIP_LIMIT = -1;

    private final String stageId;
    private final String miniStageId;
    private final List<CommittedWorkItem> committedWorkItems;
    private int wipLimit;

    SwimLane(String stageId, String miniStageId){
        super(DEFAULT_NAME);
        this.stageId = stageId;
        this.miniStageId = miniStageId;
        committedWorkItems = new ArrayList<CommittedWorkItem>();
        wipLimit = NO_WIP_LIMIT;

        DomainEventPublisher
                .instance()
                .publish(new SwimLaneCreated(
                        this.getId(),
                        this.getName()));
    }

    public void commitWorkItemById(String workItemId) throws WipLimitExceedException {
        if (isReachWipLimit())
            throw new WipLimitExceedException("Exceed WIP limit : " + wipLimit);

        committedWorkItems.add(new CommittedWorkItem(
                this.getStageId(), this.getMiniStageId(), this.getId(), workItemId, committedWorkItems.size()+1));

        DomainEventPublisher
                .instance()
                .publish(new WorkItemMovedIn(
                        this.getId(),
                        this.getName(),
                        this.getStageId(),
                        this.getMiniStageId(),
                        this.getId(),
                        workItemId));
    }

    public int getWipLimit(){
        return wipLimit;
    }

    public void setWipLimit(int limit){
        wipLimit = limit;
    }


    public boolean isReachWipLimit() {
        return committedWorkItems.size() == wipLimit;
    }

    public List<CommittedWorkItem> getCommittedWorkItems() {
        return Collections.unmodifiableList(committedWorkItems);
    }

    public boolean uncommitWorkItemById(String workItemId) {
        for(CommittedWorkItem each : committedWorkItems){
            if (each.getWorkItemId().equalsIgnoreCase(workItemId)){
                committedWorkItems.remove(each);
                reorderCommittedWorkItem();

                DomainEventPublisher
                        .instance()
                        .publish(new WorkItemMovedOut(
                                this.getId(),
                                this.getName(),
                                this.getStageId(),
                                this.getMiniStageId(),
                                this.getId(),
                                workItemId));
                return true;
            }
        }
        return false;
    }

    private void reorderCommittedWorkItem(){
        for (int i = 0; i < committedWorkItems.size(); i++){
            committedWorkItems.get(i).setOrdering(i+1);
        }
    }

    public String getStageId() {
        return stageId;
    }

    public String getMiniStageId() {
        return miniStageId;
    }



}
