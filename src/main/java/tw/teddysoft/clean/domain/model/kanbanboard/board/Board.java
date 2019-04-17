package tw.teddysoft.clean.domain.model.kanbanboard.board;

import tw.teddysoft.clean.domain.model.DomainEventPublisher;
import tw.teddysoft.clean.domain.model.Entity;
import tw.teddysoft.clean.domain.model.kanbanboard.stage.Stage;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class Board extends Entity {

    private Set<BoardStage> boardStages;

    public Board(String name) {
        super(name);
        boardStages = new HashSet<BoardStage>();

        DomainEventPublisher
                .instance()
                .publish(new BoardCreated(
                        this.getId(),
                        this.getName()));
    }

    public Stage createStage(String stageName) {
        Stage stage = new Stage(stageName, this.getId());
        return stage;
    }

    public void addStage(Stage stage) {
        BoardStage boardStage = new BoardStage(this.getId(), stage.getId());
        boardStage.setOrdering(boardStages.size() + 1);
        boardStages.add(boardStage);
    }

    public Set<BoardStage> getBoardStages() {
        return Collections.unmodifiableSet(boardStages);
    }


    public int getStageOrderingByStageId(String id){
        for (BoardStage each : boardStages){
            if (each.getStageId().equalsIgnoreCase(id)){
                return each.getOrdering();
            }
        }
        return 0;
    }

    public void reorderBoardStage(String stageId, int oldOrdering, int newOrdering) {
        if(isMoveToSameOrdering(newOrdering, oldOrdering))
            return;

        for(BoardStage each : boardStages){
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


    private boolean inForwardAffectedZone(int newOrdering, int oldOrdering, BoardStage each){
        return each.getOrdering() >= newOrdering && each.getOrdering() < oldOrdering;
    }

    private boolean inBackwardAffectedZone(int newOrdering, int oldOrdering, BoardStage each){
        return each.getOrdering() > oldOrdering && each.getOrdering() <= newOrdering;
    }


    private boolean inForwardFreeZone(int newOrdering, int oldOrdering, BoardStage each) {
        return each.getOrdering() < newOrdering || each.getOrdering() > oldOrdering;
    }

    private boolean inBackwardFreeZone(int newOrdering, int oldOrdering, BoardStage each){
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
        for (BoardStage each : boardStages) {
            sb.append("BoardStage");
            sb.append(String.format("[ordering]=%-16s", each.getOrdering()));
                sb.append(String.format("[stage id]=%-40s", each.getStageId()));
//                sb.append(String.format("[board id]=%-40s", each.getBoardId()));
                sb.append("\n");
        }

        System.out.println("Dump BoardStages => \n" + sb.toString());

        return sb.toString();
    }
}
