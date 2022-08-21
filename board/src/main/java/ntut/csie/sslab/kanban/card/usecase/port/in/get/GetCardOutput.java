package ntut.csie.sslab.kanban.card.usecase.port.in.get;

import ntut.csie.sslab.ddd.usecase.cqrs.CqrsOutput;

public class GetCardOutput extends CqrsOutput<GetCardOutput> {
    private CardDto cardDto;

    public CardDto getCard() {
        return cardDto;
    }

    public void setCard(CardDto cardDto) {
        this.cardDto = cardDto;
    }
}
