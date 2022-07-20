package ntut.csie.sslab.kanban.usecase.card;



import ntut.csie.sslab.ddd.usecase.cqrs.CqrsCommandOutput;
import ntut.csie.sslab.ddd.usecase.cqrs.ExitCode;
import ntut.csie.sslab.kanban.card.entity.Card;
import ntut.csie.sslab.kanban.card.entity.CardType;
import ntut.csie.sslab.kanban.usecase.AbstractSpringBootJpaTest;
import ntut.csie.sslab.kanban.card.usecase.create.in.CreateCardInput;
import ntut.csie.sslab.kanban.card.usecase.create.in.CreateCardUseCase;
import ntut.csie.sslab.kanban.workflow.entity.Lane;
import ntut.csie.sslab.kanban.workflow.entity.LaneType;
import ntut.csie.sslab.kanban.workflow.entity.SwimLane;
import ntut.csie.sslab.kanban.workflow.entity.Workflow;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;


public class CreateCardUseCaseTest extends AbstractSpringBootJpaTest {


    @BeforeEach
    public void setUp() {
        super.setUp();

        workflowId = "workflow id for create card";
        rootStageId = "root stage id for create card";
        userId = "user id for create card";
        username = "Teddy";
        boardId = "board id for create card";
    }


    @Test
    public void should_succeed_when_create_card_in_root_stage() {

        CreateCardUseCase createCardUseCase = newCreateCardUseCase();
        CreateCardInput createCardInput = new CreateCardInput();
        createCardInput.setWorkflowId(workflowId);
        createCardInput.setLaneId(rootStageId);
        createCardInput.setDescription("firstCard");
        createCardInput.setEstimate("xl");
        createCardInput.setNote("firstNotes");
        createCardInput.setDeadline("2020/03/11");
        createCardInput.setType(CardType.General.toString());
        createCardInput.setUserId(userId);
        createCardInput.setUsername(username);
        createCardInput.setBoardId(boardId);

        CqrsCommandOutput output = createCardUseCase.execute(createCardInput);

        assertNotNull(output.getId());
        assertEquals(ExitCode.SUCCESS, output.getExitCode());
        Card card = cardRepository.findById(output.getId()).get();
        assertNotNull(card);
        assertEquals(userId, card.getUserId());
        assertEquals(boardId, card.getBoardId());
        assertEquals(rootStageId, card.getLaneId());
        assertEquals("firstCard", card.getDescription());
        assertEquals("xl", card.getEstimate());
        assertEquals("firstNotes", card.getNotes());
        assertEquals("2020/03/11", card.getDeadline());
        assertEquals(CardType.General, card.getType());
    }


    @Test
    public void should_succeed_when_create_card_in_lane() {

        workflowId = createWorkflow(boardId, "my workflow", userId, username);
        rootStageId = createStage(boardId, userId, username, workflowId, "-1", "rootStage", -1, LaneType.Standard.toString());

        String swimLaneId = createSwimLane(
                workflowId,
                rootStageId,
                "firstSwimLane",
                -1,
                LaneType.Standard.toString(),
                userId,
                username,
                boardId);

        Workflow workflow = workflowRepository.findById(workflowId).get();
        assertEquals(1, workflow.getStages().size());

        String firstCardId = createCard(
                "userId",
                workflowId,
                rootStageId,
                "firstCard",
                "estimate",
                "notes",
                "2020/03/01",
                CardType.General.toString(),
                username,
                boardId);

        Lane rootStage = workflow.getLaneById(rootStageId).get();

        workflow.commitCardInLane(firstCardId, rootStageId, 0, userId, username, boardId);
        workflowRepository.save(workflow);

        workflow = workflowRepository.findById(workflowId).get();
        rootStage = workflow.getLaneById(rootStageId).get();
        assertEquals(1, rootStage.getCommittedCards().size());

        String secondCardId = createCard(
                "userId",
                workflowId,
                rootStageId,
                "secondCard",
                "estimate",
                "notes",
                "2020/03/02",
                CardType.General.toString(),
                username,
                boardId);

        workflow = workflowRepository.findById(workflowId).get();
        rootStage = workflow.getLaneById(rootStageId).get();

        workflow.commitCardInLane(secondCardId, rootStageId, 1, userId, username, boardId);
        workflowRepository.save(workflow);


        assertEquals(2, rootStage.getCommittedCards().size());

        String thirdCardId = createCard(
                "userId",
                workflowId,
                swimLaneId,
                "thirdCard",
                "estimate",
                "notes",
                "2020/03/10",
                CardType.General.toString(),
                username,
                boardId);

        workflow = workflowRepository.findById(workflowId).get();
        SwimLane swimLane = (SwimLane) workflow.getLaneById(swimLaneId).get();
        workflow.commitCardInLane(thirdCardId, swimLaneId, 0, userId, username, boardId);
        workflowRepository.save(workflow);
        workflow = workflowRepository.findById(workflowId).get();

        assertEquals(1, swimLane.getCommittedCards().size());

        String forthCardId = createCard(
                "userId",
                workflowId,
                swimLaneId,
                "forthCard",
                "estimate",
                "notes",
                "2020/03/10",
                CardType.General.toString(),
                username,
                boardId);

        workflow = workflowRepository.findById(workflowId).get();
        swimLane = (SwimLane) workflow.getLaneById(swimLaneId).get();
        workflow.commitCardInLane(forthCardId, swimLaneId, 1, userId, username, boardId);
        workflowRepository.save(workflow);

        assertEquals(2, swimLane.getCommittedCards().size());
    }
}