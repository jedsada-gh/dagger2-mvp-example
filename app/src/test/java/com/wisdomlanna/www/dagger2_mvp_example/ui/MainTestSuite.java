package com.wisdomlanna.www.dagger2_mvp_example.ui;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        CalculatorMetricPlusTest.class,
        CalculatorMetricMinusTest.class,
        CalculatorMetricDivideTest.class,
        CalculatorMetricMultiplyTest.class,
        MainPresenterTest.class
})
public class MainTestSuite {
}
