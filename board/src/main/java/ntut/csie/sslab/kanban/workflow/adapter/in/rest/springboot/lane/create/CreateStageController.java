package ntut.csie.sslab.kanban.adapter.controller.rest.springboot.lane.create;

import ntut.csie.sslab.ddd.adapter.presenter.cqrs.CqrsCommandPresenter;
import ntut.csie.sslab.ddd.adapter.presenter.CommonViewModel;
import ntut.csie.sslab.kanban.usecase.lane.stage.create.in.CreateStageInput;
import ntut.csie.sslab.kanban.usecase.lane.stage.create.in.CreateStageUseCase;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.ws.rs.QueryParam;

@RestController
public class CreateStageController {
    private final CreateStageUseCase createStageUseCase;

    @Autowired
    public CreateStageController(CreateStageUseCase createStageUseCase) {
        this.createStageUseCase = createStageUseCase;
    }

    @PostMapping(path = "${KANBAN_PREFIX}/workflows/{workflowId}/stages", consumes = "application/json", produces = "application/json")
    public CommonViewModel createStage(@PathVariable("workflowId") String workflowId,
                                       @QueryParam("userId") String userId,
                                       @QueryParam("username") String username,
                                       @QueryParam("boardId") String boardId,
                                       @RequestBody String stageInfo) {


        String name = "";
        int wipLimit = -1;
        String laneType = "";
        String parentId = "";
        try {
            JSONObject json = new JSONObject(stageInfo);
            name = json.getString("name");
            wipLimit= json.getInt("wipLimit");
            laneType= json.getString("laneType");
            parentId= json.getString("parentId");
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }

        CreateStageInput input = new CreateStageInput();
        input.setWorkflowId(workflowId);
        input.setName(name);
        input.setWipLimit(wipLimit);
        input.setLaneType(laneType);
        input.setParentId(parentId);
        input.setUserId(userId);
        input.setUsername(username);
        input.setBoardId(boardId);

        CqrsCommandPresenter presenter = CqrsCommandPresenter.newInstance();

        CommonViewModel viewModel = CqrsCommandPresenter.newInstance().buildViewModel(createStageUseCase.execute(input));

        return viewModel;
    }
}
