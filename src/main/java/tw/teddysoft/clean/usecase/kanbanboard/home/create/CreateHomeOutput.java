package tw.teddysoft.clean.usecase.kanbanboard.home.create;

import tw.teddysoft.clean.domain.usecase.Output;

public interface CreateHomeOutput extends Output {
    void setHomeId(String id);
    String getHomeId();
}
