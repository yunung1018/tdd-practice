package ntut.csie.sslab.kanban.card.usecase.port.in.get;

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
