package ntut.csie.sslab.kanban.card.usecase.get.out;


import ntut.csie.sslab.kanban.card.entity.Card;
import ntut.csie.sslab.ddd.usecase.AbstractRepository;

import java.util.List;

public interface CardRepository extends AbstractRepository<Card, String> {
	List<Card> getCardsByBoardId(String boardId);
}

