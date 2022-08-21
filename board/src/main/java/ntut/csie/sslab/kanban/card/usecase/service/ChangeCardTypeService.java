package ntut.csie.sslab.kanban.card.usecase.service;

import ntut.csie.sslab.ddd.usecase.DomainEventBus;
import ntut.csie.sslab.ddd.usecase.cqrs.CqrsOutput;
import ntut.csie.sslab.ddd.usecase.cqrs.ExitCode;
import ntut.csie.sslab.kanban.card.entity.Card;
import ntut.csie.sslab.kanban.card.entity.CardType;
import ntut.csie.sslab.kanban.card.usecase.port.in.type.ChangeCardTypeInput;
import ntut.csie.sslab.kanban.card.usecase.port.in.type.ChangeCardTypeUseCase;
import ntut.csie.sslab.kanban.common.ClientBoardContentMightExpire;
import ntut.csie.sslab.kanban.card.usecase.port.out.CardRepository;

public class ChangeCardTypeService implements ChangeCardTypeUseCase {

    private CardRepository cardRepository;
    private DomainEventBus domainEventBus;

    public ChangeCardTypeService(CardRepository cardRepository, DomainEventBus domainEventBus) {
        this.cardRepository = cardRepository;
        this.domainEventBus = domainEventBus;
    }

    @Override
    public CqrsOutput execute(ChangeCardTypeInput input) {
        Card card = cardRepository.findById(input.getCardId()).orElse(null);
        CqrsOutput output = CqrsOutput.create();

        if (null == card){
            output.setId(input.getCardId())
                    .setExitCode(ExitCode.FAILURE)
                    .setMessage("Change card type failed: card not found, card id = " + input.getCardId());
            domainEventBus.post(new ClientBoardContentMightExpire(input.getBoardId()));
            return output;
        }

        CardType cardType = null;

        switch(input.getNewType()) {
            case("General"):
                cardType = CardType.General;
                break;
            case("Expedite"):
                cardType = CardType.Expedite;
                break;
        }
        card.changeType(cardType, input.getUserId(), input.getUsername(), input.getBoardId());

        cardRepository.save(card);
        domainEventBus.postAll(card);

        return output.setId(card.getCardId()).setExitCode(ExitCode.SUCCESS);
    }

}
