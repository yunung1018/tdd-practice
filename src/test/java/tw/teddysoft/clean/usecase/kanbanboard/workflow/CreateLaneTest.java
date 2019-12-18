package tw.teddysoft.clean.usecase.kanbanboard.workflow;

import org.junit.Before;
import org.junit.Test;
import tw.teddysoft.clean.adapter.gateway.kanbanboard.InMemoryWorkflowRepository;
import tw.teddysoft.clean.adapter.presenter.kanbanboard.lane.SingleStageLanePresenter;
import tw.teddysoft.clean.adapter.presenter.kanbanboard.workflow.SingleWorkflowPresenter;
import tw.teddysoft.clean.domain.model.kanbanboard.workflow.Lane;
import tw.teddysoft.clean.domain.model.kanbanboard.workflow.Workflow;
import tw.teddysoft.clean.usecase.lane.stage.create.CreateStageLaneUseCase;
import tw.teddysoft.clean.usecase.kanbanboard.workflow.create.CreateWorkflowInput;
import tw.teddysoft.clean.usecase.kanbanboard.workflow.create.CreateWorkflowOutput;
import tw.teddysoft.clean.usecase.kanbanboard.workflow.create.CreateWorkflowUseCase;
import tw.teddysoft.clean.usecase.kanbanboard.workflow.create.impl.CreateWorkflowUseCaseImpl;
import tw.teddysoft.clean.usecase.lane.stage.create.CreateStageLaneInput;
import tw.teddysoft.clean.usecase.lane.stage.create.CreateStageLaneOutput;
import tw.teddysoft.clean.usecase.lane.stage.create.impl.CreateStageLaneUseCaseImpl;
import tw.teddysoft.clean.usecase.lane.vertical.create.CreateVerticalLaneInput;
import tw.teddysoft.clean.usecase.lane.vertical.create.CreateVerticalLaneOutput;
import tw.teddysoft.clean.usecase.lane.vertical.create.CreateVerticalLaneUseCase;
import tw.teddysoft.clean.usecase.lane.vertical.create.impl.CreateVerticalLaneUseCaseImpl;

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
    public void create_stage_lane(){
        create_stage_lane(workflow.getId(), "Backlog");
        assertEquals(1, workflow.getStageLanes().size());
        assertEquals("Backlog", workflow.getStageLanes().get(0).getName());
    }


    @Test
    public void create_two_vertical_lanes_under_the_stage_lange_Backlog(){
        create_stage_lane(workflow.getId(), "Backlog");
        Lane backlog = workflow.getStageLanes().get(0);

        create_vertical_lane(workflow.getId(), "Legend", backlog.getId());
        assertEquals(1, backlog.getSubLanes().size());
        assertEquals("Legend", backlog.getSubLanes().get(0).getName());

        create_vertical_lane(workflow.getId(), "Ready", backlog.getId());
        assertEquals("Ready", backlog.getSubLanes().get(1).getName());
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

    private void create_stage_lane(String workflowId, String stageName){
        CreateStageLaneUseCase createStageLaneUC = new CreateStageLaneUseCaseImpl(repository);
        CreateStageLaneInput input = CreateStageLaneUseCaseImpl.createInput();
        CreateStageLaneOutput output = new SingleStageLanePresenter();
        input.setWorkflowId(workflowId);
        input.setLaneName(stageName);
        createStageLaneUC.execute(input, output);
    }

    private void create_vertical_lane(String workflowId, String LaneName, String parentId){

        CreateVerticalLaneUseCase createVerticalLaneUC = new CreateVerticalLaneUseCaseImpl(repository);
        CreateVerticalLaneInput input = CreateVerticalLaneUseCaseImpl.createInput();
        CreateVerticalLaneOutput output = new SingleStageLanePresenter();
        input.setWorkflowId(workflowId);
        input.setParentId(parentId);
        input.setLaneName(LaneName);

        createVerticalLaneUC.execute(input, output);
    }

}
