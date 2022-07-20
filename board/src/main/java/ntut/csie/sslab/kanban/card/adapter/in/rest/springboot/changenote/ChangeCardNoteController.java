package ntut.csie.sslab.kanban.card.adapter.in.rest.springboot.changenote;


import ntut.csie.sslab.ddd.adapter.presenter.cqrs.CqrsCommandPresenter;
import ntut.csie.sslab.ddd.adapter.presenter.CommonViewModel;
import ntut.csie.sslab.kanban.card.usecase.port.in.note.ChangeCardNoteUseCase;
import ntut.csie.sslab.kanban.card.usecase.port.in.note.ChangeCardNoteInput;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.ws.rs.*;

@RestController
public class ChangeCardNoteController {
    private final ChangeCardNoteUseCase changeCardNoteUseCase;

    @Autowired
    public ChangeCardNoteController(ChangeCardNoteUseCase changeCardNoteUseCase) {
        this.changeCardNoteUseCase = changeCardNoteUseCase;
    }

    @PutMapping(path = "${KANBAN_PREFIX}/cards/{cardId}/note", consumes = "application/json", produces = "application/json")
    public CommonViewModel changeCardNote(@PathVariable("cardId") String cardId,
                                          @QueryParam("userId") String userId,
                                          @QueryParam("username") String username,
                                          @QueryParam("boardId") String boardId,
                                          @RequestBody String cardInfo) {

        String newNotes = "";

        try {
            JSONObject cardJSON = new JSONObject(cardInfo);
            newNotes = cardJSON.getString("newNotes");
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }

        ChangeCardNoteInput input = new ChangeCardNoteInput();
        input.setCardId(cardId);
        input.setNewNotes(newNotes);
        input.setUserId(userId);
        input.setUsername(username);
        input.setBoardId(boardId);

        CommonViewModel viewModel = CqrsCommandPresenter.newInstance().buildViewModel(changeCardNoteUseCase.execute(input));

        return viewModel;
    }
}
