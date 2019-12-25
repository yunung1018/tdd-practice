package tw.teddysoft.clean.app.console.spring.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import tw.teddysoft.clean.adapter.presenter.kanbanboard.stage.MultipleStagePresenter;
import tw.teddysoft.clean.adapter.presenter.kanbanboard.stage.SingleStagePresenter;
import tw.teddysoft.clean.usecase.kanbanboard.board.BoardRepository;
import tw.teddysoft.clean.usecase.kanbanboard.old_ministage.update.UpdateMiniStageInput;
import tw.teddysoft.clean.usecase.kanbanboard.old_ministage.update.UpdateMiniStageOutput;
import tw.teddysoft.clean.usecase.kanbanboard.old_ministage.update.UpdateMiniStageUseCase;
import tw.teddysoft.clean.usecase.kanbanboard.old_ministage.update.impl.UpdateMiniStageUseCaseImpl;
import tw.teddysoft.clean.usecase.kanbanboard.old_stage.StageRepository;
import tw.teddysoft.clean.usecase.kanbanboard.old_stage.add.AddStageInput;
import tw.teddysoft.clean.usecase.kanbanboard.old_stage.add.AddStageOutput;
import tw.teddysoft.clean.usecase.kanbanboard.old_stage.add.AddStageUseCase;
import tw.teddysoft.clean.usecase.kanbanboard.old_stage.add.impl.AddStageUseCaseImpl;
import tw.teddysoft.clean.usecase.kanbanboard.old_stage.get.GetStageInput;
import tw.teddysoft.clean.usecase.kanbanboard.old_stage.get.GetStageOutput;
import tw.teddysoft.clean.usecase.kanbanboard.old_stage.get.GetStageUseCase;
import tw.teddysoft.clean.usecase.kanbanboard.old_stage.get.impl.GetStageUseCaseImpl;

//TODO
@Configuration
public class UseCaseBeanConfiguration {

  @Autowired
  private StageRepository stageRepository;

  @Autowired
  private BoardRepository boardRepository;

  @Bean(name="addStageUseCase")
  public AddStageUseCase addStageUseCase() {
    return new AddStageUseCaseImpl(stageRepository);
  }
  @Bean(name="addStageInput")
  public AddStageInput addStageInput() {
//    return AddStageUseCaseImpl.createInput();
    return null;
  }
  @Bean(name="addStageOutput")
  public AddStageOutput addStageOutput() {
    return new SingleStagePresenter();
  }

  @Bean(name="getStageUseCase")
  public GetStageUseCase getStageUseCase() {
    return new GetStageUseCaseImpl(boardRepository, stageRepository);
  }
  @Bean(name="getStageInput")
  public GetStageInput getStageInput() {
//    return GetStageUseCaseImpl.createInput();
    return null;
  }
  @Bean(name="getStageOutput")
  public GetStageOutput getStageOutput() {
    return new MultipleStagePresenter();
  }


  @Bean(name="updateMiniStageUseCase")
  public UpdateMiniStageUseCase updateMiniStageUseCase() {

//    return new UpdateMiniStageUseCaseImpl(stageRepository
    return null;
  }

  @Bean(name="getUpdateMiniStateInput")
  public UpdateMiniStageInput getUpdateMiniStateInput() {
//    return UpdateMiniStageUseCaseImpl.createInput();
  return null;
  }
  @Bean(name="getUpdateMiniStageOutput")
  public UpdateMiniStageOutput getUpdateMiniStageOutput() {

    return new UpdateMiniStageOutput(){};
  }


  @Autowired
  UpdateMiniStageUseCase updateMiniStageUseCase;
  @Autowired
  UpdateMiniStageInput updateMiniStageInput;



}
