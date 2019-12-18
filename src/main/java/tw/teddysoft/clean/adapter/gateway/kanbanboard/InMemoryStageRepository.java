package tw.teddysoft.clean.adapter.gateway.kanbanboard;

import tw.teddysoft.clean.domain.model.kanbanboard.stage.Stage;
import tw.teddysoft.clean.usecase.kanbanboard.stage.StageRepository;

import java.util.ArrayList;
import java.util.List;

public class InMemoryStageRepository implements StageRepository {

    private List<Stage> stages;

    public InMemoryStageRepository(){
        stages = new ArrayList<Stage>();
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
            return arg;
        }
        else if (stages.add(arg)){
            return arg;
        }
        else
            return null;
    }

    public boolean remove(Stage arg) {
        return stages.remove(arg);
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
