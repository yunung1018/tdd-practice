package tw.teddysoft.clean.usecase.kanbanboard.workflow;

import org.junit.Before;
import org.junit.Test;
import tw.teddysoft.clean.adapter.gateway.kanbanboard.InMemoryStageRepository;
import tw.teddysoft.clean.adapter.gateway.kanbanboard.InMemoryWorkflowRepository;
import tw.teddysoft.clean.adapter.presenter.kanbanboard.stage.SingleStagePresenter;
import tw.teddysoft.clean.adapter.presenter.kanbanboard.workflow.SingleWorkflowPresenter;
import tw.teddysoft.clean.usecase.kanbanboard.stage.add.AddStageInput;
import tw.teddysoft.clean.usecase.kanbanboard.stage.add.AddStageOutput;
import tw.teddysoft.clean.usecase.kanbanboard.stage.add.impl.AddStageUseCaseImpl;
import tw.teddysoft.clean.usecase.kanbanboard.workflow.create.CreateWorkflowInput;
import tw.teddysoft.clean.usecase.kanbanboard.workflow.create.CreateWorkflowOutput;
import tw.teddysoft.clean.usecase.kanbanboard.workflow.create.CreateWorkflowUseCase;
import tw.teddysoft.clean.usecase.kanbanboard.workflow.create.impl.CreateWorkflowUseCaseImpl;

import static org.junit.Assert.*;

public class CreateWorkflowTest {

    private WorkflowRepository repository;
    private CreateWorkflowUseCase createWorkflowUC;

    @Before
    public void setUp(){
        repository = new InMemoryWorkflowRepository();
        createWorkflowUC = new CreateWorkflowUseCaseImpl(repository);
    }


    @Test
    public void create_the_first_workflow() {

        CreateWorkflowInput input = CreateWorkflowUseCaseImpl.createInput();
        CreateWorkflowOutput output = new SingleWorkflowPresenter();
        input.setBoardId("000-1234");
        input.setWorkflowName("Default Workflow");

        createWorkflowUC.execute(input, output);

        assertNotNull(output.getWorkflowId());
        assertEquals("Default Workflow", output.getWorkflowName());
        assertEquals(1, repository.findAll().size());

    }

}
