package tw.teddysoft.clean.adapter.gateway.kanbanboard;


import tw.teddysoft.clean.domain.model.kanbanboard.board.Board;
import tw.teddysoft.clean.usecase.kanbanboard.board.BoardRepository;

import java.util.ArrayList;
import java.util.List;

public class InMemoryBoardRepository implements BoardRepository {

    private List<Board> boards;

    public InMemoryBoardRepository(){
        boards = new ArrayList<Board>();
    }

    public List<Board> findAll() {
        return boards;
    }

    public Board findById(String id) {
        for(Board each : boards){
            if (each.getId().equalsIgnoreCase(id))
                return each;
        }
        throw new RuntimeException("Cannot find board with id : " + id);
    }

    public Board findFirstByName(String name) {
        for(Board each : boards){
            if (each.getTitle().equals(name))
                return each;
        }
        throw new RuntimeException("Cannot find board with name : " + name);
    }

    public Board save(Board arg) {
        if (boards.contains(arg))
            return arg;
        else if (boards.add(arg))
            return arg;
        else
            return null;
    }

    public boolean remove(Board arg) {
        return boards.remove(arg);
    }

}
