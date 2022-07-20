package ntut.csie.sslab.kanban.card.adapter.in.rest.springboot.create;

import ntut.csie.sslab.ddd.adapter.presenter.cqrs.CqrsCommandPresenter;
import ntut.csie.sslab.ddd.adapter.presenter.CommonViewModel;
import ntut.csie.sslab.kanban.card.usecase.port.in.create.CreateCardInput;
import ntut.csie.sslab.kanban.card.usecase.port.in.create.CreateCardUseCase;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.ws.rs.*;

@RestController
public class CreateCardController {
	private final CreateCardUseCase createCardUseCase;

	@Autowired
	public CreateCardController(CreateCardUseCase createCardUseCase) {
		this.createCardUseCase = createCardUseCase;
	}

	@PostMapping(path = "${KANBAN_PREFIX}/cards", consumes = "application/json", produces = "application/json")
	public CommonViewModel createCard(@QueryParam("workflowId") String workflowId,
									  @QueryParam("laneId") String laneId,
									  @QueryParam("userId") String userId,
									  @QueryParam("username") String username,
									  @QueryParam("boardId") String boardId,
									  @RequestBody String cardInfo) {

		String description = "";
		String estimate = "";
		String note = "";
		String deadline = "";
		String type = "";

		try {
			JSONObject cardJSON = new JSONObject(cardInfo);
			description = cardJSON.getString("description");
			estimate = cardJSON.getString("estimate");
			note = cardJSON.getString("note");
			deadline = cardJSON.getString("deadline");
			type = cardJSON.getString("type");
		} catch (JSONException e) {
			throw new RuntimeException(e);
		}

		CreateCardInput input = new CreateCardInput();
		input.setWorkflowId(workflowId);
		input.setLaneId(laneId);
		input.setUserId(userId);
		input.setDescription(description);
		input.setEstimate(estimate);
		input.setNote(note);
		input.setDeadline(deadline);
		input.setType(type);
		input.setUsername(username);
		input.setBoardId(boardId);

		CommonViewModel viewModel = CqrsCommandPresenter.newInstance().buildViewModel(createCardUseCase.execute(input));

		return viewModel;
	}
}
