package tw.teddysoft.clean.app.console.spring.shell.stage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import tw.teddysoft.clean.usecase.kanbanboard.stage.StageRepository;
import tw.teddysoft.clean.usecase.kanbanboard.stage.add.AddStageInput;
import tw.teddysoft.clean.usecase.kanbanboard.stage.add.AddStageOutput;
import tw.teddysoft.clean.usecase.kanbanboard.stage.add.AddStageUseCase;
import tw.teddysoft.clean.usecase.kanbanboard.stage.get.*;

@ShellComponent
public class ListStageCommand {



    @Autowired
    StageRepository stageRepository;

    @Autowired
    GetStageUseCase getStageUseCase;
    @Autowired
    GetStageInput getStageInput;
    @Autowired
    GetStageOutput getStageOutput;

    @Autowired
    AddStageUseCase addStageUseCase;
    @Autowired
    AddStageInput addStageInput;
    @Autowired
    AddStageOutput addStageOutput;


    @ShellMethod(value="stage command", key={"list-stage", "ls"})
    public String getStages(
            @ShellOption(value={"-b", "--board-id"}, defaultValue= CommandConst.BOARD_ID_ALL) String boardId,
            @ShellOption(value={"-d", "--dump"}, defaultValue="false") boolean isDump
            )
    {

        if (boardId.equalsIgnoreCase(CommandConst.BOARD_ID_ALL))
            getStageInput.setFindAll(true);
        else
            getStageInput.setFindAll(false);

        getStageInput.setBoardId(boardId);
        getStageUseCase.execute(getStageInput, getStageOutput);

        if(0 == getStageOutput.getStageDtos().size())
            return "No stage found.";

        StringBuilder sb = new StringBuilder();
        for (StageDto each : getStageOutput.getStageDtos()) {
            sb.append(String.format("%-20s", each.getName() + "[" + each.getOrdering() + "]"));
            if(isDump){
                sb.append(String.format("[id]=%-40s", each.getId()));
                sb.append(String.format("[board id]=%-40s", each.getBoardId()));

                for(MiniStageDto miniStage : each.getMiniStageDtos()){

                    sb.append("\n");
                    sb.append(String.format("%16s %-14s", "[mini stage]=", miniStage.getName()));
                    sb.append(String.format("[id]=%-40s", miniStage.getId()));

                    for(SwimLaneDto swimLane : miniStage.getSwimLaneDtos()){
                        sb.append("\n");
//                        sb.append(String.format("[swim lane]=%-16s", swimLane.getId()));
                        sb.append(String.format("%20s %-40s", "[swim lane id]=", miniStage.getId()));
                    }
                }

                sb.append("\n");
            }
        }
        return sb.toString();

    }



// Refactoring example

//    @NotNull
//    private String getStageByBoardId(String boardId, boolean isDump) {
//        getStageInput.setBoardId(boardId);
//        getStageUseCase.execute(getStageInput, getStageOutput);
//
//        if(0 == getStageOutput.getStageDtos().size())
//            return "No stage found.";
//
//        StringBuilder sb = new StringBuilder();
//        for (StageDto each : getStageOutput.getStageDtos()) {
//            sb.append(String.format("%-16s", each.getName()));
//            if(isDump){
//                sb.append(String.format("[id]=%-40s", each.getId()));
//                sb.append(String.format("[board id]=%-40s", each.getBoardId()));
//                sb.append("\n");
//            }
//        }
//        return sb.toString();
//    }
//
//
//    private String getAllStages(boolean isDump) {
//        StringBuilder sb = new StringBuilder();
//        if(0 == stageRepository.setFindAll().size())
//            return "No stage found.";
//
//        for (Stage each : stageRepository.setFindAll()) {
//            sb.append(String.format("%-16s", each.getName()));
//            if(isDump){
//                sb.append(String.format("[id]=%-40s", each.getId()));
//                sb.append(String.format("[board id]=%-40s", each.getBoardId()));
//                sb.append("\n");
//            }
//
//        }
//        return sb.toString();
//    }

}
