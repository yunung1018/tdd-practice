package ntut.csie.sslab.team.entity.team;

import ntut.csie.sslab.ddd.model.ValueObject;

public class TeamName implements ValueObject {
    private String name;

    public TeamName(String name){
        super();
        this.name = name;
    }

    public String value() {
        return name;
    }

    public static TeamName valueOf(String name){
        return new TeamName(name);
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof TeamName))
            return false;

        TeamName target = (TeamName) o;
        return target.name.equals(name) ;
    }

    @Override public int hashCode() {
        int result = 17;
        result = 31 * result + name.hashCode();
        return result;
    }


}
