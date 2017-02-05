package com.wisdomlanna.www.dagger2_mvp_example.main;

interface MainInteractor {

    interface OnValidatePlusListener {
        void setOnError(String message);

        void setOnPlusSuccess(int result);
    }

    void validatePlus(OnValidatePlusListener listener, int... numbers);
}
