package tw.teddysoft.clean.usecase.kanbanboard.workflow;

import org.junit.Before;
import org.junit.Test;
import tw.teddysoft.clean.adapter.gateway.kanbanboard.InMemoryWorkflowRepository;
import tw.teddysoft.clean.adapter.presenter.kanbanboard.lane.SingleStageLanePresenter;
import tw.teddysoft.clean.adapter.presenter.kanbanboard.workflow.SingleWorkflowPresenter;
import tw.teddysoft.clean.domain.model.kanbanboard.workflow.Lane;
import tw.teddysoft.clean.domain.model.kanbanboard.workflow.LaneOrientation;
import tw.teddysoft.clean.domain.model.kanbanboard.workflow.Workflow;
import tw.teddysoft.clean.usecase.lane.stage.create.CreateStageLaneUseCase;
import tw.teddysoft.clean.usecase.kanbanboard.workflow.create.CreateWorkflowInput;
import tw.teddysoft.clean.usecase.kanbanboard.workflow.create.CreateWorkflowOutput;
import tw.teddysoft.clean.usecase.kanbanboard.workflow.create.CreateWorkflowUseCase;
import tw.teddysoft.clean.usecase.kanbanboard.workflow.create.impl.CreateWorkflowUseCaseImpl;
import tw.teddysoft.clean.usecase.lane.stage.create.CreateStageLaneInput;
import tw.teddysoft.clean.usecase.lane.stage.create.CreateStageLaneOutput;
import tw.teddysoft.clean.usecase.lane.stage.create.impl.CreateStageLaneUseCaseImpl;
import tw.teddysoft.clean.usecase.lane.ministage.create.CreateMiniStageLaneInput;
import tw.teddysoft.clean.usecase.lane.ministage.create.CreateMiniStageLaneOutput;
import tw.teddysoft.clean.usecase.lane.ministage.create.CreateMiniStageLaneUseCase;
import tw.teddysoft.clean.usecase.lane.ministage.create.impl.CreateMiniStageLaneUseCaseImpl;
import tw.teddysoft.clean.usecase.lane.swimlane.create.CreateSwimLaneInput;
import tw.teddysoft.clean.usecase.lane.swimlane.create.CreateSwimLaneOutput;
import tw.teddysoft.clean.usecase.lane.swimlane.create.CreateSwimLaneUseCase;
import tw.teddysoft.clean.usecase.lane.swimlane.create.impl.CreateSwimLaneUseCaseImpl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class CreateLaneTest {

    private WorkflowRepository repository;
    private Workflow workflow;

    @Before
    public void setUp(){
        repository = new InMemoryWorkflowRepository();
        create_default_workflow();
        workflow = repository.findFirstByName("Default");
        assertNotNull(workflow);
    }

    @Test
    public void create_stagelane(){
        create_stagelane(workflow.getId(), "Backlog");
        assertEquals(1, workflow.getStageLanes().size());
        assertEquals(LaneOrientation.VERTICAL, workflow.getStageLanes().get(0).getOrientation());
        assertEquals("Backlog", workflow.getStageLanes().get(0).getName());
    }


    @Test
    public void create_two_ministagelanes_under_the_stagelane_Backlog(){
        create_stagelane(workflow.getId(), "Backlog");
        Lane backlog = workflow.getStageLanes().get(0);

        create_ministagelane(workflow.getId(), "Legend", backlog.getId());
        assertEquals(1, backlog.getSubLanes().size());
        assertEquals(LaneOrientation.VERTICAL, backlog.getSubLanes().get(0).getOrientation());
        assertEquals("Legend", backlog.getSubLanes().get(0).getName());

        create_ministagelane(workflow.getId(), "Ready", backlog.getId());
        assertEquals(LaneOrientation.VERTICAL, backlog.getSubLanes().get(1).getOrientation());
        assertEquals("Ready", backlog.getSubLanes().get(1).getName());
    }

    @Test
    public void create_two_swimlanes_under_the_stagelane_Backlog(){
        create_stagelane(workflow.getId(), "Backlog");
        Lane backlog = workflow.getStageLanes().get(0);

        create_swimlane(workflow.getId(), "Top5", backlog.getId());
        assertEquals(1, backlog.getSubLanes().size());
        assertEquals(LaneOrientation.HORIZONTAL, backlog.getSubLanes().get(0).getOrientation());
        assertEquals("Top5", backlog.getSubLanes().get(0).getName());


        create_swimlane(workflow.getId(), "Idea", backlog.getId());
        assertEquals(2, backlog.getSubLanes().size());
        assertEquals(LaneOrientation.HORIZONTAL, backlog.getSubLanes().get(1).getOrientation());
        assertEquals("Idea", backlog.getSubLanes().get(1).getName());
    }



    @Test
    public void create_business_process_maintenance_stagelane(){
        create_stagelane(workflow.getId(), "Operations - Business Process Maintenance");
        Lane operation = workflow.getStageLanes().get(0);

        String productionProblemId = create_swimlane(workflow.getId(), "Production Problem", operation.getId());
            create_ministagelane(workflow.getId(), "New", productionProblemId);
            String workingID = create_ministagelane(workflow.getId(), "Working", productionProblemId);
                create_ministagelane(workflow.getId(), "Find Cause", workingID);
                create_ministagelane(workflow.getId(), "Fix Cause", workingID);
            create_ministagelane(workflow.getId(), "Done", productionProblemId);

        String plannedBusinessNeedId = create_swimlane(workflow.getId(), "Planned Business Need", operation.getId());
            String due2Months = create_ministagelane(workflow.getId(), "Due 2 months", plannedBusinessNeedId);
                create_swimlane(workflow.getId(), "High Impact", due2Months);
                create_swimlane(workflow.getId(), "Low Impact", due2Months);
            String due1Month = create_ministagelane(workflow.getId(), "Due 1 month", plannedBusinessNeedId);
                create_swimlane(workflow.getId(), "High Impact", due1Month);
                create_swimlane(workflow.getId(), "Low Impact", due1Month);
            String due1week = create_ministagelane(workflow.getId(), "Due 1 week", plannedBusinessNeedId);
                create_swimlane(workflow.getId(), "High Impact", due1week);
                create_swimlane(workflow.getId(), "Low Impact", due1week);
            String inWork = create_ministagelane(workflow.getId(), "In Work", plannedBusinessNeedId);
                create_swimlane(workflow.getId(), "High Impact", inWork);
                create_swimlane(workflow.getId(), "Low Impact", inWork);
            String done = create_ministagelane(workflow.getId(), "Done", plannedBusinessNeedId);
                create_swimlane(workflow.getId(), "High Impact", done);
                create_swimlane(workflow.getId(), "Low Impact", done);

        String routineId = create_swimlane(workflow.getId(), "Routine", operation.getId());

        String unplannedId = create_swimlane(workflow.getId(), "Unplanned", operation.getId());
            create_ministagelane(workflow.getId(), "New", unplannedId);
            create_ministagelane(workflow.getId(), "Committed", unplannedId);
            create_ministagelane(workflow.getId(), "In Work", unplannedId);
            create_ministagelane(workflow.getId(), "Test", unplannedId);
            create_ministagelane(workflow.getId(), "Done", unplannedId);

        String platformImprovementsId = create_swimlane(workflow.getId(), "Platform Improvements", operation.getId());
            create_ministagelane(workflow.getId(), "New", platformImprovementsId);
            create_ministagelane(workflow.getId(), "Committed", platformImprovementsId);
            create_ministagelane(workflow.getId(), "In Work", platformImprovementsId);
            create_ministagelane(workflow.getId(), "Test", platformImprovementsId);
            create_ministagelane(workflow.getId(), "Done", platformImprovementsId);

        workflow.dumpLane();

    }



    private void create_default_workflow() {
        CreateWorkflowUseCase createWorkflowUC = new CreateWorkflowUseCaseImpl(repository);

        CreateWorkflowInput input = CreateWorkflowUseCaseImpl.createInput();
        CreateWorkflowOutput output = new SingleWorkflowPresenter();
        input.setBoardId("000-1234");
        input.setWorkflowName("Default");

        createWorkflowUC.execute(input, output);
        workflow = repository.findFirstByName("Default");
        assertNotNull(workflow);
    }

    private void create_stagelane(String workflowId, String stageName){
        CreateStageLaneUseCase createStageLaneUC = new CreateStageLaneUseCaseImpl(repository);
        CreateStageLaneInput input = CreateStageLaneUseCaseImpl.createInput();
        CreateStageLaneOutput output = new SingleStageLanePresenter();
        input.setWorkflowId(workflowId);
        input.setLaneName(stageName);
        createStageLaneUC.execute(input, output);
    }

    private String create_ministagelane(String workflowId, String LaneName, String parentId){

        CreateMiniStageLaneUseCase createMiniStageLaneUC = new CreateMiniStageLaneUseCaseImpl(repository);
        CreateMiniStageLaneInput input = CreateMiniStageLaneUseCaseImpl.createInput();
        CreateMiniStageLaneOutput output = new SingleStageLanePresenter();
        input.setWorkflowId(workflowId);
        input.setParentId(parentId);
        input.setLaneName(LaneName);

        createMiniStageLaneUC.execute(input, output);

        return output.getId();
    }

    private String create_swimlane(String workflowId, String LaneName, String parentId){

        CreateSwimLaneUseCase createSwimLaneUC = new CreateSwimLaneUseCaseImpl(repository);

        CreateSwimLaneInput input = CreateSwimLaneUseCaseImpl.createInput();
        CreateSwimLaneOutput output = new SingleStageLanePresenter();

        input.setWorkflowId(workflowId);
        input.setParentId(parentId);
        input.setLaneName(LaneName);

        createSwimLaneUC.execute(input, output);

        return output.getId();
    }

}
