package tw.teddysoft.clean.domain.model.kanbanboard.board;

import org.junit.Test;
import tw.teddysoft.clean.domain.model.AbstractDomainEventTest;
import tw.teddysoft.clean.domain.model.kanbanboard.workspace.Workspace;
import tw.teddysoft.clean.usecase.KanbanTestUtility;

import static org.assertj.core.api.Assertions.assertThat;

public class BoardTest extends AbstractDomainEventTest {

    @Test
    public void creating_board_publishes_BoardCreated_event() {
        new Board("Scrum Board", KanbanTestUtility.WORKSPACE_ID);
        assertThat(storedSubscriber.expectedResults.size()).isEqualTo(1);
        assertThat(subscriber.expectedResult).startsWith("BoardCreated");
    }

    //TODO
    @Test
    public void deleting_board_publishes_a_BoardDeleted_event() {
        // how to delete a board?
    }
}
