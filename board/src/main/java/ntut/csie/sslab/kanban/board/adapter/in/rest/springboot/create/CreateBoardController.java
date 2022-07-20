package ntut.csie.sslab.kanban.board.adapter.in.rest.springboot.create;

import ntut.csie.sslab.ddd.adapter.presenter.cqrs.CqrsCommandPresenter;
import ntut.csie.sslab.ddd.adapter.presenter.CommonViewModel;
import ntut.csie.sslab.kanban.board.usecase.port.in.create.CreateBoardInput;
import ntut.csie.sslab.kanban.board.usecase.port.in.create.CreateBoardUseCase;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.ws.rs.*;

@RestController
public class CreateBoardController {
    private CreateBoardUseCase createBoardUseCase;

    @Autowired
    public void setCreateBoardUseCase(CreateBoardUseCase createBoardUseCase) {
        this.createBoardUseCase = createBoardUseCase;
    }

    @PostMapping(path = "${KANBAN_PREFIX}/teams/{teamId}/boards", consumes = "application/json", produces = "application/json")
    public CommonViewModel createBoard(@QueryParam("userId") String userId,
                                       @PathVariable("teamId") String teamId,
                                       @RequestBody String boardInfo) {

        String name = "";
        try {
            JSONObject boardJSON = new JSONObject(boardInfo);
            name = boardJSON.getString("name");
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }

        CreateBoardInput input = new CreateBoardInput();
        input.setName(name);
        input.setUserId(userId);
        input.setTeamId(teamId);

        CommonViewModel viewModel = CqrsCommandPresenter.newInstance().buildViewModel(createBoardUseCase.execute(input));

        return viewModel;
    }
}
