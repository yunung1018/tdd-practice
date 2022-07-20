package ntut.csie.sslab.kanban.card.usecase.port.in.get;

import ntut.csie.sslab.ddd.usecase.cqrs.CqrsCommandOutput;

public class GetCardOutput extends CqrsCommandOutput<GetCardOutput> {
    private CardDto cardDto;

    public CardDto getCard() {
        return cardDto;
    }

    public void setCard(CardDto cardDto) {
        this.cardDto = cardDto;
    }
}
