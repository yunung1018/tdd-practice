package ntut.csie.sslab.kanban.board.usecase.port.in.create;

import org.checkerframework.checker.units.qual.C;

public class CreateBoardOutput1 {
    private String id;
    private String name;
    public CreateBoardOutput1(String string) {
        this.id = string;
    }
    public CreateBoardOutput1(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
