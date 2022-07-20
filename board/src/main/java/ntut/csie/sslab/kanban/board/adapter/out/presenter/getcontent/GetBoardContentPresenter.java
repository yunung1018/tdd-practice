package ntut.csie.sslab.kanban.board.adapter.presenter.getcontent;

import ntut.csie.sslab.ddd.adapter.presenter.Presenter;
import ntut.csie.sslab.kanban.board.usecase.port.in.CommittedWorkflowDto;
import ntut.csie.sslab.kanban.board.usecase.port.in.getcontent.GetBoardContentOutput;
import ntut.csie.sslab.kanban.usecase.card.CardDto;
import ntut.csie.sslab.kanban.usecase.lane.LaneDto;
import ntut.csie.sslab.kanban.usecase.workflow.WorkflowDto;

import java.util.ArrayList;
import java.util.List;

import static ntut.csie.sslab.ddd.model.common.Contract.requireNotNull;

public class GetBoardContentPresenter implements Presenter<GetBoardContentOutput, BoardContentBasicViewModel> {

    @Override
    public BoardContentBasicViewModel buildViewModel(GetBoardContentOutput output) {
        requireNotNull("GetBoardContentOutput", output);

        BoardContentBasicViewModel boardContentViewModel = new BoardContentBasicViewModel();
        boardContentViewModel.setExitCode(output.getExitCode());
        boardContentViewModel.setMessage(output.getMessage());
        boardContentViewModel.setId(output.getId());

        List<WorkflowDto> orderedWorkflowDtos = new ArrayList<>();
        for(CommittedWorkflowDto committedWorkflowDto : output.getCommittedWorkflowDtos()) {
            for (WorkflowDto workflowDto : output.getWorkflowDtos()) {
                if(committedWorkflowDto.getWorkflowId().equals(workflowDto.getWorkflowId()))
                    orderedWorkflowDtos.add(workflowDto);
            }
        }

        for (WorkflowDto workflowDto : orderedWorkflowDtos) {
            workflowDto.setLanes(bindCardDtoToLaneDto(workflowDto.getLanes(), output.getCardDtos()));
        }

        boardContentViewModel.setBoardId(output.getBoardId());
        boardContentViewModel.setWorkflows(orderedWorkflowDtos);
        boardContentViewModel.setBoardMembers(output.getBoardMemberDtos());
        return boardContentViewModel;
    }

    private List<LaneDto> bindCardDtoToLaneDto(List<LaneDto> laneDtos, List<CardDto> cardDtos) {
        for(LaneDto laneDto : laneDtos) {
            for(CardDto cardDto : cardDtos) {
                if(laneDto.getLaneId().equals(cardDto.getLaneId())) {
                    laneDto.addCardDto(cardDto);
                }
            }
            laneDto.setLanes(bindCardDtoToLaneDto(laneDto.getLanes(), cardDtos));
        }

        return laneDtos;
    }
}
