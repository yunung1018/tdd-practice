package tw.teddysoft.clean.usecase.card;

import tw.teddysoft.clean.domain.model.card.Card;
import tw.teddysoft.clean.domain.model.kanbanboard.home.Home;
import tw.teddysoft.clean.domain.usecase.repository.Repository;
import tw.teddysoft.clean.domain.usecase.repository.RepositoryPeer;

import java.util.List;

public class CardRepository extends Repository<Card> {
    public CardRepository(RepositoryPeer peer) {
        super(peer);
    }
}
