package ntut.csie.sslab.kanban.workflow.entity;

public class Workflow1 {
    private String id;
    private String name;
    private String boardId;

    public Workflow1(String id, String name, String boardId) {
        this.id = id;
        this.name = name;
        this.boardId = boardId;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getBoardId() {
        return boardId;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setBoardId(String boardId) {
        this.boardId = boardId;
    }
    public void setId(String id) {
        this.id = id;
    }

}
