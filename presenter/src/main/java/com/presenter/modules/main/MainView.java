package com.presenter.modules.main;

import com.presenter.model.SomeUiModel;

import java.util.List;


public interface MainView {

  void initUi();

  void showGetContactsError();

  void refreshContactsList(List<SomeUiModel> contacts);

  void refreshUi();
}
