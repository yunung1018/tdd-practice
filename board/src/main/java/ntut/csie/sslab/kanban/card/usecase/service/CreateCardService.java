package ntut.csie.sslab.kanban.card.usecase.service;

import ntut.csie.sslab.ddd.usecase.DomainEventBus;
import ntut.csie.sslab.ddd.usecase.cqrs.CqrsCommandOutput;
import ntut.csie.sslab.ddd.usecase.cqrs.ExitCode;
import ntut.csie.sslab.kanban.card.entity.Card;
import ntut.csie.sslab.kanban.card.entity.CardBuilder;
import ntut.csie.sslab.kanban.card.entity.CardType;
import ntut.csie.sslab.kanban.card.usecase.port.in.create.CreateCardInput;
import ntut.csie.sslab.kanban.card.usecase.port.in.create.CreateCardUseCase;
import ntut.csie.sslab.kanban.card.usecase.port.out.CardRepository;

public class CreateCardService implements CreateCardUseCase {
	private final CardRepository cardRepository;
	private final DomainEventBus domainEventBus;
	
	public CreateCardService(CardRepository cardRepository, DomainEventBus domainEventBus) {
		this.cardRepository = cardRepository;
		this.domainEventBus = domainEventBus;
	}
	
	@Override
	public CqrsCommandOutput execute(CreateCardInput input) {
		Card card = CardBuilder.newInstance()
				.workflowId(input.getWorkflowId())
				.laneId(input.getLaneId())
				.userId(input.getUserId())
				.description(input.getDescription())
				.estimate(input.getEstimate())
				.notes(input.getNote())
				.deadline(input.getDeadline())
				.type(CardType.valueOf(input.getType()))
				.username(input.getUsername())
				.boardId(input.getBoardId())
				.build();

		cardRepository.save(card);
		domainEventBus.postAll(card);

		return CqrsCommandOutput.create().setId(card.getCardId()).setExitCode(ExitCode.SUCCESS);
	}
}
