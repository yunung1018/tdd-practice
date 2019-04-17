package tw.teddysoft.clean.app.console.spring.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import tw.teddysoft.clean.adapter.gateway.kanbanboard.SerializableBoardRepository;
import tw.teddysoft.clean.adapter.gateway.kanbanboard.SerializableStageRepository;
import tw.teddysoft.clean.app.console.DefaultBoard;
import tw.teddysoft.clean.usecase.kanbanboard.board.BoardRepository;
import tw.teddysoft.clean.usecase.kanbanboard.stage.StageRepository;

@Configuration
@Order(1)
public class RepositoryConfiguration {

  @Autowired
  private BoardRepository boardRepository;

  @Bean(name="stageRepository")
  public StageRepository stageRepository() {

//    StageRepository stageRepository = new InMemoryStageRepository();
    StageRepository stageRepository = new SerializableStageRepository();


    DefaultBoard defaultBoard = new DefaultBoard(boardRepository, stageRepository);
    defaultBoard.createKanbanBoardGameStage();
    defaultBoard.createScrumBoardStage();

    return stageRepository;
//    return new InMemoryStageRepository();
  }


  @Bean(name="boardRepository")
  public BoardRepository boardRepository() {
//    BoardRepository repository = new InMemoryBoardRepository();
    BoardRepository repository = new SerializableBoardRepository();
    return repository;
  }


}
