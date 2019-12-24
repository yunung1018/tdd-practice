package tw.teddysoft.clean.adapter.presenter.kanbanboard.stage;

import tw.teddysoft.clean.usecase.kanbanboard.old_stage.add.AddStageOutput;

public class SingleStagePresenter implements AddStageOutput {

    private String stageName;
    private String stageId;
    private String miniStageId;

    public String getStageName() {
        return stageName;
    }

    public void setStageName(String stageName) {
        this.stageName = stageName;
    }

    public String getStageId() {
        return stageId;
    }

    public void setStageId(String stageId) {
        this.stageId = stageId;
    }

    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("Stage Name: ").append(getStageName()).append("; ");
        sb.append("Stage Id: ").append(getStageId()).append(".");
        return sb.toString();
    }

    @Override
    public String getMiniStageId() {
        return miniStageId;
    }

    @Override
    public void setMiniStageId(String miniStageId) {
        this.miniStageId = miniStageId;
    }
}
