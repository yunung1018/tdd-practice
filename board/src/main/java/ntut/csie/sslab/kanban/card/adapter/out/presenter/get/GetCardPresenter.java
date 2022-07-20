package ntut.csie.sslab.kanban.adapter.presenter.card.get;

import ntut.csie.sslab.ddd.adapter.presenter.Presenter;
import ntut.csie.sslab.kanban.usecase.card.get.in.GetCardOutput;

public class GetCardPresenter implements Presenter<GetCardOutput, CardViewModel> {
    private CardViewModel viewModel;

    @Override
    public CardViewModel buildViewModel(GetCardOutput data) {
        viewModel = new CardViewModel();
        viewModel.setCard(data.getCard());
        return viewModel;
    }
}
