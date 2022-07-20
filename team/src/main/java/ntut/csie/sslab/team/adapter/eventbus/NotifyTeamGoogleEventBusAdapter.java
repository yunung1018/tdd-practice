package ntut.csie.sslab.team.adapter.eventbus;

import com.google.common.eventbus.Subscribe;
import ntut.csie.sslab.kanban.board.entity.event.BoardCreated;
import ntut.csie.sslab.kanban.board.entity.event.BoardRenamed;

public class NotifyTeamGoogleEventBusAdapter {

    @Subscribe
    public void whenBoardCreatedInTeam(BoardCreated event) {
        // To be handled
    }

    @Subscribe
    public void whenBoardRenamed(BoardRenamed event){
        // To be handled
    }
}
