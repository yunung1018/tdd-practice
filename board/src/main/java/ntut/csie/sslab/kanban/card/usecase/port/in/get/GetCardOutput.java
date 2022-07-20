package ntut.csie.sslab.kanban.card.usecase.get.in;

import ntut.csie.sslab.ddd.usecase.cqrs.CqrsCommandOutput;
import ntut.csie.sslab.kanban.card.usecase.CardDto;

public class GetCardOutput extends CqrsCommandOutput<GetCardOutput> {
    private CardDto cardDto;

    public CardDto getCard() {
        return cardDto;
    }

    public void setCard(CardDto cardDto) {
        this.cardDto = cardDto;
    }
}
