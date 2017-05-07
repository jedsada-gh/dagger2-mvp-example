package com.wisdomlanna.www.dagger2_mvp_example.template.frangment;

import com.wisdomlanna.www.dagger2_mvp_example.ui.base.BaseInterface;

class CustomFragmentInterface {

    interface View extends BaseInterface.View {
        void testResult();
    }

    interface Presenter {
        void testFragment();
    }
}
