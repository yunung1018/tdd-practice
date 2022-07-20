package ntut.csie.sslab.team.usecase.rename;

import ntut.csie.sslab.ddd.usecase.Input;

public class RenameTeamInput implements Input {
    private String teamId;
    private String name;

    public String getTeamId() {
        return teamId;
    }

    public void setTeamId(String teamId) {
        this.teamId = teamId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
