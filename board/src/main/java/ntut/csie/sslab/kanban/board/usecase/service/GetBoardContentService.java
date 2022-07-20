package ntut.csie.sslab.kanban.board.usecase.service;

import ntut.csie.sslab.ddd.usecase.DomainEventBus;
import ntut.csie.sslab.ddd.usecase.cqrs.ExitCode;
import ntut.csie.sslab.kanban.board.entity.Board;
import ntut.csie.sslab.kanban.board.usecase.port.in.getcontent.BoardMemberDto;
import ntut.csie.sslab.kanban.board.usecase.port.in.getcontent.CommittedWorkflowDto;
import ntut.csie.sslab.kanban.board.usecase.port.in.getcontent.BoardMemberMapper;
import ntut.csie.sslab.kanban.board.usecase.port.in.getcontent.CommittedWorkflowMapper;
import ntut.csie.sslab.kanban.common.ClientBoardContentMightExpire;
import ntut.csie.sslab.kanban.board.usecase.port.in.getcontent.GetBoardContentInput;
import ntut.csie.sslab.kanban.board.usecase.port.in.getcontent.GetBoardContentOutput;
import ntut.csie.sslab.kanban.board.usecase.port.in.getcontent.GetBoardContentUseCase;
import ntut.csie.sslab.kanban.board.usecase.port.out.BoardRepository;
import ntut.csie.sslab.kanban.card.usecase.port.in.get.CardDto;
import ntut.csie.sslab.kanban.card.usecase.port.out.CardRepository;
import ntut.csie.sslab.kanban.card.usecase.port.in.get.CardMapper;
import ntut.csie.sslab.kanban.workflow.usecase.WorkflowsMapper;
import ntut.csie.sslab.kanban.workflow.usecase.port.in.get.WorkflowDto;
import ntut.csie.sslab.kanban.workflow.usecase.WorkflowRepository;

import java.util.List;

public class GetBoardContentServer implements GetBoardContentUseCase {
    private final BoardRepository boardRepository;
    private final WorkflowRepository workflowRepository;
    private final CardRepository cardRepository;
    private final DomainEventBus domainEventBus;

    public GetBoardContentServer(BoardRepository boardRepository,
                                 WorkflowRepository workflowRepository,
                                 CardRepository cardRepository,
                                 DomainEventBus domainEventBus) {
        this.boardRepository = boardRepository;
        this.workflowRepository = workflowRepository;
        this.cardRepository = cardRepository;
        this.domainEventBus = domainEventBus;
    }

    @Override
    public GetBoardContentOutput execute(GetBoardContentInput input) {

        Board board = boardRepository.findById(input.getBoardId()).orElse(null);
        GetBoardContentOutput output = new GetBoardContentOutput();

        if (null == board){
            output.setBoardId(input.getBoardId())
                    .setExitCode(ExitCode.FAILURE)
                    .setMessage("Get board failed: board not found, board id = " + input.getBoardId());
            domainEventBus.post(new ClientBoardContentMightExpire(input.getBoardId()));
            return output;
        }

        List<BoardMemberDto> boardMemberDtos = BoardMemberMapper.toDto(board.getBoardMembers());
        List<WorkflowDto> workflowDtos = WorkflowsMapper.toDto(workflowRepository.getWorkflowsByBoardId(input.getBoardId()));
        List<CardDto> cardDtosInBoard = CardMapper.toDto(cardRepository.getCardsByBoardId(input.getBoardId()));
        List<CommittedWorkflowDto> committedWorkflowDtos = CommittedWorkflowMapper.toDto(board.getCommittedWorkflows());

        output.setBoardId(input.getBoardId())
                .setBoardMemberDtos(boardMemberDtos)
                .setWorkflowDtos(workflowDtos)
                .setCommittedWorkflowDtos(committedWorkflowDtos)
                .setCardDtos(cardDtosInBoard);

        return output;
    }
}
