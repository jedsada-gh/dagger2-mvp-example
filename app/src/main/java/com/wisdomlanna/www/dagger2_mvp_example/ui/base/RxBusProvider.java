package com.wisdomlanna.www.dagger2_mvp_example.ui.base;

import com.hwangjr.rxbus.Bus;

public class RxBusProvider {

    private static Bus sBus;

    public static synchronized Bus get() {
        if (sBus == null) {
            sBus = new Bus();
        }
        return sBus;
    }
}
