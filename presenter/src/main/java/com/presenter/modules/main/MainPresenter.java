package com.presenter.modules.main;

import com.presenter.Presenter;


public class MainPresenter extends Presenter<MainView> {


  @Override public void onViewAttached() {
    getView().initUi();
  }

  public void onResume() {
    refreshContactList();
  }

  public void onRefresh() {
    getView().refreshUi();
    refreshContactList();
  }

  private void refreshContactList() {
  }
}
