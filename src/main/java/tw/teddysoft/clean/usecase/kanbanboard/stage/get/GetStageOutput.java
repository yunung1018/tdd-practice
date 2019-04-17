package tw.teddysoft.clean.usecase.kanbanboard.stage.get;

import tw.teddysoft.clean.domain.usecase.Output;

import java.util.List;

public interface GetStageOutput extends Output {
    List<StageDto> getStageDtos();
    void setStageDtos(List<StageDto> stages);
    public StageDto findByStageName(String name);
    String toString();

//    List<Stage> getStages();
//    void setStages(List<Stage> stages);

}
