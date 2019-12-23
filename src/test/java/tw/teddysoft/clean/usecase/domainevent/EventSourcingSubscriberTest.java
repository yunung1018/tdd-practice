package tw.teddysoft.clean.usecase.domainevent;

import org.junit.Before;
import org.junit.Test;
import tw.teddysoft.clean.adapter.gateway.kanbanboard.InMemoryDomainEventRepository;
import tw.teddysoft.clean.domain.model.PersistentDomainEvent;
import tw.teddysoft.clean.domain.model.kanbanboard.WipLimitExceedException;
import tw.teddysoft.clean.domain.model.AbstractDomainEventTest;
import tw.teddysoft.clean.domain.model.kanbanboard.old_stage.Stage;
import tw.teddysoft.clean.usecase.KanbanTestUtility;
import tw.teddysoft.clean.usecase.domainevent.sourcing.RegisterEventSourcingSubscriberUseCase;
import tw.teddysoft.clean.usecase.domainevent.sourcing.impl.RegisterEventSourcingSubscriberUseCaseImpl;

import static org.assertj.core.api.Assertions.assertThat;

public class EventSourcingSubscriberTest extends AbstractDomainEventTest {

    private Stage stage;
    private DomainEventRepository<PersistentDomainEvent> domainEventRepository;
    private KanbanTestUtility util;

    @Before
    public void mySetUp(){
        util = new KanbanTestUtility();
//        domainEventRepository = new SerializableDomainEventRepository();
        domainEventRepository = new InMemoryDomainEventRepository();
        RegisterEventSourcingSubscriberUseCase useCase = new RegisterEventSourcingSubscriberUseCaseImpl(domainEventRepository);
        useCase.execute(null,  null);
    }

    @Test
    public void creating_a_workitem_publishes_a_WorkItemCreated_event() throws WipLimitExceedException {
//        storedSubscriber.expectedResults.clear();

        // TODO : Fix Me

//        util.createScrumBoardAndStage();
//
//        util.createCardOnScrumBoard(new String [] {"Apple Pay", "Line Pay", "Pay by Master Card"});
//
//        assertThat(domainEventRepository.findAll().size()).isEqualTo(13);
//        assertThat(domainEventRepository.findAll().get(0).getDetail()).startsWith("BoardCreated");
//        assertThat(domainEventRepository.findAll().get(1).getDetail()).startsWith("WorkflowCreated");
//        assertThat(domainEventRepository.findAll().get(2).getDetail()).startsWith("WorkflowCommitted");
//        assertThat(domainEventRepository.findAll().get(3).getDetail()).startsWith("WorkflowCommitted");
//        assertThat(domainEventRepository.findAll().get(4).getDetail()).startsWith("BoardCreated");
//        assertThat(domainEventRepository.findAll().get(5).getDetail()).startsWith("BoardCreated");
//        assertThat(domainEventRepository.findAll().get(6).getDetail()).startsWith("BoardCreated");
//        assertThat(domainEventRepository.findAll().get(7).getDetail()).startsWith("BoardCreated");
//        assertThat(domainEventRepository.findAll().get(8).getDetail()).startsWith("BoardCreated");
//        assertThat(domainEventRepository.findAll().get(9).getDetail()).startsWith("CardCreated");
//        assertThat(domainEventRepository.findAll().get(10).getDetail()).startsWith("CardCommitted");
//        assertThat(domainEventRepository.findAll().get(11).getDetail()).startsWith("CardCommitted");
//        assertThat(domainEventRepository.findAll().get(12).getDetail()).startsWith("CardCommitted22");

    }

}
