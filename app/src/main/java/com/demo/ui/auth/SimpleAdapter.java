package com.demo.ui.auth;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.demo.DemoApp;
import com.demo.domain.entity.Country;

import java.util.List;

import no.noname.baseapp.R;

/**
 * Created by Beka on 8/13/16.
 */
public class SimpleAdapter extends RecyclerView.Adapter<SimpleViewHolder> {
    private final List<Country> mDataList;

    public SimpleAdapter(List<Country> dataList) {
        this.mDataList = dataList;
    }

    @Override
    public SimpleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(DemoApp.getContext()).inflate(R.layout.cell_simple_list, parent, false);
        return new SimpleViewHolder(v);
    }

    @Override
    public void onBindViewHolder(SimpleViewHolder holder, int position) {
        holder.bind(mDataList.get(position));
    }

    @Override
    public int getItemCount() {
        return mDataList.size();
    }
}
