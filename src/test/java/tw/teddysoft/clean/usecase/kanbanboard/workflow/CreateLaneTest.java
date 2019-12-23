package tw.teddysoft.clean.usecase.kanbanboard.workflow;

import org.junit.Before;
import org.junit.Test;
import tw.teddysoft.clean.domain.model.kanbanboard.workflow.Lane;
import tw.teddysoft.clean.domain.model.kanbanboard.workflow.LaneOrientation;
import tw.teddysoft.clean.domain.model.kanbanboard.workflow.Workflow;
import tw.teddysoft.clean.usecase.TestContext;
import tw.teddysoft.clean.usecase.kanbanboard.workspace.CreateWorkspaceTest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class CreateLaneTest {

    private TestContext context;
    private Workflow workflow;

    @Before
    public void setUp(){
        context = new TestContext();
        context.workspaceId = context.createWorkspace(CreateWorkspaceTest.USER_ID, CreateWorkspaceTest.WORKSPACE_NAME)
                .getWorkspaceId();

        context.boardId = context.createBoard(context.workspaceId, TestContext.SCRUM_BOARD_NAME).getBoardId();

        workflow = context.getWorkflowRepository().findAll().get(0);
    }

    @Test
    public void create_top_stage(){
        create_top_stage(workflow.getId(), "Backlog");
        assertEquals(1, workflow.getStages().size());
        assertEquals(LaneOrientation.VERTICAL, workflow.getStages().get(0).getOrientation());
        assertEquals("Backlog", workflow.getStages().get(0).getName());
    }

    @Test
    public void create_two_ministagelanes_under_the_stagelane_Backlog(){
        create_top_stage(workflow.getId(), "Backlog");
        Lane backlog = workflow.getStages().get(0);

        createStage(workflow.getId(), "Legend", backlog.getId());
        assertEquals(1, backlog.getSubLanes().size());
        assertEquals(LaneOrientation.VERTICAL, backlog.getSubLanes().get(0).getOrientation());
        assertEquals("Legend", backlog.getSubLanes().get(0).getName());

        createStage(workflow.getId(), "Ready", backlog.getId());
        assertEquals(LaneOrientation.VERTICAL, backlog.getSubLanes().get(1).getOrientation());
        assertEquals("Ready", backlog.getSubLanes().get(1).getName());
    }

    @Test
    public void create_two_swimlanes_under_the_stagelane_Backlog(){
        create_top_stage(workflow.getId(), "Backlog");
        Lane backlog = workflow.getStages().get(0);

        createSwimlane(workflow.getId(), "Top5", backlog.getId());
        assertEquals(1, backlog.getSubLanes().size());
        assertEquals(LaneOrientation.HORIZONTAL, backlog.getSubLanes().get(0).getOrientation());
        assertEquals("Top5", backlog.getSubLanes().get(0).getName());

        createSwimlane(workflow.getId(), "Idea", backlog.getId());
        assertEquals(2, backlog.getSubLanes().size());
        assertEquals(LaneOrientation.HORIZONTAL, backlog.getSubLanes().get(1).getOrientation());
        assertEquals("Idea", backlog.getSubLanes().get(1).getName());
    }



    @Test
    public void create_business_process_maintenance_stage(){
        // create a stage like : https://reurl.cc/1QEryG

        create_top_stage(workflow.getId(), "Operations - Business Process Maintenance");
        Lane operation = workflow.getStages().get(0);

        String productionProblemId = createSwimlane(workflow.getId(), "Production Problem", operation.getId());
            createStage(workflow.getId(), "New", productionProblemId);
            String workingID = createStage(workflow.getId(), "Working", productionProblemId);
                createStage(workflow.getId(), "Find Cause", workingID);
                createStage(workflow.getId(), "Fix Cause", workingID);
            createStage(workflow.getId(), "Done", productionProblemId);

        String plannedBusinessNeedId = createSwimlane(workflow.getId(), "Planned Business Need", operation.getId());
            String due2Months = createStage(workflow.getId(), "Due 2 months", plannedBusinessNeedId);
                createSwimlane(workflow.getId(), "High Impact", due2Months);
                createSwimlane(workflow.getId(), "Low Impact", due2Months);
            String due1Month = createStage(workflow.getId(), "Due 1 month", plannedBusinessNeedId);
                createSwimlane(workflow.getId(), "High Impact", due1Month);
                createSwimlane(workflow.getId(), "Low Impact", due1Month);
            String due1week = createStage(workflow.getId(), "Due 1 week", plannedBusinessNeedId);
                createSwimlane(workflow.getId(), "High Impact", due1week);
                createSwimlane(workflow.getId(), "Low Impact", due1week);
            String inWork = createStage(workflow.getId(), "In Work", plannedBusinessNeedId);
                createSwimlane(workflow.getId(), "High Impact", inWork);
                createSwimlane(workflow.getId(), "Low Impact", inWork);
            String done = createStage(workflow.getId(), "Done", plannedBusinessNeedId);
                createSwimlane(workflow.getId(), "High Impact", done);
                createSwimlane(workflow.getId(), "Low Impact", done);

        String routineId = createSwimlane(workflow.getId(), "Routine", operation.getId());

        String unplannedId = createSwimlane(workflow.getId(), "Unplanned", operation.getId());
            createStage(workflow.getId(), "New", unplannedId);
            createStage(workflow.getId(), "Committed", unplannedId);
            createStage(workflow.getId(), "In Work", unplannedId);
            createStage(workflow.getId(), "Test", unplannedId);
            createStage(workflow.getId(), "Done", unplannedId);

        String platformImprovementsId = createSwimlane(workflow.getId(), "Platform Improvements", operation.getId());
            createStage(workflow.getId(), "New", platformImprovementsId);
            createStage(workflow.getId(), "Committed", platformImprovementsId);
            createStage(workflow.getId(), "In Work", platformImprovementsId);
            createStage(workflow.getId(), "Test", platformImprovementsId);
            createStage(workflow.getId(), "Done", platformImprovementsId);

        workflow.dumpLane();
        assertEquals(36, workflow.getTotalLaneSize());
    }

    @Test
    public void create_devops_workflow(){
        // create a workflow like : https://reurl.cc/31W81L ,
        //                          https://reurl.cc/L1gvWL , and
        //                          https://reurl.cc/algpzG

        String devQueueId = create_top_stage(workflow.getId(), "Dev Queue");
            String helpId = createStage(workflow.getId(), "Help", devQueueId);
                createSwimlane(workflow.getId(), "Legend", helpId);
                createSwimlane(workflow.getId(), "Templates", helpId);
            createStage(workflow.getId(), "Backlog", devQueueId);
            createStage(workflow.getId(), "Up Next", devQueueId);

        String devInFightId = create_top_stage(workflow.getId(), "Dev - In Flight");
            String epicInFlightId = createSwimlane(workflow.getId(), "Epic - In Flight", devInFightId);
                createStage(workflow.getId(), "Breakdown", epicInFlightId);
                createStage(workflow.getId(), "Doing", epicInFlightId);
                createStage(workflow.getId(), "Pending Ops", epicInFlightId);

            String expeditedId = createSwimlane(workflow.getId(), "Expedited", devInFightId);
                createStage(workflow.getId(), "Doing", expeditedId);
                createStage(workflow.getId(), "Testing", expeditedId);
                createStage(workflow.getId(), "Review", expeditedId);

            String standardId = createSwimlane(workflow.getId(), "Standard", devInFightId);
                createStage(workflow.getId(), "Doing", standardId);
                createStage(workflow.getId(), "Testing", standardId);
                createStage(workflow.getId(), "Review", standardId);

        String operationsQueue = create_top_stage(workflow.getId(), "Operations Queue");
            String help = createStage(workflow.getId(), "Help", operationsQueue);
                createSwimlane(workflow.getId(), "Legend", help);
                 createSwimlane(workflow.getId(), "Templates", help);
            createStage(workflow.getId(), "Ops Backlog", operationsQueue);

        String opsInFlight = create_top_stage(workflow.getId(), "Ops - In Flight");
            createStage(workflow.getId(), "Up Next", opsInFlight);
        String doingId = createStage(workflow.getId(), "Doing", opsInFlight);
            String keepTheLightsOnId = createSwimlane(workflow.getId(), "Keep The Lights On", doingId);
                createStage(workflow.getId(), "Doing", keepTheLightsOnId);
                createStage(workflow.getId(), "Testing", keepTheLightsOnId);
        String expeditedId2 = createSwimlane(workflow.getId(), "Expedited", doingId);
            createStage(workflow.getId(), "Doing", expeditedId2);
            createStage(workflow.getId(), "Testing", expeditedId2);
        String standardId2 = createSwimlane(workflow.getId(), "Standard", doingId);
            createStage(workflow.getId(), "Doing", standardId2);
            createStage(workflow.getId(), "Testing", standardId2);

        create_top_stage(workflow.getId(), "Completed");

        String finishedId = create_top_stage(workflow.getId(), "Finished - Ready to Archive");
            createSwimlane(workflow.getId(), "Finished As Planned", finishedId);
            createSwimlane(workflow.getId(), "Started bu not Finished", finishedId);
            createSwimlane(workflow.getId(), "Discarded Requests / Ideas", finishedId);

        workflow.dumpLane();
        assertEquals(41, workflow.getTotalLaneSize());
    }


    private String createWorkflow(String boardId, String name) {
        return context.createWorkflow(boardId, name).getWorkflowId();
    }

    private String create_top_stage(String workflowId, String name){
        return createStage(workflowId, name, null);
    }

    private String createStage(String workflowId, String name, String parentId){
        return context.createStage(workflowId, name, parentId).getId();
    }

    private String createSwimlane(String workflowId, String name, String parentId){
        return context.createSwimlane(workflowId, name, parentId).getId();
    }

}
