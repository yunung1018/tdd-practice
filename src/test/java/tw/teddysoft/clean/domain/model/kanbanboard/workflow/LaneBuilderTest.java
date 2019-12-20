package tw.teddysoft.clean.domain.model.kanbanboard.workflow;

import org.junit.Test;
import tw.teddysoft.clean.domain.model.AbstractDomainEventTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.*;

public class LaneBuilderTest extends AbstractDomainEventTest {

    @Test
    public void default_lane_created_by_lanebuilder_is_ministage() {
        Lane lane = LaneBuilder.getInstance()
                .title("Backlog")
                .workflowId("000-1234")
                .build();

        assertEquals(LaneOrientation.VERTICAL, lane.getOrientation());
        assertTrue(lane instanceof Stage);
        assertEquals(0, lane.getWipLimit());
        assertEquals("Backlog", lane.getTitle());
        assertEquals("000-1234", lane.getWorkflowId());

        assertThat(storedSubscriber.expectedResults.size()).isEqualTo(1);
        assertThat(storedSubscriber.expectedResults.get(0)).startsWith("StageCreated");
    }

    @Test
    public void create_stage() {
        Lane lane = LaneBuilder.getInstance()
                .title("Backlog")
                .workflowId("000-1234")
                .swimlane()
                .stage()
                .wipLimit(5)
                .build();

        assertEquals(5, lane.getWipLimit());
        assertEquals(LaneOrientation.VERTICAL, lane.getOrientation());
        assertThat(storedSubscriber.expectedResults.size()).isEqualTo(1);
        assertThat(storedSubscriber.expectedResults.get(0)).startsWith("StageCreated");
    }

    @Test
    public void create_swimlane() {
        Lane lane = LaneBuilder.getInstance()
                .title("Backlog")
                .workflowId("000-1234")
                .stage()
                .swimlane()
                .wipLimit(1)
                .build();

        assertEquals(LaneOrientation.HORIZONTAL, lane.getOrientation());
        assertEquals(1, lane.getWipLimit());
        assertThat(storedSubscriber.expectedResults.size()).isEqualTo(1);
        assertThat(storedSubscriber.expectedResults.get(0)).startsWith("SwimlaneCreated");
    }

}
