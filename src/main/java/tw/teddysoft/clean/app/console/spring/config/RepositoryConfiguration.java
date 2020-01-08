package tw.teddysoft.clean.app.console.spring.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import tw.teddysoft.clean.adapter.gateway.kanbanboard.InMemoryAggregateRootRepositoryPeer;
import tw.teddysoft.clean.adapter.gateway.kanbanboard.SerializableAggregateRootRepositoryPeer;
import tw.teddysoft.clean.app.console.DefaultBoard;
import tw.teddysoft.clean.domain.model.kanbanboard.board.Board;
import tw.teddysoft.clean.domain.model.kanbanboard.workflow.Workflow;
import tw.teddysoft.clean.domain.usecase.repository.Repository;

@Configuration
@Order(1)
public class RepositoryConfiguration {

  @Autowired
  private Repository<Board> boardRepository;

  @Bean(name="workflowRepository")
  public Repository<Workflow> workflowRepository() {

    Repository<Workflow> workflowRepository = new Repository(new InMemoryAggregateRootRepositoryPeer());
//    WorkflowRepository workflowRepository = new SerializableWorkflowRepository();


    DefaultBoard defaultBoard = new DefaultBoard(boardRepository, workflowRepository);
    defaultBoard.createKanbanBoardGameStage();
    defaultBoard.createScrumBoardStage();

    return workflowRepository;
//    return new InMemoryStageRepository();
  }


  @Bean(name="boardRepository")
  public Repository<Board> boardRepository() {
//    BoardRepository repository = new InMemoryBoardRepository();
    Repository<Board> repository = new Repository(new SerializableAggregateRootRepositoryPeer("board-repository.ser"));
    return repository;
  }


}
