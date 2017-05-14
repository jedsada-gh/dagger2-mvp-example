package com.wisdomlanna.www.dagger2_mvp_example.ui.showlist;

import com.wisdomlanna.www.dagger2_mvp_example.ui.base.BaseInterface;

class ShowListInterface {

    interface View extends BaseInterface.View {
        void testResult();
    }

    interface Presenter {
        void test();
    }
}
