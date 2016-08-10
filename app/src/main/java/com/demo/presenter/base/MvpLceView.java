package com.demo.presenter.base;

/**
 * @param <M> The type of the data displayed in this view
 */
public interface MvpLceView<M> extends MvpView {

    /**
     * Display a loading view while loading data in background.
     * <b>The loading view must have the id = R.id.loadingView</b>
     *
     * @param pullToRefresh true, if pull-to-refresh has been invoked loading.
     */
    void showLoading(boolean pullToRefresh);

    /**
     * Show the content view.
     * <p>
     * <b>The content view must have the id = R.id.contentView</b>
     */
    void showContent();

    /**
     * Show the error view.
     * <b>The error view must be a TextView with the id = R.id.errorView</b>
     *
     * @param e             The Throwable that has caused this error
     * @param pullToRefresh true, if the exception was thrown during pull-to-refresh, otherwise
     *                      false.
     */
    void showError(Error e, boolean pullToRefresh);

    void hideError();

    void showRetry();

    void hideRetry();

    /**
     * The data that should be displayed with {@link #showContent()}
     */
    void setData(M data);
}