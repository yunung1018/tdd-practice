package tw.teddysoft.clean.domain.model.kanbanboard.stage;

import org.junit.Before;
import tw.teddysoft.clean.domain.model.DomainEvent;
import tw.teddysoft.clean.domain.model.DomainEventPublisher;
import tw.teddysoft.clean.domain.model.DomainEventSubscriber;

import java.util.ArrayList;
import java.util.List;

public class AbstractDomainEventTest {

    protected FakeSubscriber subscriber;
    protected FakeStoredSubscriber storedSubscriber;

    @Before
    public void setUp(){
        subscriber = new FakeSubscriber();
        storedSubscriber = new FakeStoredSubscriber();

        DomainEventPublisher.instance().subscribe(subscriber);
        DomainEventPublisher.instance().subscribe(storedSubscriber);
    }

    protected class FakeSubscriber implements DomainEventSubscriber<DomainEvent> {
        public String expectedResult;
        @Override
        public Class<DomainEvent> subscribedToEventType() {
            return DomainEvent.class;
        }
        @Override
        public void handleEvent(DomainEvent domainEvent) {
            expectedResult = domainEvent.detail();
        }
    }


    protected class FakeStoredSubscriber implements DomainEventSubscriber<DomainEvent> {
        public List<String> expectedResults = new ArrayList<>();

        @Override
        public Class<DomainEvent> subscribedToEventType() {
            return DomainEvent.class;
        }

        @Override
        public void handleEvent(DomainEvent domainEvent) {
            expectedResults.add(domainEvent.detail());
        }
    }

}
