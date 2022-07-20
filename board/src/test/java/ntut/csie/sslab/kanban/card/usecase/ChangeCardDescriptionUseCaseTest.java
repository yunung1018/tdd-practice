package ntut.csie.sslab.kanban.usecase.card;


import ntut.csie.sslab.ddd.usecase.cqrs.CqrsCommandOutput;
import ntut.csie.sslab.ddd.usecase.cqrs.ExitCode;
import ntut.csie.sslab.kanban.card.entity.Card;
import ntut.csie.sslab.kanban.card.entity.CardType;
import ntut.csie.sslab.kanban.usecase.AbstractSpringBootJpaTest;
import ntut.csie.sslab.kanban.card.usecase.edit.description.in.ChangeCardDescriptionInput;
import ntut.csie.sslab.kanban.card.usecase.edit.description.in.ChangeCardDescriptionUseCase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ChangeCardDescriptionUseCaseTest extends AbstractSpringBootJpaTest {

    @BeforeEach
    public void setUp() {
        super.setUp();
    }


    @Test
    public void should_succeed_when_change_card_description() {

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

        ChangeCardDescriptionUseCase changeCardDescriptionUseCase = newChangeCardDescriptionUseCase();
        ChangeCardDescriptionInput input = new ChangeCardDescriptionInput();
        input.setCardId(firstCardId);
        input.setDescription("EditedDescription");
        input.setBoardId(boardId);
        input.setUserId(userId);
        input.setUsername(username);

        CqrsCommandOutput output = changeCardDescriptionUseCase.execute(input);

        assertNotNull(output.getId());
        assertEquals(ExitCode.SUCCESS, output.getExitCode());
        Card card = cardRepository.findById(output.getId()).get();
        assertEquals("EditedDescription", card.getDescription());
    }
}