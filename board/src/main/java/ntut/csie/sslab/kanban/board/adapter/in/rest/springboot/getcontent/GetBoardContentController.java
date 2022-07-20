package ntut.csie.sslab.kanban.board.adapter.rest.getcontent;

import ntut.csie.sslab.kanban.board.adapter.presenter.getcontent.BoardContentBasicViewModel;
import ntut.csie.sslab.kanban.board.adapter.presenter.getcontent.GetBoardContentPresenter;
import ntut.csie.sslab.kanban.board.usecase.getcontent.in.GetBoardContentInput;
import ntut.csie.sslab.kanban.board.usecase.getcontent.in.GetBoardContentUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GetBoardContentController {
    private GetBoardContentUseCase getBoardContentUseCase;

    @Autowired
    public void setGetBoardContentUseCase(GetBoardContentUseCase getBoardContentUseCase) {
        this.getBoardContentUseCase = getBoardContentUseCase;
    }

    @GetMapping(path = "${KANBAN_PREFIX}/boards/{boardId}/content", produces = "application/json")
    public BoardContentBasicViewModel getBoardContent(@PathVariable("boardId") String boardId) {

        GetBoardContentInput input = new GetBoardContentInput();
        input.setBoardId(boardId);
        GetBoardContentPresenter presenter = new GetBoardContentPresenter();

        BoardContentBasicViewModel viewModel = presenter.buildViewModel(getBoardContentUseCase.execute(input));

        return viewModel;
    }
}
