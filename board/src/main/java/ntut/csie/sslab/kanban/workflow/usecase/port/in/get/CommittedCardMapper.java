package ntut.csie.sslab.kanban.workflow.usecase;

import ntut.csie.sslab.kanban.workflow.entity.CommittedCard;

public class CommittedCardMapper {
    public static CommittedCardDto toDto(CommittedCard committedCard){
        CommittedCardDto dto= new CommittedCardDto();
        dto.setCardId(committedCard.getCardId());
        dto.setLaneId(committedCard.getLaneId());
        dto.setOrder(committedCard.getOrder());
        return dto;
    }
}
