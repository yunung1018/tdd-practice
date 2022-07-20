package ntut.csie.sslab.account.user.adapter.get;

import ntut.csie.sslab.account.user.usecase.get.GetUserOutput;
import ntut.csie.sslab.ddd.adapter.presenter.Presenter;

import static ntut.csie.sslab.ddd.model.common.Contract.requireNotNull;

public class GetUserPresenter implements Presenter<GetUserOutput, UserBasicViewModel> {


    @Override
    public UserBasicViewModel buildViewModel(GetUserOutput data) {
        requireNotNull("GetUserOutput", data);

        UserBasicViewModel viewModel = new UserBasicViewModel();
        viewModel.setUser(data.getUser());
        viewModel.setMessage(data.getMessage());
        viewModel.setExitCode(data.getExitCode());
        viewModel.setId(data.getId());

        return viewModel;
    }
}
