package com.wisdomlanna.www.dagger2_mvp_example.ui.base.exception;

public class MvpNotSetLayoutException extends RuntimeException {
    public MvpNotSetLayoutException() {
        super("getLayoutView() not return 0");
    }
}

