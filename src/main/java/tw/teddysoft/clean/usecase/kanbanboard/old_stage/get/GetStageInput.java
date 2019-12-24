package tw.teddysoft.clean.usecase.kanbanboard.old_stage.get;

import tw.teddysoft.clean.domain.usecase.Input;

public interface GetStageInput extends Input {

    void setBoardId(String id);
    String getBoardId();
    void setFindAll(boolean arg);
    boolean isFindAll();

}
