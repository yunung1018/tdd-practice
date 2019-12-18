package tw.teddysoft.clean.adapter.gateway.kanbanboard;


import tw.teddysoft.clean.domain.model.kanbanboard.board.Board;
import tw.teddysoft.clean.usecase.kanbanboard.board.BoardRepository;

import java.util.ArrayList;
import java.util.List;

public class SerializableBoardRepository implements BoardRepository {
    private final String STORED_FILE_NAME = "board-repository.ser";
    private List<Board> boards;

    public SerializableBoardRepository(){

        boards = new ArrayList<Board>();

        if (SerializationUtil.storedFileExists(STORED_FILE_NAME)){
            boards = (List<Board>) SerializationUtil.loadFromFile(STORED_FILE_NAME);
        }
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
            if (each.getName().equals(name))
                return each;
        }
        throw new RuntimeException("Cannot find board with name : " + name);
    }

    public Board save(Board arg) {
        if (boards.contains(arg)){
            SerializationUtil.saveToFile(STORED_FILE_NAME, boards);
            return arg;
        }
        else if (boards.add(arg)){
            SerializationUtil.saveToFile(STORED_FILE_NAME, boards);
            return arg;
        }
        else
            return null;
    }

    public boolean remove(Board arg) {
        boolean result =  boards.remove(arg);
        SerializationUtil.saveToFile(STORED_FILE_NAME, boards);
        return result;
    }

}
