package tw.teddysoft.clean.domain.model.kanbanboard.workspace;

import org.junit.Test;
import tw.teddysoft.clean.usecase.Context;
import tw.teddysoft.clean.usecase.TestContext;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;

public class WorkspaceTest {

    @Test
    public void creating_workspace_should_generate_a_WorkspaceCreated_event() {
        Workspace workspace = new Workspace("Teddy's Workspace", Context.USER_ID);

        assertEquals("Teddy's Workspace", workspace.getName());
        assertEquals(Context.USER_ID, workspace.getUserId());
        assertThat(workspace.getDomainEvents().size()).isEqualTo(1);
        assertThat(workspace.getDomainEvents().get(0).detail()).startsWith("WorkspaceCreated");

    }
}
