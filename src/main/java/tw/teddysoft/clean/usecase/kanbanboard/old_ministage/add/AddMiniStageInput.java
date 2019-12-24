package tw.teddysoft.clean.usecase.kanbanboard.old_ministage.add;

import tw.teddysoft.clean.domain.usecase.Input;

public interface AddMiniStageInput extends Input {

    void setMiniStateName(String name);

    void setStageId(String id);

    String getMiniStageName();

    String getStageId();
}
