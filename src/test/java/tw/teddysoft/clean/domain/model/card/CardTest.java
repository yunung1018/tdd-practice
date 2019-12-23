package tw.teddysoft.clean.domain.model.card;

import org.junit.Before;
import org.junit.Test;
import tw.teddysoft.clean.domain.model.DomainEventPublisher;
import tw.teddysoft.clean.domain.model.kanbanboard.WipLimitExceedException;
import tw.teddysoft.clean.domain.model.AbstractDomainEventTest;
import tw.teddysoft.clean.domain.model.kanbanboard.old_stage.Stage;

import static org.assertj.core.api.Assertions.assertThat;

public class CardTest extends AbstractDomainEventTest {

    @Before
    public void setUp(){
        super.setUp();
    }

    @Test
    public void creating_a_card_should_publish_a_CardCreated_event() {
        storedSubscriber.expectedResults.clear();
        Card card = new Card("Implement apply pay.");
        assertThat(storedSubscriber.expectedResults.size()).isEqualTo(1);
        assertThat(storedSubscriber.expectedResults.get(0)).startsWith("CardCreated[Name='Implement apply pay.'");
    }


//    @Test
//    public void committing_a_workitem_to_a_swimlane_publishes_a_WorkItemMovedIn_event() throws WipLimitExceedException {
//        Card card = new Card("", "", "Implement apply pay.");
//        storedSubscriber.expectedResults.clear();
//        String swimlaneId = stage.getDefaultMiniStage().getDefaultSwimLane().getId();
//        stage.commitWorkItemToSwimLaneById(swimlaneId, card.getId());
//
//        assertThat(storedSubscriber.expectedResults.size()).isEqualTo(1);
//        assertThat(storedSubscriber.expectedResults.get(0)).startsWith("WorkItemMovedIn");
//    }
//
//
//    @Test
//    public void uncommitting_a_workitem_from_a_swimlane_publishes_a_WorkItemMovedOut_event() throws WipLimitExceedException {
//        Card card = new Card("", "", "Implement apply pay.");
//        String swimlaneId = stage.getDefaultMiniStage().getDefaultSwimLane().getId();
//        stage.commitWorkItemToSwimLaneById(swimlaneId, card.getId());
//
//        storedSubscriber.expectedResults.clear();
//        stage.uncommitWorkItemFromSwimLaneById(swimlaneId, card.getId());
//
//        assertThat(storedSubscriber.expectedResults.size()).isEqualTo(1);
//        assertThat(storedSubscriber.expectedResults.get(0)).startsWith("WorkItemMovedOut");
//
//        System.out.println(storedSubscriber.expectedResults.get(0));
//    }


}
