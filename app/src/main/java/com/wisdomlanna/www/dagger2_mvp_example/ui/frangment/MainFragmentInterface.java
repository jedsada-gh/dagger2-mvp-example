package com.wisdomlanna.www.dagger2_mvp_example.ui.frangment;

import com.wisdomlanna.www.dagger2_mvp_example.ui.base.BaseInterface;

class MainFragmentInterface {

    interface View extends BaseInterface.View {
        void testResult();
    }

    interface Presenter {
        void testFragment();
    }
}
