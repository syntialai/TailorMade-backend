package com.future.tailormade;

import org.junit.After;
import org.junit.Before;

public abstract class BaseTest {

    @Before
    public abstract void setUp();

    @After
    public abstract void tearDown();
}
