package com.wisdomlanna.www.dagger2_mvp_example.ui.frangment;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.powermock.modules.junit4.PowerMockRunner;

import static org.mockito.Mockito.timeout;
import static org.mockito.Mockito.verify;

@RunWith(PowerMockRunner.class)
public class MainFragmentPresenterTest {

    @Mock
    private MainFragmentInterface.View mockView;

    private MainFragmentPresenter presenter;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        presenter = new MainFragmentPresenter();
        presenter.attachView(mockView);
    }

    @Test
    public void onViewCreate() {
        presenter.onViewCreate();
    }

    @Test
    public void onViewDestroy() {
        presenter.onViewDestroy();
    }

    @Test
    public void onViewStart() {
        presenter.onViewStart();
    }

    @Test
    public void onViewStop() {
        presenter.onViewStop();
    }

    @Test
    public void testFragment() {
        presenter.testFragment();
        verify(mockView, timeout(1)).testResult();
    }
}