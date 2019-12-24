package tw.teddysoft.clean.usecase.kanbanboard.old_stage.add;

import tw.teddysoft.clean.domain.usecase.Output;

public interface AddStageOutput extends Output {
    String getStageName();
    void setStageName(String stageName);
    String getStageId();
    void setStageId(String stageId);

    String getMiniStageId();
    void setMiniStageId(String miniStageId);
}
