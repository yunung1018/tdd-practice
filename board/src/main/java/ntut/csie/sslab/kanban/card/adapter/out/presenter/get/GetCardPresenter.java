package ntut.csie.sslab.kanban.card.adapter.out.presenter.get;

import ntut.csie.sslab.ddd.adapter.presenter.Presenter;
import ntut.csie.sslab.kanban.card.usecase.port.in.get.GetCardOutput;

public class GetCardPresenter implements Presenter<GetCardOutput, CardViewModel> {
    private CardViewModel viewModel;

    @Override
    public CardViewModel buildViewModel(GetCardOutput data) {
        viewModel = new CardViewModel();
        viewModel.setCard(data.getCard());
        return viewModel;
    }
}
