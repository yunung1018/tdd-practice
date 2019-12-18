package tw.teddysoft.clean.usecase.kanbanboard.stage;

import tw.teddysoft.clean.domain.model.kanbanboard.old_stage.Stage;

import java.util.List;

public interface StageRepository {

    List<Stage> findAll();
    Stage findById(String id);

    Stage findFirstByName(String name);

    Stage save(Stage arg);
    boolean remove(Stage arg);

    List<Stage> findByBoardId(String boardId);
}
