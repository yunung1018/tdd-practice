package ntut.csie.sslab.kanban.card.usecase.port.in.description.impl;

import ntut.csie.sslab.ddd.usecase.DomainEventBus;
import ntut.csie.sslab.ddd.usecase.cqrs.CqrsCommandOutput;
import ntut.csie.sslab.ddd.usecase.cqrs.ExitCode;
import ntut.csie.sslab.kanban.card.entity.Card;
import ntut.csie.sslab.kanban.card.usecase.port.in.description.in.ChangeCardDescriptionInput;
import ntut.csie.sslab.kanban.card.usecase.port.in.description.in.ChangeCardDescriptionUseCase;
import ntut.csie.sslab.kanban.common.ClientBoardContentMightExpire;
import ntut.csie.sslab.kanban.card.usecase.port.out.CardRepository;

public class ChangeCardDescriptionUseCaseImpl implements ChangeCardDescriptionUseCase {
    private final CardRepository cardRepository;
    private final DomainEventBus domainEventBus;

    public ChangeCardDescriptionUseCaseImpl(CardRepository cardRepository,
                                            DomainEventBus domainEventBus) {

        this.cardRepository = cardRepository;
        this.domainEventBus = domainEventBus;
    }

    @Override
    public CqrsCommandOutput execute(ChangeCardDescriptionInput input) {
        Card card = cardRepository.findById(input.getCardId()).orElse(null);
        CqrsCommandOutput output = CqrsCommandOutput.create();

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
