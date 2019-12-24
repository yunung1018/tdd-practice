package tw.teddysoft.clean.usecase.kanbanboard.board;

import static org.junit.Assert.assertEquals;

/*

Ready
Analysis
Development
Test
Ready to Deploy
Deployed

 */


public class ReorderStageOfBoardTest  {

//    private KanbanTestUtility util;
//    private MoveStageOfBoardUseCase moveStageOfBoardUseCase;
//    private MoveStageOfBoardOutput output;
//    private MoveStageOfBoardInput input;
//
//    @Before
//    public void setUp(){
//
//        util = new KanbanTestUtility();
//        util.createKanbanBoardAndStage();
//
//        moveStageOfBoardUseCase =
//                new MoveStageOfBoardUseCaseImpl(util.getBoardRepository(), util.getStageRepository());
//        input = MoveStageOfBoardUseCaseImpl.createInput();
//        input.setBoardId(util.getKanbanBoard().getId());
//
//    }
//
//    @Test
//    public void move_Analysis_at_position_2_to_Test_at_position_4() {
//        verifyStageOrdering(1, 2, 3, 4, 5, 6);
//        doReordering(2, 4);
//        verifyStageOrdering(1, 4, 2, 3, 5, 6);
//    }
//
//    @Test
//    public void move_Analysis_at_position_2_to_Ready_at_position_1() {
//        verifyStageOrdering(1, 2, 3, 4, 5, 6);
//        doReordering(2, 1);
//        verifyStageOrdering(2, 1, 3, 4, 5, 6);
//    }
//
//    @Test
//    public void move_Ready_at_position_1_to_Deployed_at_position_6() {
//        verifyStageOrdering(1, 2, 3, 4, 5, 6);
//        doReordering(1, 6);
//        verifyStageOrdering(6, 1, 2, 3, 4, 5);
//    }
//
//
//    @Test
//    public void move_Deployed_at_position_6_to_Ready_at_position_1() {
//        verifyStageOrdering(1, 2, 3, 4, 5, 6);
//        doReordering(6, 1);
//        verifyStageOrdering(2, 3, 4, 5, 6, 1);
//    }
//
//    private void doReordering(int oldOrdering, int newOrdering) {
//        Stage analysis = util.finFirstStageByName(KanbanTestUtility.KANBAN_STAGE_NAME_2_ANALYSIS);
//        input.setStageId(analysis.getId());
//        input.setOldOrdering(oldOrdering);
//        input.setNewOrdering(newOrdering);
//        moveStageOfBoardUseCase.execute(input, null);
//    }
//
//
//    private void verifyStageOrdering(int i1, int i2, int i3, int i4, int i5, int i6) {
//        GetStageOutput getStageOutput = getStageOutput();
//        assertEquals(i1, getStageOutput.findByStageName("Ready").getOrdering());
//        assertEquals(i2, getStageOutput.findByStageName("Analysis").getOrdering());
//        assertEquals(i3, getStageOutput.findByStageName("Development").getOrdering());
//        assertEquals(i4, getStageOutput.findByStageName("Test").getOrdering());
//        assertEquals(i5, getStageOutput.findByStageName("Ready to Deploy").getOrdering());
//        assertEquals(i6, getStageOutput.findByStageName("Deployed").getOrdering());
//    }
//
//    private GetStageOutput getStageOutput(){
//        GetStageUseCase getStageUseCase = new GetStageUseCaseImpl(util.getBoardRepository(), util.getStageRepository());
//        GetStageInput getStageInput = GetStageUseCaseImpl.createInput();
//        getStageInput.setBoardId(util.getKanbanBoard().getId());
//        GetStageOutput getStageOutput = new MultipleStagePresenter();
//        getStageUseCase.execute(getStageInput, getStageOutput);
//        return getStageOutput;
//    }


}
