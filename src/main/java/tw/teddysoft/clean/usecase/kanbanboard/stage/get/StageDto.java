package tw.teddysoft.clean.usecase.kanbanboard.stage.get;

import java.util.List;

public class StageDto {

    private String name;
    private String id;
    private String boardId;
    private List<MiniStageDto> miniStages;
    private int ordering;

    public StageDto(String name, String id, int ordering, String boardId, List<MiniStageDto> miniStages){
        this.name = name;
        this.id = id;
        this.ordering = ordering;
        this.boardId = boardId;
        this.miniStages = miniStages;
    }

    public String getName() {
        return name;
    }

    public String getId() {
        return id;
    }

    public String getBoardId() {
        return boardId;
    }

    public List<MiniStageDto> getMiniStageDtos() {
        return miniStages;
    }

    public int getOrdering() {
        return ordering;
    }
}
