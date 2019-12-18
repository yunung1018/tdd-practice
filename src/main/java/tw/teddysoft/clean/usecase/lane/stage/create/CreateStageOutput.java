package tw.teddysoft.clean.usecase.lane.stage.create;

import tw.teddysoft.clean.domain.usecase.Output;

public interface CreateStageOutput extends Output {

    String getId();
    void setId(String id);

}
