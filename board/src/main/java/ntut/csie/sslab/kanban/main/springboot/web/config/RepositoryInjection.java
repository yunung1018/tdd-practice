package ntut.csie.sslab.kanban.main.config;

import ntut.csie.sslab.ddd.adapter.gateway.GoogleEventBusAdapter;
import ntut.csie.sslab.kanban.board.adapter.out.repository.BoardRepositorySpringJpaAdapter;
import ntut.csie.sslab.kanban.board.adapter.out.repository.BoardRepositoryPeer;
import ntut.csie.sslab.kanban.card.adapter.out.repository.CardRepositoryImpl;
import ntut.csie.sslab.kanban.card.adapter.out.repository.CardRepositoryPeer;
import ntut.csie.sslab.kanban.workflow.adapter.out.repository.WorkflowRepositoryImpl;
import ntut.csie.sslab.kanban.workflow.adapter.out.repository.WorkflowRepositoryPeer;
import ntut.csie.sslab.ddd.usecase.DomainEventBus;
import ntut.csie.sslab.kanban.board.usecase.port.out.BoardRepository;
import ntut.csie.sslab.kanban.card.usecase.port.out.CardRepository;
import ntut.csie.sslab.kanban.workflow.usecase.port.out.WorkflowRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@PropertySource(value = "classpath:/application.properties")
@Configuration("KanbanRepositoryInjection")
public class RepositoryInjection {

  private BoardRepositoryPeer boardRepositoryPeer;

  private WorkflowRepositoryPeer workflowRepositoryPeer;

  private CardRepositoryPeer cardRepositoryPeer;

  @Autowired
  public void setBoardRepositoryPeer(BoardRepositoryPeer boardRepositoryPeer){
    this.boardRepositoryPeer = boardRepositoryPeer;
  }

  @Autowired
  public void setWorkflowRepositoryPeer(WorkflowRepositoryPeer workflowRepositoryPeer) {
    this.workflowRepositoryPeer = workflowRepositoryPeer;
  }

  @Autowired
  public void setCardRepositoryPeer(CardRepositoryPeer cardRepositoryPeer) {
    this.cardRepositoryPeer = cardRepositoryPeer;
  }


  @Bean(name="boardRepository")
  public BoardRepository boardRepository() {
    return new BoardRepositorySpringJpaAdapter(boardRepositoryPeer);
  }

  @Bean(name="workflowRepository")
  public WorkflowRepository workflowRepository() {
    return new WorkflowRepositoryImpl(workflowRepositoryPeer);
  }

  @Bean(name="cardRepository")
  public CardRepository cardRepository() {
    return new CardRepositoryImpl(cardRepositoryPeer);
  }


  @Bean(name="kanbanEventBus")
  public DomainEventBus eventBus() {
    return new GoogleEventBusAdapter();
  }

}
