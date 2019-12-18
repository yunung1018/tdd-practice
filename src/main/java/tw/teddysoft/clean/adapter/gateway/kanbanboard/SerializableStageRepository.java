package tw.teddysoft.clean.adapter.gateway.kanbanboard;

import tw.teddysoft.clean.domain.model.kanbanboard.stage.Stage;
import tw.teddysoft.clean.usecase.kanbanboard.stage.StageRepository;

import java.util.ArrayList;
import java.util.List;

public class SerializableStageRepository implements StageRepository {

    private final List<Stage> stages;
    private final String STORED_FILE_NAME = "stage-repository.ser";

    public SerializableStageRepository(){

        if (SerializationUtil.storedFileExists(STORED_FILE_NAME)){
            stages = (List<Stage>) SerializationUtil.loadFromFile(STORED_FILE_NAME);
        }
        else{
            stages = new ArrayList<Stage>();
        }
    }

    public List<Stage> findAll() {
        return stages;
    }

    public Stage findById(String id) {
        for(Stage each : stages){
            if (each.getId().equalsIgnoreCase(id))
                return each;
        }
        throw new RuntimeException("Cannot find stage with id : " + id);
    }

    public Stage findFirstByName(String name) {
        for(Stage each : stages){
            if (each.getName().equals(name))
                return each;
        }
        throw new RuntimeException("Cannot find stage with name : " + name);
    }

    public Stage save(Stage arg) {
        if (stages.contains(arg)) {
            SerializationUtil.saveToFile(STORED_FILE_NAME, stages);
            return arg;
        }
        else if (stages.add(arg)){
            SerializationUtil.saveToFile(STORED_FILE_NAME, stages);
            return arg;
        }
        else
            return null;
    }

    public boolean remove(Stage arg) {
        boolean result =  stages.remove(arg);
        SerializationUtil.saveToFile(STORED_FILE_NAME, stages);
        return result;
    }

    public List<Stage> findByBoardId(String boardId) {
        List<Stage> results = new ArrayList<Stage>();

        for(Stage each : stages){
            if (each.getBoardId().equalsIgnoreCase(boardId))
                results.add(each);
        }
        return results;
    }

}
