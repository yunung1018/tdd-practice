package tw.teddysoft.clean.usecase.kanbanboard.stage.get;

import tw.teddysoft.clean.domain.model.kanbanboard.old_stage.MiniStage;
import tw.teddysoft.clean.domain.model.kanbanboard.old_stage.Stage;
import tw.teddysoft.clean.domain.model.kanbanboard.old_stage.SwimLane;
import tw.teddysoft.clean.usecase.kanbanboard.board.BoardRepository;

import java.util.ArrayList;
import java.util.List;

public class DtoConvertor {

    public static List<StageDto> createStageDtoList(List<Stage> stages, BoardRepository boardRepository){
        List<StageDto> result = new ArrayList<>();
        for(Stage each : stages){
            boardRepository.findById(each.getBoardId()).getStageOrderingByStageId(each.getId());
            result.add(crateStageDto(each, boardRepository.findById(each.getBoardId()).getStageOrderingByStageId(each.getId())));
        }
        return result;
    }

    public static StageDto crateStageDto(Stage stage, int ordering){

        List<MiniStageDto> miniStageDtos = new ArrayList<>();

        for(MiniStage each : stage.getMiniStages()){
            miniStageDtos.add(new MiniStageDto(
                    each.getName(),
                    each.getId(),
                    createSwimLaneDtoList(each.getSwimLanes())));
        }

        StageDto result = new StageDto(
                stage.getName(),
                stage.getId(),
                ordering,
                stage.getBoardId(),
                miniStageDtos);

        return result;
    }

    public static SwimLaneDto createSwimLaneDto(SwimLane swimLane){
        return new SwimLaneDto(swimLane.getId());
    }

    public static List<SwimLaneDto> createSwimLaneDtoList(List<SwimLane> swimLanes){
        List<SwimLaneDto> results = new ArrayList<>();
        for(SwimLane each : swimLanes){
            results.add(createSwimLaneDto(each));
        }
        return results;
    }

}
