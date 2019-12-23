package tw.teddysoft.clean.usecase.card;

import tw.teddysoft.clean.domain.model.card.Card;

import java.util.List;

public interface CardRepository {

    List<Card> findAll();
    Card findById(String id);

    Card findFirstByTitle(String name);

    Card save(Card arg);
    boolean remove(Card arg);

    List<Card> findByStageId(String StageId);

}
