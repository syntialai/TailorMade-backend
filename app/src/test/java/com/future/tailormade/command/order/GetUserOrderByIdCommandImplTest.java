package com.future.tailormade.command.order;

import com.future.tailormade.BaseTest;
import com.future.tailormade.command.order.impl.GetUserOrderByIdCommandImpl;
import com.future.tailormade.exceptions.NotFoundException;
import com.future.tailormade.model.entity.order.Order;
import com.future.tailormade.payload.request.order.GetUserOrderByIdRequest;
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
import reactor.core.publisher.Mono;

public class GetUserOrderByIdCommandImplTest extends BaseTest {

    @InjectMocks
    private GetUserOrderByIdCommandImpl command;

    @Mock
    private OrderRepository orderRepository;

    @Captor
    private ArgumentCaptor<String> userIdCaptor;

    @Captor
    private ArgumentCaptor<String> orderIdCaptor;

    @Override
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Override
    public void tearDown() {
        Mockito.verifyNoMoreInteractions(orderRepository);
    }

    @Test
    public void getUserOrderById_success() {
        Order expectedResponse = createOrder();

        Mockito.when(orderRepository
                .findByUserIdAndId(ArgumentMatchers.anyString(), ArgumentMatchers.anyString())
        ).thenReturn(Mono.just(expectedResponse));

        Order actualResponse = command.execute(createGetUserOrderByIdRequest()).block();

        Mockito.verify(orderRepository)
                .findByUserIdAndId(userIdCaptor.capture(), orderIdCaptor.capture());
        Assert.assertEquals(userIdCaptor.getValue(), USER_ID);
        Assert.assertEquals(orderIdCaptor.getValue(), ORDER_ID);

        Assert.assertEquals(expectedResponse, actualResponse);
    }

    @Test
    public void getUserOrderById_notFound() {
        Mockito.when(orderRepository
                .findByUserIdAndId(ArgumentMatchers.anyString(), ArgumentMatchers.anyString())
        ).thenReturn(Mono.error(NotFoundException::new));

        try {
            command.execute(createGetUserOrderByIdRequest()).block();
        } catch (NotFoundException exception) {
            Mockito.verify(orderRepository)
                    .findByUserIdAndId(userIdCaptor.capture(), orderIdCaptor.capture());
            Assert.assertEquals(userIdCaptor.getValue(), USER_ID);
            Assert.assertEquals(orderIdCaptor.getValue(), ORDER_ID);
        }
    }

    private GetUserOrderByIdRequest createGetUserOrderByIdRequest() {
        return GetUserOrderByIdRequest.builder()
                .userId(USER_ID)
                .id(ORDER_ID)
                .build();
    }

    private Order createOrder() {
        return Order.builder()
                .id(ORDER_ID)
                .userId(USER_ID)
                .build();
    }
}
