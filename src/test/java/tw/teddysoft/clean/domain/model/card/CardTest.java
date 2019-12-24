package tw.teddysoft.clean.domain.model.card;

import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class CardTest {

    @Before
    public void setUp(){

    }

    @Test
    public void creating_a_card_should_publish_a_CardCreated_event() {
        Card card = new Card("Implement apply pay.");
        assertThat(card.getDomainEvents().size()).isEqualTo(1);
        assertThat(card.getDomainEvents().get(0).detail()).startsWith("CardCreated[Name='Implement apply pay.'");
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
