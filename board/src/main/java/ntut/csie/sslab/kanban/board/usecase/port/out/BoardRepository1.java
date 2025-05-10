package ntut.csie.sslab.kanban.board.usecase.port.out;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import ntut.csie.sslab.kanban.board.entity.Board1;

public class BoardRepository1 {
    private Map<String, Board1> boards = new HashMap<>();

    public void save(Board1 board) {

        // use memory repository
        boards.put(board.getId(), board);
    }
    
    public Board1 findById(String boardId) {
        return boards.get(boardId);
    }
}   
