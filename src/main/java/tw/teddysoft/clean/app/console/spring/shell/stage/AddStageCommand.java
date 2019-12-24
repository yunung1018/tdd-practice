package tw.teddysoft.clean.app.console.spring.shell.stage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import tw.teddysoft.clean.usecase.kanbanboard.old_stage.add.AddStageInput;
import tw.teddysoft.clean.usecase.kanbanboard.old_stage.add.AddStageOutput;
import tw.teddysoft.clean.usecase.kanbanboard.old_stage.add.AddStageUseCase;

@ShellComponent
public class AddStageCommand {

    @Autowired
    AddStageUseCase addStageUseCase;

    @Autowired
    AddStageInput addStageInput;

    @Autowired
    AddStageOutput addStageOutput;

    @ShellMethod(value = "add stage to a board", key = {"add-stage", "as"})
    public String addStage(
            @ShellOption(value = {"-n", "--stage-name"}) String stageName,
            @ShellOption(value = {"-bid", "--board-id"}) String boardId) {

        addStageInput.setStageName(stageName);
        addStageInput.setBoardId(boardId);
        addStageUseCase.execute(addStageInput, addStageOutput);

        return "stage id: " + addStageOutput.getStageId() + " added. to board " + boardId;
    }

}
