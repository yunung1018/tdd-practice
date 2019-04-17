package tw.teddysoft.clean.app.console.spring.shell.stage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import tw.teddysoft.clean.usecase.kanbanboard.ministage.update.UpdateMiniStageInput;
import tw.teddysoft.clean.usecase.kanbanboard.ministage.update.UpdateMiniStageOutput;
import tw.teddysoft.clean.usecase.kanbanboard.ministage.update.UpdateMiniStageUseCase;
import tw.teddysoft.clean.usecase.kanbanboard.stage.StageRepository;
import tw.teddysoft.clean.usecase.kanbanboard.stage.get.*;

@ShellComponent
public class UpdateMiniStageCommand {

    @Autowired
    StageRepository stageRepository;

    @Autowired
    UpdateMiniStageUseCase updateMiniStageUseCase;
    @Autowired
    UpdateMiniStageInput updateMiniStageInput;
    @Autowired
    UpdateMiniStageOutput updateMiniStageOutput;


    @ShellMethod(value="update the name of a ministage", key={"update-mini", "um"})
    public String update(
            @ShellOption(value={"-sid", "--stage-id"}) String stageId,
            @ShellOption(value={"-msid", "--ministage-id"}) String miniStageId,
            @ShellOption(value={"-n", "--ministage-name"}) String miniStageName
            )
    {
        updateMiniStageInput.setStageId(stageId);
        updateMiniStageInput.setMiniStageId(miniStageId);
        updateMiniStageInput.setMiniStageName(miniStageName);

        updateMiniStageUseCase.execute(updateMiniStageInput, null);

        return "Ministage [id=]" + stageId + " updated to a new name [" + miniStageName + "].";
    }

}
