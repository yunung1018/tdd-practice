package ntut.csie.sslab.kanban.card.usecase.get.in;

import ntut.csie.sslab.ddd.usecase.Input;

public class GetCardInput implements Input {

    private String cardId;

    public String getCardId() {
        return cardId;
    }

    public void setCardId(String cardId) {
        this.cardId = cardId;
    }
}
