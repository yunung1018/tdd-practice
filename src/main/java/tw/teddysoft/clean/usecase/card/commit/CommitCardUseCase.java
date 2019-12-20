package tw.teddysoft.clean.usecase.card.commit;

import tw.teddysoft.clean.domain.usecase.UseCase;
import tw.teddysoft.clean.usecase.card.move.MoveCommittedCardInput;
import tw.teddysoft.clean.usecase.card.move.MoveCommittedCardOutput;

public interface CommitCardUseCase
        extends UseCase<CommitCardInput, CommitCardOutput> {
}
