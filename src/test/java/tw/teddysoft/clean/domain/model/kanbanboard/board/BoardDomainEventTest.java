package tw.teddysoft.clean.domain.model.kanbanboard.board;

import org.junit.Test;
import tw.teddysoft.clean.domain.model.AbstractDomainEventTest;

import static org.assertj.core.api.Assertions.assertThat;

public class BoardDomainEventTest extends AbstractDomainEventTest {

    @Test
    public void creating_board_publishes_a_BoardCreated_event() {
        new Board("Scrum Board");
        assertThat(subscriber.expectedResult).startsWith("BoardCreated[name='Scrum Board', id=");
    }

    //TODO
    @Test
    public void deleting_board_publishes_a_BoardDeleted_event() {
        Board board = new Board("Scrum Board");
        // how to delete a board?
    }
}
