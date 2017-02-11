package com.wisdomlanna.www.dagger2_mvp_example;

import android.widget.TextView;

import com.wisdomlanna.www.dagger2_mvp_example.main.MainActivity;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import static junit.framework.Assert.assertEquals;

@RunWith(RobolectricTestRunner.class)
@Config(constants = BuildConfig.class, sdk = 21)
public class ExampleUnitTest {

    private MainActivity mainActivity;

    @Before
    public void setUp() throws Exception {
        mainActivity = Robolectric.setupActivity(MainActivity.class);
    }

    @Test
    public void shouldInjectMockStringFactory() throws Exception {
        TextView result = (TextView) mainActivity.findViewById(R.id.tvResult);
        assertEquals("result : 10", result.getText());
    }
}