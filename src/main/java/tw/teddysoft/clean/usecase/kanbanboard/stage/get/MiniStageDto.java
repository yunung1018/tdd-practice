package tw.teddysoft.clean.usecase.kanbanboard.stage.get;

import java.util.List;

public class MiniStageDto {

    private List<SwimLaneDto> swimLanes;
    private String name;
    private String id;

    public MiniStageDto(String name, String id, List<SwimLaneDto> swimLanes) {
        this.name = name;
        this.id = id;
        this.swimLanes = swimLanes;
    }

    public List<SwimLaneDto> getSwimLaneDtos() {
        return swimLanes;
    }

    public String getName() {
        return name;
    }

    public String getId() {
        return id;
    }
}
