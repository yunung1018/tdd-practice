package ntut.csie.sslab.kanban.adapter.gateway.repository.springboot.card;

import ntut.csie.sslab.kanban.entity.card.Card;
import ntut.csie.sslab.kanban.usecase.card.get.out.CardRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CardRepositoryImpl implements CardRepository {
    private final CardRepositoryPeer peer;
    public CardRepositoryImpl(CardRepositoryPeer peer){
        this.peer = peer;
    }


    @Override
    public Optional<Card> findById(String id) {
        Card card = null;

        Optional<CardData> data = peer.findById(id);
        if (data.isPresent()){
            card = CardMapper.transformToDomain(data.get());
        }

        return Optional.ofNullable(card);
    }

    @Override
    public void save(Card card) {
        CardData data = CardMapper.transformToData(card);

        peer.save(CardMapper.transformToData(card));
    }

    @Override
    public void deleteById(String id) {
        peer.deleteById(id);
    }

    @Override
    public List<Card> getCardsByBoardId(String boardId) {
        List<Card> result = new ArrayList<>();

        for(CardData each : peer.findAll()){
            if (each.getBoardId().equalsIgnoreCase(boardId)){
                result.add(CardMapper.transformToDomain(each));
            }
        }

        return result;
    }
}
