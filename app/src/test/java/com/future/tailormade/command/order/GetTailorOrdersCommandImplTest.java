package com.future.tailormade.command.order;

import com.future.tailormade.BaseTest;
import com.future.tailormade.command.order.impl.GetTailorOrdersCommandImpl;
import com.future.tailormade.model.entity.order.Order;
import com.future.tailormade.model.entity.order.OrderDesign;
import com.future.tailormade.model.enums.OrderStatusEnum;
import com.future.tailormade.payload.request.order.GetTailorOrdersRequest;
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

public class GetTailorOrdersCommandImplTest extends BaseTest {

    @InjectMocks
    private GetTailorOrdersCommandImpl command;

    @Mock
    private OrderRepository orderRepository;

    @Captor
    private ArgumentCaptor<String> tailorIdCaptor;

    @Captor
    private ArgumentCaptor<OrderStatusEnum> orderStatusCaptor;

    @Override
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Override
    public void tearDown() {
        Mockito.verifyNoMoreInteractions(orderRepository);
    }

    @Test
    public void getTailorOrders_success() {
        Order expectedOrder = createOrder();
        BasePagingResponse<GetOrdersResponse> expectedResponse = createPaging(
                createGetOrdersResponse()
        );

        Mockito.when(
                orderRepository.findAllByTailorIdAndStatus(
                        ArgumentMatchers.anyString(),
                        ArgumentMatchers.any(OrderStatusEnum.class),
                        ArgumentMatchers.any(Pageable.class)
                )
        ).thenReturn(Flux.just(expectedOrder));
        Mockito.when(
                orderRepository.countAllByTailorIdAndStatus(
                        ArgumentMatchers.anyString(), 
                        ArgumentMatchers.any(OrderStatusEnum.class)
                )
        ).thenReturn(Mono.just(ITEM_COUNT));

        BasePagingResponse<GetOrdersResponse> actualResponse = command
                .execute(createGetTailorOrdersRequest())
                .block();

        Mockito.verify(orderRepository).findAllByTailorIdAndStatus(
                tailorIdCaptor.capture(),
                orderStatusCaptor.capture(),
                pageableCaptor.capture()
        );
        Assert.assertEquals(tailorIdCaptor.getValue(), TAILOR_ID);
        Assert.assertEquals(orderStatusCaptor.getValue(), ORDER_STATUS);
        Assert.assertEquals(pageableCaptor.getValue(), getPageable());

        Mockito.verify(orderRepository)
                .countAllByTailorIdAndStatus(tailorIdCaptor.capture(), orderStatusCaptor.capture());
        Assert.assertEquals(tailorIdCaptor.getValue(), TAILOR_ID);
        Assert.assertEquals(orderStatusCaptor.getValue(), ORDER_STATUS);

        Assert.assertEquals(expectedResponse, actualResponse);
    }

    @Test
    public void getTailorOrders_notFound() {
        BasePagingResponse<GetOrdersResponse> expectedResponse = createPaging();

        Mockito.when(
                orderRepository.findAllByTailorIdAndStatus(
                        ArgumentMatchers.anyString(),
                        ArgumentMatchers.any(OrderStatusEnum.class),
                        ArgumentMatchers.any(Pageable.class)
                )
        ).thenReturn(Flux.empty());
        Mockito.when(
                orderRepository.countAllByTailorIdAndStatus(
                        ArgumentMatchers.anyString(),
                        ArgumentMatchers.any(OrderStatusEnum.class)
                )
        ).thenReturn(Mono.just(ITEM_EMPTY_COUNT));

        BasePagingResponse<GetOrdersResponse> actualResponse = command
                .execute(createGetTailorOrdersRequest())
                .block();

        Mockito.verify(orderRepository).findAllByTailorIdAndStatus(
                tailorIdCaptor.capture(),
                orderStatusCaptor.capture(),
                pageableCaptor.capture()
        );
        Assert.assertEquals(tailorIdCaptor.getValue(), TAILOR_ID);
        Assert.assertEquals(orderStatusCaptor.getValue(), ORDER_STATUS);
        Assert.assertEquals(pageableCaptor.getValue(), getPageable());

        Mockito.verify(orderRepository)
                .countAllByTailorIdAndStatus(tailorIdCaptor.capture(), orderStatusCaptor.capture());
        Assert.assertEquals(tailorIdCaptor.getValue(), TAILOR_ID);
        Assert.assertEquals(orderStatusCaptor.getValue(), ORDER_STATUS);

        Assert.assertEquals(expectedResponse, actualResponse);
    }

    private GetTailorOrdersRequest createGetTailorOrdersRequest() {
        return GetTailorOrdersRequest.builder()
                .tailorId(TAILOR_ID)
                .status(ORDER_STATUS)
                .page(PAGE)
                .itemPerPage(ITEM_PER_PAGE)
                .build();
    }

    private GetOrdersResponse createGetOrdersResponse() {
        return GetOrdersResponse.builder()
                .id(ORDER_ID)
                .tailorId(TAILOR_ID)
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
                .tailorId(TAILOR_ID)
                .design(createOrderDesign())
                .build();
    }

    private OrderDesign createOrderDesign() {
        return OrderDesign.builder()
                .id(DESIGN_ID)
                .tailorId(TAILOR_ID)
                .build();
    }
}
