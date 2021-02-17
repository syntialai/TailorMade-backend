package com.future.tailormade;

import com.future.tailormade.model.enums.GenderEnum;
import com.future.tailormade.model.enums.OrderStatusEnum;
import com.future.tailormade.model.enums.RoleEnum;
import com.future.tailormade.payload.response.base.BasePagingResponse;
import org.junit.After;
import org.junit.Before;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.Collections;
import java.util.UUID;

public abstract class BaseTest {

    protected static final Integer PAGE = 0;
    protected static final Integer ITEM_PER_PAGE = 20;
    protected static final Integer TOTAL_ITEM = 1;
    protected static final Integer TOTAL_PAGE = 1;
    protected static final Long ITEM_COUNT = 1L;
    protected static final Long ITEM_EMPTY_COUNT = 0L;

    protected static final String USER_ID = UUID.randomUUID().toString();
    protected static final String USER_NAME = "USER_NAME";
    protected static final String USER_EMAIL = "user@mail.com";
    protected static final RoleEnum USER_ROLE = RoleEnum.ROLE_USER;
    protected static final GenderEnum USER_GENDER = GenderEnum.Female;

    protected static final String TAILOR_ID = UUID.randomUUID().toString();
    protected static final RoleEnum TAILOR_ROLE = RoleEnum.ROLE_TAILOR;

    protected static final String DESIGN_ID = "DESIGN ID";
    protected static final String DESIGN_TITLE = "DESIGN TITLE";

    protected static final String ORDER_ID = "ORDER ID";
    protected static final OrderStatusEnum ORDER_STATUS = OrderStatusEnum.Accepted;

    @Before
    public abstract void setUp();

    @After
    public abstract void tearDown();

    @Captor
    protected ArgumentCaptor<Pageable> pageableCaptor;

    public Pageable getPageable() {
        return PageRequest.of(PAGE, ITEM_PER_PAGE);
    }

    public <T> BasePagingResponse<T> createPaging(T data) {
        return BasePagingResponse.<T>builder()
                .totalItem(TOTAL_ITEM)
                .data(Collections.singletonList(data))
                .build();
    }

    public <T> BasePagingResponse<T> createPaging() {
        return BasePagingResponse.<T>builder()
                .totalItem(ITEM_EMPTY_COUNT.intValue())
                .data(Collections.emptyList())
                .build();
    }
}
