package ntut.csie.sslab.kanban.card.usecase.get.impl;

import ntut.csie.sslab.kanban.card.usecase.get.out.CardRepository;
import ntut.csie.sslab.kanban.card.usecase.CardMapper;
import ntut.csie.sslab.kanban.card.usecase.get.in.GetCardInput;
import ntut.csie.sslab.kanban.card.usecase.get.in.GetCardOutput;
import ntut.csie.sslab.kanban.card.usecase.get.in.GetCardUseCase;

public class GetCardUseCaseImpl implements GetCardUseCase {

    private final CardRepository cardRepository;

    public GetCardUseCaseImpl(CardRepository cardRepository) {
        this.cardRepository = cardRepository;
    }

    @Override
    public GetCardOutput execute(GetCardInput input) {

        GetCardOutput output = new GetCardOutput();

        output.setCard(CardMapper.toDto(cardRepository.findById(input.getCardId()).get()));

        return output;
    }
}
