package ntut.csie.sslab.kanban.board.entity;

public class Board1 {
    private String id;
    private String name;

    
    public Board1(String id, String name) {
        this.id = id;
        this.name = name;
    }
    public String getId() {
        return id;
    }
    public String getName() {
        return name;
    }   
    public void setName(String name) {
        this.name = name;
    }
    public void setId(String id) {
        this.id = id;
    }
}
