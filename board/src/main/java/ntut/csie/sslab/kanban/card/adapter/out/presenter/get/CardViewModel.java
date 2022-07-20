package ntut.csie.sslab.kanban.card.adapter.out.presenter.get;

import ntut.csie.sslab.ddd.adapter.presenter.ViewModel;
import ntut.csie.sslab.kanban.card.usecase.port.in.get.CardDto;

public class CardViewModel implements ViewModel {
    private CardDto card;

    public CardDto getCard() {
        return card;
    }

    public void setCard(CardDto card) {
        this.card = card;
    }
}
