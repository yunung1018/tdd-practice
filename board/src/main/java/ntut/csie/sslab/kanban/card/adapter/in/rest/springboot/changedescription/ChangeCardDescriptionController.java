package ntut.csie.sslab.kanban.adapter.controller.rest.springboot.card.changedescription;


import ntut.csie.sslab.ddd.adapter.presenter.cqrs.CqrsCommandPresenter;
import ntut.csie.sslab.ddd.adapter.presenter.CommonViewModel;
import ntut.csie.sslab.kanban.usecase.card.edit.description.in.ChangeCardDescriptionInput;
import ntut.csie.sslab.kanban.usecase.card.edit.description.in.ChangeCardDescriptionUseCase;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.ws.rs.*;

@RestController
public class ChangeCardDescriptionController {
    private final ChangeCardDescriptionUseCase changeCardDescriptionUseCase;

    @Autowired
    public ChangeCardDescriptionController(ChangeCardDescriptionUseCase changeCardDescriptionUseCase) {
        this.changeCardDescriptionUseCase = changeCardDescriptionUseCase;
    }

    @PutMapping(path = "${KANBAN_PREFIX}/cards/{cardId}/description", consumes = "application/json", produces = "application/json")
    public CommonViewModel changeCardDescription(@PathVariable("cardId") String cardId,
                                                 @QueryParam("userId")String userId,
                                                 @QueryParam("username")String username,
                                                 @QueryParam("boardId") String boardId,
                                                 @RequestBody String cardInfo) {

        String description = "";

        try {
            JSONObject cardJSON = new JSONObject(cardInfo);
            description = cardJSON.getString("description");
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }

        ChangeCardDescriptionInput input = new ChangeCardDescriptionInput();
        input.setCardId(cardId);
        input.setDescription(description);
        input.setUserId(userId);
        input.setUsername(username);
        input.setBoardId(boardId);

        CommonViewModel viewModel = CqrsCommandPresenter.newInstance().buildViewModel(changeCardDescriptionUseCase.execute(input));

        return viewModel;

    }

}
