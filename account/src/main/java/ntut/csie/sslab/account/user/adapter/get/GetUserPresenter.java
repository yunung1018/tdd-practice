package ntut.csie.sslab.account.users.adapter.get;

import ntut.csie.sslab.account.users.usecase.get.in.GetUserOutput;
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
