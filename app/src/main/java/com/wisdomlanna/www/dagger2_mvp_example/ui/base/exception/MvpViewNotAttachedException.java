package com.wisdomlanna.www.dagger2_mvp_example.ui.base.exception;

public class MvpViewNotAttachedException extends RuntimeException {
    public MvpViewNotAttachedException() {
        super("Please call Presenter.attachView(MvpBaseView) before" +
                " requesting data to the View");
    }
}

