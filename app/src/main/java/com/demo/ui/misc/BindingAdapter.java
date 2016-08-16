package com.demo.ui.misc;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.List;

/**
 * An implementation of {@link android.widget.BaseAdapter} which uses the new/bind pattern and
 * view holder pattern for its views.
 *
 * Inspired by/baesd on Jake Wharton's BindingAdapter:
 * https://gist.github.com/JakeWharton/5423616
 *
 * Pulled from Patrick Hammond's https://gist.github.com/patrickhammond/6094827
 *
 * @param <T> The type of item being displayed.
 * @param <H> The view holder type being used.
 */
public abstract class BindingAdapter<T, H extends ViewHolder> extends BaseAdapter {
    private final Context mContext;
    protected final List<T> mList;
    private final LayoutInflater inflater;

    public BindingAdapter(Context context, List<T> list) {
        this.mContext = context;
        this.mList = list;
        this.inflater = LayoutInflater.from(context);
    }

    public Context getContext() {
        return mContext;
    }

    @Override
    public final View getView(int position, View view, ViewGroup container) {
        if (view == null) {
            view = newView(inflater, position, container);
            if (view == null) {
                throw new IllegalStateException("newView result must not be null.");
            }

            associateViewHolder(view);
        }

        bindView(getItem(position), position, view, getViewHolder(view));
        return view;
    }

    /** Create a new instance of a view for the specified position. */
    public abstract View newView(LayoutInflater inflater, int position, ViewGroup container);

    /**
     * If your ViewHolder implementation looks something like this:
     * <pre>
     * {@code
     * static class ViewHolder {
     *     final TextView textView;
     *
     *     ViewHolder(View view) {
     *         textView = (TextView) view.findViewById(R.id.textView);
     *     }
     * }
     * </pre>
     *
     * This method only needs this as its implementation:
     * <pre>
     * {@code
     * return new ViewHolder(view);
     * }
     * </pre>
     *
     * If implementations do not need/want a view holder, just return <code>null</code>.
     */
    public abstract H buildViewHolder(View view);

    /**
     * Bind the data for the specified {@code position} to the view using a
     * {@code viewHolder} created from {@link #buildViewHolder(android.view.View)}.
     */
    public abstract void bindView(T item, int position, View view, H viewHolder);

    @Override
    public final View getDropDownView(int position, View view, ViewGroup container) {
        if (view == null) {
            view = newDropDownView(inflater, position, container);
            if (view == null) {
                throw new IllegalStateException("newDropDownView result must not be null.");
            }

            associateViewHolder(view);
        }

        bindDropDownView(getItem(position), position, view, getViewHolder(view));
        return view;
    }

    private void associateViewHolder(View view) {
        H viewHolder = buildViewHolder(view);
        view.setTag(viewHolder);
    }

    @SuppressWarnings("unchecked")
    private H getViewHolder(View view) {
        return (H) view.getTag();
    }

    /** Create a new instance of a drop-down view for the specified position. */
    public View newDropDownView(LayoutInflater inflater, int position, ViewGroup container) {
        return newView(inflater, position, container);
    }

    /** Bind the data for the specified {@code position} to the drop-down view. */
    public void bindDropDownView(T item, int position, View view, H viewHolder) {
        bindView(item, position, view, viewHolder);
    }

    //Adapter methods
    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public T getItem(int position) {
        return mList.get(position);
    }
}