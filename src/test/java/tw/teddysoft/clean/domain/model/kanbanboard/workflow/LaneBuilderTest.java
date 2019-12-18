package tw.teddysoft.clean.domain.model.kanbanboard.workflow;

import org.junit.Test;
import tw.teddysoft.clean.domain.model.AbstractDomainEventTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

public class LaneBuilderTest extends AbstractDomainEventTest {

    @Test
    public void default_lane_created_by_lanebuilder_is_ministage() {
        Lane lane = LaneBuilder.getInstance()
                .title("Backlog")
                .workflowId("000-1234")
                .build();

        assertEquals(LaneOrientation.VERTICAL, lane.getOrientation());
        assertFalse(lane instanceof Stage);
        assertEquals("Backlog", lane.getTitle());
        assertEquals("000-1234", lane.getWorkflowId());

        assertThat(storedSubscriber.expectedResults.size()).isEqualTo(1);
        assertThat(storedSubscriber.expectedResults.get(0)).startsWith("MinistageCreated");
    }

    @Test
    public void create_stage() {
        Lane lane = LaneBuilder.getInstance()
                .title("Backlog")
                .workflowId("000-1234")
                .stage()
                .build();

        assertEquals(LaneOrientation.VERTICAL, lane.getOrientation());
        assertThat(storedSubscriber.expectedResults.size()).isEqualTo(1);
        assertThat(storedSubscriber.expectedResults.get(0)).startsWith("StageCreated");
    }

    @Test
    public void create_ministage() {
        Lane lane = LaneBuilder.getInstance()
                .title("Backlog")
                .workflowId("000-1234")
                .stage()
                .ministage()
                .build();

        assertEquals(LaneOrientation.VERTICAL, lane.getOrientation());
        assertThat(storedSubscriber.expectedResults.size()).isEqualTo(1);
        assertThat(storedSubscriber.expectedResults.get(0)).startsWith("MinistageCreated");
    }

    @Test
    public void create_swimlane() {
        Lane lane = LaneBuilder.getInstance()
                .title("Backlog")
                .workflowId("000-1234")
                .ministage()
                .swimlane()
                .build();

        assertEquals(LaneOrientation.HORIZONTAL, lane.getOrientation());
        assertThat(storedSubscriber.expectedResults.size()).isEqualTo(1);
        assertThat(storedSubscriber.expectedResults.get(0)).startsWith("SwimlaneCreated");
    }

}
