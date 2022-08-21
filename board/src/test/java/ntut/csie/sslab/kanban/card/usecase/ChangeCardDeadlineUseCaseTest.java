package ntut.csie.sslab.kanban.card.usecase;


import ntut.csie.sslab.ddd.usecase.cqrs.CqrsOutput;
import ntut.csie.sslab.ddd.usecase.cqrs.ExitCode;
import ntut.csie.sslab.kanban.card.entity.Card;
import ntut.csie.sslab.kanban.card.entity.CardType;
import ntut.csie.sslab.kanban.common.usecase.AbstractSpringBootJpaTest;
import ntut.csie.sslab.kanban.card.usecase.port.in.deadline.ChangeCardDeadlineInput;
import ntut.csie.sslab.kanban.card.usecase.port.in.deadline.ChangeCardDeadlineUseCase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ChangeCardDeadlineUseCaseTest extends AbstractSpringBootJpaTest {

    @BeforeEach
    public void setUp() {
        super.setUp();
        boardId = "board id for change card deadline";
        workflowId = "workflow id for change card deadline";
        rootStageId = "root stage id for change card deadline";
        userId = "user id for change card deadline";
        username = "Teddy";
    }

    @Test
    public void should_succeed_when_change_card_deadline() {

        String firstCardId = createCard(
                userId,
                workflowId,
                rootStageId,
                "firstCard",
                "s",
                "notes",
                "2020/03/01",
                CardType.General.toString(),
                username,
                boardId);

        String newDeadline = "2020/03/02";
        ChangeCardDeadlineUseCase changeCardDeadlineUseCase = newChangeCardDeadlineUseCase();

        ChangeCardDeadlineInput input = new ChangeCardDeadlineInput();
        input.setCardId(firstCardId);
        input.setNewDeadline(newDeadline);
        input.setUserId(userId);
        input.setUsername(username);
        input.setBoardId(boardId);

        CqrsOutput output = changeCardDeadlineUseCase.execute(input);

        assertEquals(firstCardId, output.getId());
        assertEquals(ExitCode.SUCCESS, output.getExitCode());
        Card card = cardRepository.findById(output.getId()).get();
        assertEquals(newDeadline, card.getDeadline());
    }
}