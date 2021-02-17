package com.future.tailormade;

import com.future.tailormade.model.enums.GenderEnum;
import com.future.tailormade.model.enums.RoleEnum;
import org.junit.After;
import org.junit.Before;

import java.util.UUID;

public abstract class BaseTest {

    protected static final String USER_ID = UUID.randomUUID().toString();
    protected static final String USER_NAME = "USER_NAME";
    protected static final String USER_EMAIL = "user@mail.com";
    protected static final RoleEnum USER_ROLE = RoleEnum.ROLE_USER;
    protected static final GenderEnum USER_GENDER = GenderEnum.Female;

    protected static final RoleEnum TAILOR_ROLE = RoleEnum.ROLE_TAILOR;

    @Before
    public abstract void setUp();

    @After
    public abstract void tearDown();
}
