package ntut.csie.sslab.team.usecase.create;

import ntut.csie.sslab.ddd.usecase.Input;

public class CreateTeamInput implements Input {
    private String userId;
    private String teamName;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }
}
