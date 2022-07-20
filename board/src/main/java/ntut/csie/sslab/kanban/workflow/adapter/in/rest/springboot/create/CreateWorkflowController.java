package ntut.csie.sslab.kanban.adapter.controller.rest.springboot.workflow.create;

import ntut.csie.sslab.ddd.adapter.presenter.cqrs.CqrsCommandPresenter;
import ntut.csie.sslab.ddd.adapter.presenter.CommonViewModel;
import ntut.csie.sslab.kanban.usecase.workflow.create.in.CreateWorkflowInput;
import ntut.csie.sslab.kanban.usecase.workflow.create.in.CreateWorkflowUseCase;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.ws.rs.QueryParam;
@RestController
public class CreateWorkflowController {
        private final CreateWorkflowUseCase createWorkflowUseCase;

        @Autowired
        public CreateWorkflowController(CreateWorkflowUseCase createWorkflowUseCase) {
            this.createWorkflowUseCase = createWorkflowUseCase;
        }

        @PostMapping(path = "${KANBAN_PREFIX}/workflows", consumes = "application/json", produces = "application/json")
        public CommonViewModel createWorkflow(@QueryParam("boardId") String boardId,
                                              @QueryParam("userId") String userId,
                                              @QueryParam("username") String username,
                                              @RequestBody String workflowInfo) {

            String name = "";
            try {
                JSONObject workflowJSON = new JSONObject(workflowInfo);
                name = workflowJSON.getString("name");
            } catch (JSONException e) {
                throw new RuntimeException(e);
            }

            CreateWorkflowInput input = new CreateWorkflowInput();
            input.setBoardId(boardId);
            input.setName(name);
            input.setUserId(userId);
            input.setUsername(username);

            CommonViewModel viewModel = CqrsCommandPresenter.newInstance().buildViewModel(createWorkflowUseCase.execute(input));

            return viewModel;
        }
}
