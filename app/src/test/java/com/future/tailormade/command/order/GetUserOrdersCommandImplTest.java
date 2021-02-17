package com.future.tailormade.command.order;

import com.future.tailormade.BaseTest;
import com.future.tailormade.command.order.impl.GetUserOrdersCommandImpl;
import com.future.tailormade.model.entity.order.Order;
import com.future.tailormade.model.entity.order.OrderDesign;
import com.future.tailormade.payload.request.order.GetUserOrdersRequest;
import com.future.tailormade.payload.response.base.BasePagingResponse;
import com.future.tailormade.payload.response.order.GetOrdersDesignResponse;
import com.future.tailormade.payload.response.order.GetOrdersResponse;
import com.future.tailormade.repository.OrderRepository;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.ArgumentMatchers;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Pageable;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class GetUserOrdersCommandImplTest extends BaseTest {

    @InjectMocks
    private GetUserOrdersCommandImpl command;

    @Mock
    private OrderRepository orderRepository;

    @Captor
    private ArgumentCaptor<String> userIdCaptor;

    @Override
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Override
    public void tearDown() {
        Mockito.verifyNoMoreInteractions(orderRepository);
    }

    @Test
    public void getUserOrders_success() {
        Order expectedOrder = createOrder();
        BasePagingResponse<GetOrdersResponse> expectedResponse = createPaging(
                createGetOrdersResponse()
        );

        Mockito.when(
                orderRepository.findAllByUserIdOrderByCreatedAtDesc(
                        ArgumentMatchers.anyString(),
                        ArgumentMatchers.any(Pageable.class)
                )
        ).thenReturn(Flux.just(expectedOrder));
        Mockito.when(
                orderRepository.countAllByUserId(ArgumentMatchers.anyString())
        ).thenReturn(Mono.just(ITEM_COUNT));

        BasePagingResponse<GetOrdersResponse> actualResponse = command
                .execute(createGetUserOrdersRequest())
                .block();

        Mockito.verify(orderRepository).findAllByUserIdOrderByCreatedAtDesc(
                userIdCaptor.capture(),
                pageableCaptor.capture()
        );
        Assert.assertEquals(userIdCaptor.getValue(), USER_ID);
        Assert.assertEquals(pageableCaptor.getValue(), getPageable());

        Mockito.verify(orderRepository).countAllByUserId(userIdCaptor.capture());
        Assert.assertEquals(userIdCaptor.getValue(), USER_ID);

        Assert.assertEquals(expectedResponse, actualResponse);
    }

    @Test
    public void getUserOrders_notFound() {
        BasePagingResponse<GetOrdersResponse> expectedResponse = createPaging();

        Mockito.when(
                orderRepository.findAllByUserIdOrderByCreatedAtDesc(
                        ArgumentMatchers.anyString(),
                        ArgumentMatchers.any(Pageable.class)
                )
        ).thenReturn(Flux.empty());
        Mockito.when(
                orderRepository.countAllByUserId(ArgumentMatchers.anyString())
        ).thenReturn(Mono.just(ITEM_EMPTY_COUNT));

        BasePagingResponse<GetOrdersResponse> actualResponse = command
                .execute(createGetUserOrdersRequest())
                .block();

        Mockito.verify(orderRepository).findAllByUserIdOrderByCreatedAtDesc(
                userIdCaptor.capture(),
                pageableCaptor.capture()
        );
        Assert.assertEquals(userIdCaptor.getValue(), USER_ID);
        Assert.assertEquals(pageableCaptor.getValue(), getPageable());

        Mockito.verify(orderRepository).countAllByUserId(userIdCaptor.capture());
        Assert.assertEquals(userIdCaptor.getValue(), USER_ID);

        Assert.assertEquals(expectedResponse, actualResponse);
    }

    private GetUserOrdersRequest createGetUserOrdersRequest() {
        return GetUserOrdersRequest.builder()
                .userId(USER_ID)
                .page(PAGE)
                .itemPerPage(ITEM_PER_PAGE)
                .build();
    }

    private GetOrdersResponse createGetOrdersResponse() {
        return GetOrdersResponse.builder()
                .id(ORDER_ID)
                .userId(USER_ID)
                .design(createGetOrdersDesignResponse())
                .build();
    }

    private GetOrdersDesignResponse createGetOrdersDesignResponse() {
        return GetOrdersDesignResponse.builder()
                .id(DESIGN_ID)
                .build();
    }

    private Order createOrder() {
        return Order.builder()
                .id(ORDER_ID)
                .userId(USER_ID)
                .design(createOrderDesign())
                .build();
    }

    private OrderDesign createOrderDesign() {
        return OrderDesign.builder()
                .id(DESIGN_ID)
                .build();
    }
}
