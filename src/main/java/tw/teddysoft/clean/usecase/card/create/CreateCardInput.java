package tw.teddysoft.clean.usecase.card.create;

import tw.teddysoft.clean.domain.usecase.Input;

public interface CreateCardInput extends Input {

    CreateCardInput setName(String name);
    CreateCardInput setBoardId(String boardId);
    CreateCardInput setWorkflowId(String workflowId);
    CreateCardInput setLaneId(String laneId);


    String getName();
    String getBoardId();
    String getWorkflowId();
    String getLaneId();
}
