package tw.teddysoft.clean.adapter.gateway.workitem;

import tw.teddysoft.clean.domain.model.card.Card;
import tw.teddysoft.clean.usecase.card.CardRepository;

import java.util.ArrayList;
import java.util.List;

public class InMemoryCardRepository implements CardRepository {

    private List<Card> cards;

    public InMemoryCardRepository(){
        cards = new ArrayList<Card>();
    }

    @Override
    public List<Card> findAll() {
        return cards;
    }

    @Override
    public Card findById(String id) {
        for(Card each : cards){
            if (each.getId().equalsIgnoreCase(id))
                return each;
        }
//        throw new RuntimeException("Cannot find card with id : " + id);
        return null;
    }

    @Override
    public Card findFirstByName(String title) {
        for(Card each : cards){
            if (each.getName().equals(title))
                return each;
        }
        throw new RuntimeException("Cannot find card with name : " + title);
    }

    @Override
    public Card save(Card arg) {
        if (cards.contains(arg))
            return arg;
        else if (cards.add(arg))
            return arg;
        else
            return null;
    }

    @Override
    public boolean remove(Card arg) {
        return cards.remove(arg);
    }

    @Override
    public List<Card> findByStageId(String StageId) {
        throw  new RuntimeException("Not Implemented.");
    }
}
