package ntut.csie.sslab.kanban.card.usecase.service;

import ntut.csie.sslab.ddd.usecase.DomainEventBus;
import ntut.csie.sslab.ddd.usecase.cqrs.CqrsOutput;
import ntut.csie.sslab.ddd.usecase.cqrs.ExitCode;
import ntut.csie.sslab.kanban.card.entity.Card;
import ntut.csie.sslab.kanban.card.usecase.port.in.description.ChangeCardDescriptionInput;
import ntut.csie.sslab.kanban.card.usecase.port.in.description.ChangeCardDescriptionUseCase;
import ntut.csie.sslab.kanban.common.ClientBoardContentMightExpire;
import ntut.csie.sslab.kanban.card.usecase.port.out.CardRepository;

public class ChangeCardDescriptionService implements ChangeCardDescriptionUseCase {
    private final CardRepository cardRepository;
    private final DomainEventBus domainEventBus;

    public ChangeCardDescriptionService(CardRepository cardRepository,
                                        DomainEventBus domainEventBus) {

        this.cardRepository = cardRepository;
        this.domainEventBus = domainEventBus;
    }

    @Override
    public CqrsOutput execute(ChangeCardDescriptionInput input) {
        Card card = cardRepository.findById(input.getCardId()).orElse(null);
        CqrsOutput output = CqrsOutput.create();

        if (null == card){
            output.setId(input.getCardId())
                    .setExitCode(ExitCode.FAILURE)
                    .setMessage("Change card description failed: card not found, card id = " + input.getCardId());
            domainEventBus.post(new ClientBoardContentMightExpire(input.getBoardId()));
            return output;
        }

        card.changeDescription(input.getDescription(), input.getUserId(), input.getUsername(), input.getBoardId());

        cardRepository.save(card);
        domainEventBus.postAll(card);

        return output.setId(card.getCardId()).setExitCode(ExitCode.SUCCESS);
    }

}
