package ntut.csie.sslab.kanban.board.usecase.service;

import ntut.csie.sslab.ddd.usecase.DomainEventBus;
import ntut.csie.sslab.ddd.usecase.cqrs.CqrsOutput;
import ntut.csie.sslab.ddd.usecase.cqrs.ExitCode;
import ntut.csie.sslab.kanban.board.usecase.port.in.create.CreateBoardInput;
import ntut.csie.sslab.kanban.board.usecase.port.in.create.CreateBoardUseCase;
import ntut.csie.sslab.kanban.board.usecase.port.out.BoardRepository;
import ntut.csie.sslab.kanban.board.entity.Board;
import ntut.csie.sslab.kanban.board.entity.BoardBuilder;
import ntut.csie.sslab.kanban.board.entity.BoardMemberType;

public class CreateBoardService implements CreateBoardUseCase {
	private BoardRepository boardRepository;
	private DomainEventBus domainEventBus;

	public CreateBoardService(BoardRepository boardRepository,
							  DomainEventBus domainEventBus) {

		this.boardRepository = boardRepository;
		this.domainEventBus = domainEventBus;
	}

	@Override
	public CqrsOutput execute(CreateBoardInput input) {
		Board board = BoardBuilder.newInstance()
				.name(input.getName())
				.teamId(input.getTeamId())
				.userId(input.getUserId())
				.build();
		board.becameBoardMember(BoardMemberType.Manager, input.getUserId());

		boardRepository.save(board);
		domainEventBus.postAll(board);

		return CqrsOutput.create()
				.setId(board.getBoardId())
				.setExitCode(ExitCode.SUCCESS);
	}

}
