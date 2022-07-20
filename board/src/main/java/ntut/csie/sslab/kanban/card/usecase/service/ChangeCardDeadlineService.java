package ntut.csie.sslab.kanban.card.usecase.service;

import ntut.csie.sslab.ddd.usecase.DomainEventBus;
import ntut.csie.sslab.ddd.usecase.cqrs.CqrsCommandOutput;
import ntut.csie.sslab.ddd.usecase.cqrs.ExitCode;
import ntut.csie.sslab.kanban.card.entity.Card;
import ntut.csie.sslab.kanban.card.usecase.port.in.deadline.ChangeCardDeadlineInput;
import ntut.csie.sslab.kanban.common.ClientBoardContentMightExpire;
import ntut.csie.sslab.kanban.card.usecase.port.out.CardRepository;
import ntut.csie.sslab.kanban.card.usecase.port.in.deadline.ChangeCardDeadlineUseCase;

public class ChangeCardDeadlineService implements ChangeCardDeadlineUseCase {

    private final CardRepository cardRepository;
    private final DomainEventBus domainEventBus;

    public ChangeCardDeadlineService(CardRepository cardRepository, DomainEventBus domainEventBus) {
        this.cardRepository = cardRepository;
        this.domainEventBus = domainEventBus;
    }

    @Override
    public CqrsCommandOutput execute(ChangeCardDeadlineInput input) {
        Card card = cardRepository.findById(input.getCardId()).orElse(null);
        CqrsCommandOutput output = CqrsCommandOutput.create();

        if (null == card){
            output.setId(input.getCardId())
                    .setExitCode(ExitCode.FAILURE)
                    .setMessage("Change card deadline failed: card not found, card id = " + input.getCardId());
            domainEventBus.post(new ClientBoardContentMightExpire(input.getBoardId()));
            return output;
        }


        card.changeDeadline(input.getNewDeadline(), input.getUserId(), input.getUsername(), input.getBoardId());

        cardRepository.save(card);
        domainEventBus.postAll(card);

        return output.setId(card.getCardId()).setExitCode(ExitCode.SUCCESS);
    }
}
