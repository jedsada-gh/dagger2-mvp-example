package com.wisdomlanna.www.dagger2_mvp_example.ui.base.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import butterknife.ButterKnife;

public abstract class BaseViewHolder extends RecyclerView.ViewHolder {

    public BaseViewHolder(ViewGroup parent, int layout) {
        super(LayoutInflater.from(parent.getContext()).inflate(layout, parent, false));
        ButterKnife.bind(this, itemView);
    }

    protected Context getContext() {
        return itemView.getContext();
    }
}
