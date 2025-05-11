package ntut.csie.sslab.kanban.board.usecase;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Test;

import ntut.csie.sslab.kanban.board.entity.Board1;
import ntut.csie.sslab.kanban.board.usecase.port.in.create.CreateBoardInput1;
import ntut.csie.sslab.kanban.board.usecase.port.in.create.CreateBoardOutput1;
import ntut.csie.sslab.kanban.board.usecase.port.in.create.CreateBoardUseCase1;
import ntut.csie.sslab.kanban.board.usecase.port.in.notify.NotifyBoardCreatedUseCase;
import ntut.csie.sslab.kanban.board.usecase.port.out.BoardRepository1;

// import ntut.csie.sslab.kanban.board.usecase.port.in.create.CreateBoardUseCase;

public class CreateBoardUseCaseTest1 {
    @Test
    public void create_board_successfully() {
        CreateBoardInput1 input = new CreateBoardInput1();
        input.setBoardId("boardId");
        input.setName("boardName");


        BoardRepository1 repository = new BoardRepository1(); 
        CreateBoardUseCase1 useCase = new CreateBoardUseCase1(repository); // 建立 Use Case 實例
 

        //最先寫的
        CreateBoardOutput1 output = useCase.execute(input);      // 用物件來呼叫

        // Simulate fetching the saved board from the repository
        Board1 savedBoard = repository.findById(output.getId());

        //assertions
        assertNotNull(output.getId());  
        assertEquals("boardName", savedBoard.getName());
        assertEquals(output.getId(), savedBoard.getId());
    }

    @Test
    public void notify_team_members_when_board_is_created() {
        FakeNotificationService notificationService = new FakeNotificationService();
        NotifyBoardCreatedUseCase notifyUseCase = new NotifyBoardCreatedUseCase(notificationService);
        
        notifyUseCase.execute("board123", "My Board");
    
        
        assertTrue( notificationService.wasCalled());
        assertEquals("board123", notificationService.getLastBoardId());
        assertEquals("My Board", notificationService.getLastBoardName());
    }

}
