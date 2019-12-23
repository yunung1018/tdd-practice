package tw.teddysoft.clean.domain.model.kanbanboard.workspace;

import org.junit.Test;
import tw.teddysoft.clean.domain.model.AbstractDomainEventTest;
import tw.teddysoft.clean.domain.model.kanbanboard.board.Board;
import tw.teddysoft.clean.usecase.KanbanTestUtility;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;

public class WorkspaceTest extends AbstractDomainEventTest {

    @Test
    public void creating_workspace_should_publishe_WorkspaceCreated_event() {
        Workspace workspace = new Workspace("Teddy's Workspace", KanbanTestUtility.USER_ID);

        assertEquals("Teddy's Workspace", workspace.getName());
        assertEquals(KanbanTestUtility.USER_ID, workspace.getUserId());
        assertThat(subscriber.expectedResult).startsWith("WorkspaceCreated");
    }
}
