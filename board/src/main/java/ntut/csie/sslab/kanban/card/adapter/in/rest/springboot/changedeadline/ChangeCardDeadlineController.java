package ntut.csie.sslab.kanban.adapter.controller.rest.springboot.card.changedeadline;

import ntut.csie.sslab.ddd.adapter.presenter.cqrs.CqrsCommandPresenter;
import ntut.csie.sslab.ddd.adapter.presenter.CommonViewModel;
import ntut.csie.sslab.kanban.usecase.card.edit.deadline.in.ChangeCardDeadlineInput;
import ntut.csie.sslab.kanban.usecase.card.edit.deadline.in.ChangeCardDeadlineUseCase;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.ws.rs.*;

@RestController
public class ChangeCardDeadlineController {
    private final ChangeCardDeadlineUseCase changeCardDeadlineUseCase;

    @Autowired
    public ChangeCardDeadlineController(ChangeCardDeadlineUseCase changeCardDeadlineUseCase) {
        this.changeCardDeadlineUseCase = changeCardDeadlineUseCase;
    }

    @PutMapping(path = "${KANBAN_PREFIX}/cards/{cardId}/deadline", consumes = "application/json", produces = "application/json")
    public CommonViewModel changeCardDeadline(@PathVariable("cardId") String cardId,
                                              @QueryParam("username") String username,
                                              @QueryParam("userId") String userId,
                                              @QueryParam("boardId")String boardId,
                                              @RequestBody String cardInfo) {

        String newDeadline = "";


        try {
            JSONObject cardJSON = new JSONObject(cardInfo);
            newDeadline = cardJSON.getString("newDeadline");
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }

        ChangeCardDeadlineInput input = new ChangeCardDeadlineInput();
        input.setCardId(cardId);
        input.setNewDeadline(newDeadline);
        input.setUserId(userId);
        input.setUsername(username);
        input.setBoardId(boardId);

        CommonViewModel viewModel = CqrsCommandPresenter.newInstance().buildViewModel(changeCardDeadlineUseCase.execute(input));

        return viewModel;
    }
}
