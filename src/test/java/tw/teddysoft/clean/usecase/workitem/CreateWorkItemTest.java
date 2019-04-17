package tw.teddysoft.clean.usecase.workitem;

import org.junit.Before;
import org.junit.Test;
import tw.teddysoft.clean.adapter.presenter.workitem.SingleWorkItemPresenter;
import tw.teddysoft.clean.domain.model.kanbanboard.WipLimitExceedException;
import tw.teddysoft.clean.domain.model.kanbanboard.stage.Stage;
import tw.teddysoft.clean.domain.model.kanbanboard.stage.SwimLane;
import tw.teddysoft.clean.domain.model.workitem.WorkItem;
import tw.teddysoft.clean.usecase.KanbanTestUtility;
import tw.teddysoft.clean.usecase.workitem.add.CreateWorkItemInput;
import tw.teddysoft.clean.usecase.workitem.add.CreateWorkItemOutput;
import tw.teddysoft.clean.usecase.workitem.add.CreateWorkItemUseCase;
import tw.teddysoft.clean.usecase.workitem.add.impl.CreateWorkItemUseCaseImpl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

public class CreateWorkItemTest {

    private static final String EMPTY_STRING = "";

    private KanbanTestUtility util;
    private WorkItem applePay;
    private WorkItem linePay;

    @Before
    public void setUp(){
        util = new KanbanTestUtility();
        util.createScrumBoardAndStage();
        util.createKanbanBoardAndStage();

        applePay = new WorkItem("Implement Apple pay", "", "","");
        linePay = new WorkItem("Implement Line pay", "", "","");

        assertEquals(KanbanTestUtility.TOTAL_STAGE_NUMBER, util.getStageRepository().findAll().size());
    }

    @Test
    public void create_a_workitem_does_not_committed_it_to_its_swimlane() throws WipLimitExceedException {

        Stage todo = util.getStageRepository().findFirstByName("To Do");
        SwimLane swimLane = todo.getDefaultSwimLaneOfMiniStage();

        CreateWorkItemUseCase createWorkItemUseCase = new CreateWorkItemUseCaseImpl(util.getWorkItemRepository());
        CreateWorkItemInput input = CreateWorkItemUseCaseImpl.createInput() ;
        CreateWorkItemOutput output = new SingleWorkItemPresenter();

        input.setName("Implement apply pay");
        input.setStageId(todo.getId());
        input.setMiniStageId(todo.getDefaultMiniStage().getId());
        input.setSwimLaneId(swimLane.getId());

        createWorkItemUseCase.execute(input, output);

        assertEquals(util.getWorkItemRepository().findFirstByName("Implement apply pay").getId(), output.getId());
        assertEquals("Implement apply pay", output.getName());
        assertFalse(output.hasError());
        assertEquals(EMPTY_STRING, output.getErrorMessage());

        assertEquals(0, swimLane.getCommittedWorkItems().size());
    }

}
