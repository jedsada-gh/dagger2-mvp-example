package com.wisdomlanna.www.dagger2_mvp_example.template.activity;

import com.wisdomlanna.www.dagger2_mvp_example.ui.base.BaseInterface;

class CustomActivityInterface {

    interface View extends BaseInterface.View {
        void testResult();
    }

    interface Presenter {
        void test();
    }
}
