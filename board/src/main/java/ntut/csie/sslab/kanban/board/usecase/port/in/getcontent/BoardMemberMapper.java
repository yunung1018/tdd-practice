package ntut.csie.sslab.kanban.board.usecase.port.in;


import ntut.csie.sslab.kanban.board.entity.BoardMember;

import java.util.ArrayList;
import java.util.List;

public class BoardMemberMapper {

    public static BoardMemberDto toDto(BoardMember boardMember) {
        BoardMemberDto dto = new BoardMemberDto();
        dto.setBoardId(boardMember.getBoardId());
        dto.setUserId(boardMember.getUserId());
        dto.setMemberType(boardMember.getMemberType());
        return dto;
    }

    public static List<BoardMemberDto> toDto(List<BoardMember> boardMembers) {
        List<BoardMemberDto> boardMemberDtos = new ArrayList<>();
        for(BoardMember boardMember : boardMembers) {
            boardMemberDtos.add(toDto(boardMember));
        }

        return boardMemberDtos;
    }
}
