package ntut.csie.sslab.kanban.adapter.controller.rest.springboot.lane.create;

import ntut.csie.sslab.ddd.adapter.presenter.cqrs.CqrsCommandPresenter;
import ntut.csie.sslab.ddd.adapter.presenter.CommonViewModel;
import ntut.csie.sslab.kanban.usecase.lane.swimLane.create.in.CreateSwimLaneInput;
import ntut.csie.sslab.kanban.usecase.lane.swimLane.create.in.CreateSwimLaneUseCase;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.ws.rs.QueryParam;

@RestController
public class CreateSwimLaneController {
    private final CreateSwimLaneUseCase createSwimLaneUseCase;

    @Autowired
    public CreateSwimLaneController(CreateSwimLaneUseCase createSwimLaneUseCase) {
        this.createSwimLaneUseCase = createSwimLaneUseCase;
    }

    @PostMapping(path = "${KANBAN_PREFIX}/workflows/{workflowId}/swimlanes", consumes = "application/json", produces = "application/json")
    public CommonViewModel createSwimLane(@PathVariable("workflowId") String workflowId,
                                          @QueryParam("userId") String userId,
                                          @QueryParam("username") String username,
                                          @QueryParam("boardId") String boardId,
                                          @RequestBody String swimLaneInfo) {


        String name = "";
        int wipLimit = -1;
        String laneType = "";
        String parentId = "";


        try {
            JSONObject swimLaneJSON = new JSONObject(swimLaneInfo);
            name = swimLaneJSON.getString("name");
            wipLimit= swimLaneJSON.getInt("wipLimit");
            laneType= swimLaneJSON.getString("laneType");
            parentId= swimLaneJSON.getString("parentId");
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }

        CreateSwimLaneInput input = new CreateSwimLaneInput();
        input.setWorkflowId(workflowId);
        input.setParentId(parentId);
        input.setName(name);
        input.setWipLimit(wipLimit);
        input.setLaneType(laneType);
        input.setUserId(userId);
        input.setUsername(username);
        input.setBoardId(boardId);

        CommonViewModel viewModel = CqrsCommandPresenter.newInstance().buildViewModel(createSwimLaneUseCase.execute(input));

        return viewModel;
    }
}
