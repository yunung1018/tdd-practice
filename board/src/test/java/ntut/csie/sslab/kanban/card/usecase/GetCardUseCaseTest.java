package ntut.csie.sslab.kanban.card.usecase;


import ntut.csie.sslab.kanban.card.adapter.out.presenter.get.CardViewModel;
import ntut.csie.sslab.kanban.card.adapter.out.presenter.get.GetCardPresenter;
import ntut.csie.sslab.kanban.card.entity.CardType;
import ntut.csie.sslab.kanban.common.usecase.AbstractSpringBootJpaTest;
import ntut.csie.sslab.kanban.card.usecase.port.in.get.GetCardInput;
import ntut.csie.sslab.kanban.card.usecase.port.in.get.GetCardUseCase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class GetCardUseCaseTest extends AbstractSpringBootJpaTest {

    @BeforeEach
    public void setUp() {
        super.setUp();
    }

    @Test
    public void should_return_card_read_model_when_get_card_by_id() {

        String firstCardId = createCard(
                userId,
                workflowId,
                rootStageId,
                "firstCard",
                "estimate",
                "notes",
                "2020/03/01",
                CardType.General.toString(),
                username,
                boardId);

        GetCardUseCase getCardUseCase = newGetCardByIdUseCase();
        GetCardInput input = new GetCardInput();
        input.setCardId(firstCardId);

        GetCardPresenter presenter = new GetCardPresenter();
        CardViewModel viewModel = presenter.buildViewModel(getCardUseCase.execute(input));

        assertEquals(userId, viewModel.getCard().getUserId());
        assertEquals(firstCardId, viewModel.getCard().getCardId());
        assertEquals(rootStageId, viewModel.getCard().getLaneId());
        assertEquals("firstCard", viewModel.getCard().getDescription());
        assertEquals("estimate", viewModel.getCard().getEstimate());
        assertEquals("notes", viewModel.getCard().getNotes());
        assertEquals("2020/03/01",  viewModel.getCard().getDeadline());
        assertEquals(CardType.General, viewModel.getCard().getType());

    }
}