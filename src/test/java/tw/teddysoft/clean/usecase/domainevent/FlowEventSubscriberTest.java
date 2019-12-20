package tw.teddysoft.clean.usecase.domainevent;

import org.junit.Before;
import org.junit.Test;
import tw.teddysoft.clean.adapter.gateway.kanbanboard.InMemoryDomainEventRepository;
import tw.teddysoft.clean.domain.model.FlowEvent;
import tw.teddysoft.clean.domain.model.kanbanboard.WipLimitExceedException;
import tw.teddysoft.clean.domain.model.kanbanboard.old_stage.Stage;
import tw.teddysoft.clean.usecase.KanbanTestUtility;
import tw.teddysoft.clean.usecase.domainevent.flow.RegisterFlowEventSubscriberUseCase;
import tw.teddysoft.clean.usecase.domainevent.flow.impl.RegisterFlowEventSubscriberUseCaseImpl;

import static org.assertj.core.api.Assertions.assertThat;

public class FlowEventSubscriberTest {

    private Stage stage;
    private DomainEventRepository<FlowEvent> domainEventRepository;
    private KanbanTestUtility util;

    @Before
    public void mySetUp(){
        util = new KanbanTestUtility();

        domainEventRepository = new InMemoryDomainEventRepository();
        RegisterFlowEventSubscriberUseCase useCase = new RegisterFlowEventSubscriberUseCaseImpl(domainEventRepository);
        useCase.execute(null,  null);
    }

    @Test
    public void creating_a_workitem_publishes_a_WorkItemCreated_event() throws WipLimitExceedException {
        util.createKanbanBoardAndStage();
        util.createCardOnKanbanBoard(new String [] {"Print pdf", "Print word", "Print html"});


        for(FlowEvent each : domainEventRepository.findAll()){
            System.out.println(each.detail());
        }

        assertThat(domainEventRepository.findAll().size()).isEqualTo(3);
        assertThat(domainEventRepository.findAll().get(0).detail()).startsWith("WorkItemMovedIn");
        assertThat(domainEventRepository.findAll().get(2).detail()).startsWith("WorkItemMovedIn");
    }

}
