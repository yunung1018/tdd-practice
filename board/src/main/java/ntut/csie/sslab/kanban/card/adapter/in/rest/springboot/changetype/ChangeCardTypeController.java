package ntut.csie.sslab.kanban.card.adapter.in.rest.springboot.changetype;

import ntut.csie.sslab.ddd.adapter.presenter.cqrs.CqrsCommandPresenter;
import ntut.csie.sslab.ddd.adapter.presenter.CommonViewModel;
import ntut.csie.sslab.kanban.card.usecase.port.in.type.ChangeCardTypeInput;
import ntut.csie.sslab.kanban.card.usecase.port.in.type.ChangeCardTypeUseCase;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.ws.rs.*;

@RestController
public class ChangeCardTypeController {
    private final ChangeCardTypeUseCase changeCardTypeUseCase;

    @Autowired
    public ChangeCardTypeController(ChangeCardTypeUseCase changeCardTypeUseCase) {
        this.changeCardTypeUseCase = changeCardTypeUseCase;
    }

    @PutMapping(path = "${KANBAN_PREFIX}/cards/{cardId}/type", consumes = "application/json", produces = "application/json")
    public CommonViewModel changeCardType(@PathVariable("cardId") String cardId,
                                          @QueryParam("username") String username,
                                          @QueryParam("userId") String userId,
                                          @QueryParam("boardId")String boardId,
                                          @RequestBody String cardInfo) {

        String newType = "";

        try {
            JSONObject cardJSON = new JSONObject(cardInfo);
            newType = cardJSON.getString("newType");
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }

        ChangeCardTypeInput input = new ChangeCardTypeInput();
        input.setCardId(cardId);
        input.setNewType(newType);
        input.setUserId(userId);
        input.setUsername(username);
        input.setBoardId(boardId);

        CommonViewModel viewModel = CqrsCommandPresenter.newInstance().buildViewModel(changeCardTypeUseCase.execute(input));

        return viewModel;
    }
}
