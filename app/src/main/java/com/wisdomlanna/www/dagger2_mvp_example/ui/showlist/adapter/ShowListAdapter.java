package com.wisdomlanna.www.dagger2_mvp_example.ui.showlist.adapter;

import android.view.ViewGroup;

import com.wisdomlanna.www.dagger2_mvp_example.ui.base.adapter.BaseListAdapter;

public class ShowListAdapter extends BaseListAdapter<ShowListViewHolder, ShowListPresenterInterface.Presenter>
        implements ShowListPresenterInterface.Adapter {

    @Override
    public ShowListPresenterInterface.Presenter createPresenter() {
        return ShowListAdapterPresenter.create();
    }

    @Override
    public ShowListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(ShowListViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }
}
