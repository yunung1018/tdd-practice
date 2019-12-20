package tw.teddysoft.clean.usecase.kanbanboard.workflow;

import org.junit.Before;
import org.junit.Test;
import tw.teddysoft.clean.adapter.gateway.kanbanboard.InMemoryStageRepository;
import tw.teddysoft.clean.adapter.gateway.kanbanboard.InMemoryWorkflowRepository;
import tw.teddysoft.clean.adapter.presenter.kanbanboard.lane.SingleStagePresenter;
import tw.teddysoft.clean.adapter.presenter.kanbanboard.workflow.SingleWorkflowPresenter;
import tw.teddysoft.clean.domain.model.kanbanboard.workflow.Lane;
import tw.teddysoft.clean.domain.model.kanbanboard.workflow.LaneOrientation;
import tw.teddysoft.clean.domain.model.kanbanboard.workflow.Workflow;
import tw.teddysoft.clean.usecase.KanbanTestUtility;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class CreateLaneTest {

    private WorkflowRepository repository;
    private Workflow workflow;

    @Before
    public void setUp(){
        repository = new InMemoryWorkflowRepository();
        String workflowId = create_workflow("000-1234", "Default");
        workflow = repository.findById(workflowId);
        assertNotNull(workflow);
    }

    @Test
    public void create_top_stage(){
        create_top_stage(workflow.getId(), "Backlog");
        assertEquals(1, workflow.getStages().size());
        assertEquals(LaneOrientation.VERTICAL, workflow.getStages().get(0).getOrientation());
        assertEquals("Backlog", workflow.getStages().get(0).getTitle());
    }


    @Test
    public void create_two_ministagelanes_under_the_stagelane_Backlog(){
        create_top_stage(workflow.getId(), "Backlog");
        Lane backlog = workflow.getStages().get(0);

        create_stage(workflow.getId(), "Legend", backlog.getId());
        assertEquals(1, backlog.getSubLanes().size());
        assertEquals(LaneOrientation.VERTICAL, backlog.getSubLanes().get(0).getOrientation());
        assertEquals("Legend", backlog.getSubLanes().get(0).getTitle());

        create_stage(workflow.getId(), "Ready", backlog.getId());
        assertEquals(LaneOrientation.VERTICAL, backlog.getSubLanes().get(1).getOrientation());
        assertEquals("Ready", backlog.getSubLanes().get(1).getTitle());
    }

    @Test
    public void create_two_swimlanes_under_the_stagelane_Backlog(){
        create_top_stage(workflow.getId(), "Backlog");
        Lane backlog = workflow.getStages().get(0);

        create_swimlane(workflow.getId(), "Top5", backlog.getId());
        assertEquals(1, backlog.getSubLanes().size());
        assertEquals(LaneOrientation.HORIZONTAL, backlog.getSubLanes().get(0).getOrientation());
        assertEquals("Top5", backlog.getSubLanes().get(0).getTitle());


        create_swimlane(workflow.getId(), "Idea", backlog.getId());
        assertEquals(2, backlog.getSubLanes().size());
        assertEquals(LaneOrientation.HORIZONTAL, backlog.getSubLanes().get(1).getOrientation());
        assertEquals("Idea", backlog.getSubLanes().get(1).getTitle());
    }



    @Test
    public void create_business_process_maintenance_stage(){
        // create a stage like : https://reurl.cc/1QEryG

        create_top_stage(workflow.getId(), "Operations - Business Process Maintenance");
        Lane operation = workflow.getStages().get(0);

        String productionProblemId = create_swimlane(workflow.getId(), "Production Problem", operation.getId());
            create_stage(workflow.getId(), "New", productionProblemId);
            String workingID = create_stage(workflow.getId(), "Working", productionProblemId);
                create_stage(workflow.getId(), "Find Cause", workingID);
                create_stage(workflow.getId(), "Fix Cause", workingID);
            create_stage(workflow.getId(), "Done", productionProblemId);

        String plannedBusinessNeedId = create_swimlane(workflow.getId(), "Planned Business Need", operation.getId());
            String due2Months = create_stage(workflow.getId(), "Due 2 months", plannedBusinessNeedId);
                create_swimlane(workflow.getId(), "High Impact", due2Months);
                create_swimlane(workflow.getId(), "Low Impact", due2Months);
            String due1Month = create_stage(workflow.getId(), "Due 1 month", plannedBusinessNeedId);
                create_swimlane(workflow.getId(), "High Impact", due1Month);
                create_swimlane(workflow.getId(), "Low Impact", due1Month);
            String due1week = create_stage(workflow.getId(), "Due 1 week", plannedBusinessNeedId);
                create_swimlane(workflow.getId(), "High Impact", due1week);
                create_swimlane(workflow.getId(), "Low Impact", due1week);
            String inWork = create_stage(workflow.getId(), "In Work", plannedBusinessNeedId);
                create_swimlane(workflow.getId(), "High Impact", inWork);
                create_swimlane(workflow.getId(), "Low Impact", inWork);
            String done = create_stage(workflow.getId(), "Done", plannedBusinessNeedId);
                create_swimlane(workflow.getId(), "High Impact", done);
                create_swimlane(workflow.getId(), "Low Impact", done);

        String routineId = create_swimlane(workflow.getId(), "Routine", operation.getId());

        String unplannedId = create_swimlane(workflow.getId(), "Unplanned", operation.getId());
            create_stage(workflow.getId(), "New", unplannedId);
            create_stage(workflow.getId(), "Committed", unplannedId);
            create_stage(workflow.getId(), "In Work", unplannedId);
            create_stage(workflow.getId(), "Test", unplannedId);
            create_stage(workflow.getId(), "Done", unplannedId);

        String platformImprovementsId = create_swimlane(workflow.getId(), "Platform Improvements", operation.getId());
            create_stage(workflow.getId(), "New", platformImprovementsId);
            create_stage(workflow.getId(), "Committed", platformImprovementsId);
            create_stage(workflow.getId(), "In Work", platformImprovementsId);
            create_stage(workflow.getId(), "Test", platformImprovementsId);
            create_stage(workflow.getId(), "Done", platformImprovementsId);

        workflow.dumpLane();
        assertEquals(36, workflow.getTotalLaneSize());
    }

    @Test
    public void create_devops_workflow(){
        // create a workflow like : https://reurl.cc/31W81L ,
        //                          https://reurl.cc/L1gvWL , and
        //                          https://reurl.cc/algpzG

        String devQueueId = create_top_stage(workflow.getId(), "Dev Queue");
            String helpId = create_stage(workflow.getId(), "Help", devQueueId);
                create_swimlane(workflow.getId(), "Legend", helpId);
                create_swimlane(workflow.getId(), "Templates", helpId);
            create_stage(workflow.getId(), "Backlog", devQueueId);
            create_stage(workflow.getId(), "Up Next", devQueueId);

        String devInFightId = create_top_stage(workflow.getId(), "Dev - In Flight");
            String epicInFlightId = create_swimlane(workflow.getId(), "Epic - In Flight", devInFightId);
                create_stage(workflow.getId(), "Breakdown", epicInFlightId);
                create_stage(workflow.getId(), "Doing", epicInFlightId);
                create_stage(workflow.getId(), "Pending Ops", epicInFlightId);

            String expeditedId = create_swimlane(workflow.getId(), "Expedited", devInFightId);
                create_stage(workflow.getId(), "Doing", expeditedId);
                create_stage(workflow.getId(), "Testing", expeditedId);
                create_stage(workflow.getId(), "Review", expeditedId);

            String standardId = create_swimlane(workflow.getId(), "Standard", devInFightId);
                create_stage(workflow.getId(), "Doing", standardId);
                create_stage(workflow.getId(), "Testing", standardId);
                create_stage(workflow.getId(), "Review", standardId);

        String operationsQueue = create_top_stage(workflow.getId(), "Operations Queue");
            String help = create_stage(workflow.getId(), "Help", operationsQueue);
                create_swimlane(workflow.getId(), "Legend", help);
                 create_swimlane(workflow.getId(), "Templates", help);
            create_stage(workflow.getId(), "Ops Backlog", operationsQueue);

        String opsInFlight = create_top_stage(workflow.getId(), "Ops - In Flight");
            create_stage(workflow.getId(), "Up Next", opsInFlight);
        String doingId = create_stage(workflow.getId(), "Doing", opsInFlight);
            String keepTheLightsOnId = create_swimlane(workflow.getId(), "Keep The Lights On", doingId);
                create_stage(workflow.getId(), "Doing", keepTheLightsOnId);
                create_stage(workflow.getId(), "Testing", keepTheLightsOnId);
        String expeditedId2 = create_swimlane(workflow.getId(), "Expedited", doingId);
            create_stage(workflow.getId(), "Doing", expeditedId2);
            create_stage(workflow.getId(), "Testing", expeditedId2);
        String standardId2 = create_swimlane(workflow.getId(), "Standard", doingId);
            create_stage(workflow.getId(), "Doing", standardId2);
            create_stage(workflow.getId(), "Testing", standardId2);

        create_top_stage(workflow.getId(), "Completed");

        String finishedId = create_top_stage(workflow.getId(), "Finished - Ready to Archive");
            create_swimlane(workflow.getId(), "Finished As Planned", finishedId);
            create_swimlane(workflow.getId(), "Started bu not Finished", finishedId);
            create_swimlane(workflow.getId(), "Discarded Requests / Ideas", finishedId);

        workflow.dumpLane();
        assertEquals(41, workflow.getTotalLaneSize());
    }


    private String create_workflow(String boardId, String title) {
        return KanbanTestUtility.create_workflow(boardId, title, repository);
    }

    private String create_top_stage(String workflowId, String title){
        return create_stage(workflowId, title, null);
    }

    private String create_stage(String workflowId, String title, String parentId){
        return KanbanTestUtility.create_stage(workflowId, title, parentId, repository);
    }

    private String create_swimlane(String workflowId, String title, String parentId){
        return KanbanTestUtility.create_swimlane(workflowId, title, parentId, repository);
    }

}
