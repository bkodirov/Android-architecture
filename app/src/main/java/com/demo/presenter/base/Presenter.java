package com.demo.presenter.base;

public abstract class Presenter<V extends MvpView> {

  protected V mView;


  public void attachView(V view) {
    mView = view;
    onViewAttached();
  }

  public void detachView() {

  }

  protected V getView() {
    return mView;
  }

  public abstract void onViewAttached();
}
