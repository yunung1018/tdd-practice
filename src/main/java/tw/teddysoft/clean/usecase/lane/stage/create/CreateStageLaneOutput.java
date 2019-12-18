package tw.teddysoft.clean.usecase.lane.stage.create;

import tw.teddysoft.clean.domain.usecase.Output;

public interface CreateStageLaneOutput extends Output {

    String getId();
    void setId(String id);

}
