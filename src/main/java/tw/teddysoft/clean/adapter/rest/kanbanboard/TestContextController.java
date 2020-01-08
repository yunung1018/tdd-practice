package tw.teddysoft.clean.adapter.rest.kanbanboard;

import org.junit.Before;
import org.junit.Test;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tw.teddysoft.clean.domain.model.kanbanboard.board.Board;
import tw.teddysoft.clean.domain.model.kanbanboard.workflow.Lane;
import tw.teddysoft.clean.domain.model.kanbanboard.workflow.LaneOrientation;
import tw.teddysoft.clean.domain.model.kanbanboard.workflow.Workflow;
import tw.teddysoft.clean.usecase.Context;

import javax.swing.*;
import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
public class TestContextController {

    private Context context;

        public TestContextController(){
            super();
            context = Context.newInstance();
            context.registerAllEventHandler();
        }


        private String createDefaultWorkspaceUseCase(){
            return context.doCreateWorkspaceUseCase(
                    Context.USER_ID,
                    Context.WORKSPACE_NAME)
                    .getWorkspaceId();
        }

    @RequestMapping("/devOpsBoard")
    public BoardDto getDevOpsBoard(){

        System.out.println("getDevOpsBoard");

        String workspaceId = createDefaultWorkspaceUseCase();

        String boardId = context.doCreateBoardUseCase(
                workspaceId,
                Context.SCRUM_BOARD_NAME).getBoardId();

        Board board = context.getBoardRepository().findById(boardId);
        String workflowId = board.getCommittedWorkflow().iterator().next().getWorkflowId();
        Workflow workflow = context.getWorkflowRepository().findById(workflowId);
        context.createDevOpsWorkflow(workflow);

        BoardDto boardDto = new BoardDto(board.getId());
        board.getCommittedWorkflow().stream().forEach(
                each ->
                    boardDto.addWorkflows(context.getWorkflowRepository().findById(each.getWorkflowId()))
                );

        return boardDto;
    }


    @RequestMapping("/devOpsWorkflow")
    public List<Lane> getDevOpsWorkflow(){
        System.out.println("getDevOpsWorkflow");
        String workspaceId = createDefaultWorkspaceUseCase();

        String boardId = context.doCreateBoardUseCase(
                workspaceId,
                Context.SCRUM_BOARD_NAME).getBoardId();

        Board board = context.getBoardRepository().findById(boardId);
        String workflowId = board.getCommittedWorkflow().iterator().next().getWorkflowId();
        Workflow workflow = context.getWorkflowRepository().findById(workflowId);
        context.createDevOpsWorkflow(workflow);

        return workflow.getStages();
    }


    @RequestMapping("/operations")
    public List<Lane> getBusinessProcessMaintenanceStages(){
        String workspaceId = context.doCreateWorkspaceUseCase(
                Context.USER_ID,
                Context.WORKSPACE_NAME)
                .getWorkspaceId();

        String boardId = context.doCreateBoardUseCase(
                workspaceId,
                Context.SCRUM_BOARD_NAME).getBoardId();

        Board board = context.getBoardRepository().findById(boardId);
        String workflowId = board.getCommittedWorkflow().iterator().next().getWorkflowId();
        Workflow workflow = context.getWorkflowRepository().findById(workflowId);
        context.createBusinessProcessMaintenanceStage(workflow);

        return workflow.getStages();
    }


    @RequestMapping("/scrum")
    public List<Lane> getScrumWorkflow(){

        String workspaceId = context.doCreateWorkspaceUseCase(
                Context.USER_ID,
                Context.WORKSPACE_NAME)
                .getWorkspaceId();

        String boardId = context.doCreateBoardUseCase(
                workspaceId,
                Context.SCRUM_BOARD_NAME).getBoardId();

        Board board = context.getBoardRepository().findById(boardId);
        System.out.println("board.getCommittedWorkflow().size() = " + board.getCommittedWorkflow().size());

        String workflowId = board.getCommittedWorkflow().iterator().next().getWorkflowId();
        System.out.println("workflow id = " + workflowId);


        Workflow workflow = context.getWorkflowRepository().findById(workflowId);

        context.createScrumStage(workflow);

        return workflow.getStages();
    }

    @RequestMapping("/kanban")
    public List<Lane> getKanbanWorkflow(){

        String workspaceId = context.doCreateWorkspaceUseCase(
                Context.USER_ID,
                Context.WORKSPACE_NAME)
                .getWorkspaceId();

        String boardId = context.doCreateBoardUseCase(
                workspaceId,
                Context.SCRUM_BOARD_NAME).getBoardId();

        Board board = context.getBoardRepository().findById(boardId);
        System.out.println("board.getCommittedWorkflow().size() = " + board.getCommittedWorkflow().size());

        String workflowId = board.getCommittedWorkflow().iterator().next().getWorkflowId();
        System.out.println("workflow id = " + workflowId);

        Workflow workflow = context.getWorkflowRepository().findById(workflowId);

        context.crateKanbanBoardGame(workflow);

        return workflow.getStages();
    }


}
