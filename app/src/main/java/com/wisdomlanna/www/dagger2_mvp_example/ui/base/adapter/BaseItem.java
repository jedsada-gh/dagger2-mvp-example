package com.wisdomlanna.www.dagger2_mvp_example.ui.base.adapter;

@org.parceler.Parcel(org.parceler.Parcel.Serialization.BEAN)
class BaseItem {

    private int type;

    public BaseItem(int type) {
        this.type = type;
    }

    public BaseItem() {
    }

    int getType() {
        return type;
    }
}
