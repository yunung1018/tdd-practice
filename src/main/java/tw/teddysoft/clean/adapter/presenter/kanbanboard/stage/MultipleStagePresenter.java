package tw.teddysoft.clean.adapter.presenter.kanbanboard.stage;

import tw.teddysoft.clean.usecase.kanbanboard.stage.get.GetStageOutput;
import tw.teddysoft.clean.usecase.kanbanboard.stage.get.StageDto;

import java.util.ArrayList;
import java.util.List;

public class MultipleStagePresenter implements GetStageOutput {

    private List<StageDto> stages;

    public MultipleStagePresenter(){
        super();
        stages = new ArrayList<StageDto>();
    }

    @Override
    public List<StageDto> getStageDtos() {
        return stages;
    }

    @Override
    public void setStageDtos(List<StageDto> stages) {
        this.stages = stages;
    }

    public StageDto findByStageName(String name){
        for(StageDto each : stages){
            if (each.getName().equals(name))
                return each;
        }
        throw new RuntimeException("Stage dto not found. Name = " + name);
    }


    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();

        for(StageDto each : stages){
            sb.append("Stage name : ").append(each.getName()).append(" [").append(each.getOrdering()).append("]");
            sb.append("id= ").append(each.getId()).append("\n");
            sb.append("     MiniStage : ").append(each.getMiniStageDtos().toString()).append("\n");
        }
        sb.append("\n");

        return sb.toString();
    }



}
