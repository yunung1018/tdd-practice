package ntut.csie.sslab.team.entity.team;

import ntut.csie.sslab.ddd.model.Entity;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Project implements Entity<ProjectId> {

    private final ProjectId projectId;
    private TeamId teamId;
    private String name;
    private List<Board> boards;

    public Project(ProjectId projectId, TeamId teamId, String name) {
        this.projectId = projectId;
        this.name = name;
        this.teamId = teamId;
        boards = new ArrayList<>();
    }

    public Optional<Board> findBoardById(BoardId boardId) {
        for(Board each: boards) {
            if(each.getId().equals(boardId)) {
                return Optional.of(each);
            }
        }

        return Optional.empty();
    }

    public void addBoard(Board board){

        if (findBoardById(board.getId()).isPresent())
            throw new RuntimeException("Board is already in the project.");

//        board.setProjectId(id);
        boards.add(board);
    }

    public void removeBoard(Board board) {
        boards.remove(board);
    }

    public ProjectId getId() {
        return projectId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public TeamId getTeamId() {
        return teamId;
    }

    public List<Board> getBoards(){
        return boards;
    };
}
