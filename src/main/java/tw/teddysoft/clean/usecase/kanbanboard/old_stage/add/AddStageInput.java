package tw.teddysoft.clean.usecase.kanbanboard.old_stage.add;

import tw.teddysoft.clean.domain.usecase.Input;

public interface AddStageInput extends Input {
    String getStageName();
    void setStageName(String stageName);
    String getBoardId();
    void setBoardId(String boardId);
}
