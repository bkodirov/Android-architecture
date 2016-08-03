package com.presenter;

public abstract class Presenter<V> {

  protected V mView;


  public void attachView(V view) {
    mView = view;
    onViewAttached();
  }

  public void detachView() {

  }

  public V getView() {
    return mView;
  }

  public abstract void onViewAttached();
}
