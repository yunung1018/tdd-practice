package tw.teddysoft.clean.usecase.kanbanboard.old_ministage.update;

import tw.teddysoft.clean.domain.usecase.Input;

public interface UpdateMiniStageInput extends Input {
    String getStageId();
    String getMiniStageId();
    String getMiniStageName();

    void setStageId(String stateId);
    void setMiniStageId(String miniStageId);
    void setMiniStageName(String name);

}
