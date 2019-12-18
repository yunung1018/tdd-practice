package tw.teddysoft.clean.domain.model.kanbanboard.workflow;

import org.junit.Test;
import tw.teddysoft.clean.domain.model.AbstractDomainEventTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

public class LaneBuilderTest extends AbstractDomainEventTest {

    @Test
    public void default_lane_orientation_is_vertical_and_non_stage() {
        Lane lane = LaneBuilder.getInstance()
                .name("Backlog")
                .workflowId("000-1234")
                .build();

        assertEquals(LaneOrientation.VERTICAL, lane.getOrientation());
        assertFalse(lane instanceof StageLane);
        assertEquals("Backlog", lane.getName());
        assertEquals("000-1234", lane.getWorkflowId());

        assertThat(storedSubscriber.expectedResults.size()).isEqualTo(1);
        assertThat(storedSubscriber.expectedResults.get(0)).startsWith("VerticalLaneCreated");
    }

    @Test
    public void create_stage_lane() {
        Lane lane = LaneBuilder.getInstance()
                .name("Backlog")
                .workflowId("000-1234")
                .horizontal()
                .stage()
                .build();

        assertEquals(LaneOrientation.VERTICAL, lane.getOrientation());
        assertThat(storedSubscriber.expectedResults.size()).isEqualTo(1);
        assertThat(storedSubscriber.expectedResults.get(0)).startsWith("StageLaneCreated");
    }

    @Test
    public void create_vertical_lane() {
        Lane lane = LaneBuilder.getInstance()
                .name("Backlog")
                .workflowId("000-1234")
                .vertical()
                .build();

        assertEquals(LaneOrientation.VERTICAL, lane.getOrientation());
        assertThat(storedSubscriber.expectedResults.size()).isEqualTo(1);
        assertThat(storedSubscriber.expectedResults.get(0)).startsWith("VerticalLaneCreated");
    }

    @Test
    public void create_horizontal_lane() {
        Lane lane = LaneBuilder.getInstance()
                .name("Backlog")
                .workflowId("000-1234")
                .vertical()
                .horizontal()
                .build();

        assertEquals(LaneOrientation.HORIZONTAL, lane.getOrientation());
        assertThat(storedSubscriber.expectedResults.size()).isEqualTo(1);
        assertThat(storedSubscriber.expectedResults.get(0)).startsWith("HorizontalLaneCreated");
    }

}
