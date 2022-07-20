package ntut.csie.sslab.kanban.card.usecase.service;

import ntut.csie.sslab.kanban.card.usecase.port.out.CardRepository;
import ntut.csie.sslab.kanban.card.usecase.port.in.get.CardMapper;
import ntut.csie.sslab.kanban.card.usecase.port.in.get.GetCardInput;
import ntut.csie.sslab.kanban.card.usecase.port.in.get.GetCardOutput;
import ntut.csie.sslab.kanban.card.usecase.port.in.get.GetCardUseCase;

public class GetCardUseService implements GetCardUseCase {

    private final CardRepository cardRepository;

    public GetCardUseService(CardRepository cardRepository) {
        this.cardRepository = cardRepository;
    }

    @Override
    public GetCardOutput execute(GetCardInput input) {

        GetCardOutput output = new GetCardOutput();

        output.setCard(CardMapper.toDto(cardRepository.findById(input.getCardId()).get()));

        return output;
    }
}
