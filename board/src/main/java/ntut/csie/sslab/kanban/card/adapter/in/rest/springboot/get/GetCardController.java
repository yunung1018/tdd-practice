package ntut.csie.sslab.kanban.adapter.controller.rest.springboot.card.get;

import ntut.csie.sslab.kanban.adapter.presenter.card.get.CardViewModel;
import ntut.csie.sslab.kanban.adapter.presenter.card.get.GetCardPresenter;
import ntut.csie.sslab.kanban.usecase.card.get.in.GetCardInput;
import ntut.csie.sslab.kanban.usecase.card.get.in.GetCardUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.ws.rs.QueryParam;


@RestController
public class GetCardController {
    private final GetCardUseCase getCardUseCase;

    @Autowired
    public GetCardController(GetCardUseCase getCardUseCase) {
        this.getCardUseCase = getCardUseCase;
    }

    @GetMapping(path = "${KANBAN_PREFIX}/cards/{cardId}", produces = "application/json")
    public CardViewModel getCard(@PathVariable("cardId") String cardId, @QueryParam("boardId") String boardId) {

        GetCardInput input = (GetCardInput) getCardUseCase;
        input.setCardId(cardId);
        GetCardPresenter presenter = new GetCardPresenter();

        CardViewModel viewModel = presenter.buildViewModel(getCardUseCase.execute(input));

        return viewModel;
    }
}
