package com.future.tailormade.command.order;

import com.future.tailormade.BaseTest;
import com.future.tailormade.command.order.impl.RejectTailorOrderCommandImpl;
import com.future.tailormade.exceptions.NotFoundException;
import com.future.tailormade.model.entity.order.Order;
import com.future.tailormade.model.enums.OrderStatusEnum;
import com.future.tailormade.payload.request.order.RejectTailorOrderRequest;
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

public class RejectTailorOrderCommandImplTest extends BaseTest {

    @InjectMocks
    private RejectTailorOrderCommandImpl command;

    @Mock
    private OrderRepository orderRepository;

    @Captor
    private ArgumentCaptor<String> tailorIdCaptor;

    @Captor
    private ArgumentCaptor<String> orderIdCaptor;

    @Captor
    private ArgumentCaptor<OrderStatusEnum> orderStatusCaptor;

    @Captor
    private ArgumentCaptor<Order> orderCaptor;

    @Override
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Override
    public void tearDown() {
        Mockito.verifyNoMoreInteractions(orderRepository);
    }

    @Test
    public void acceptTailorOrder_success() {
        Order expectedIncomingOrder = createOrder(OrderStatusEnum.Incoming);
        Order expectedOrder = createOrder(OrderStatusEnum.Rejected);

        Mockito.when(orderRepository.findByTailorIdAndIdAndStatus(
                ArgumentMatchers.anyString(),
                ArgumentMatchers.anyString(),
                ArgumentMatchers.any(OrderStatusEnum.class)
        )).thenReturn(Mono.just(expectedIncomingOrder));
        Mockito.when(
                orderRepository.save(ArgumentMatchers.any(Order.class))
        ).thenReturn(Mono.just(expectedOrder));

        command.execute(createRejectTailorOrderRequest()).block();

        Mockito.verify(orderRepository).findByTailorIdAndIdAndStatus(
                tailorIdCaptor.capture(),
                orderIdCaptor.capture(),
                orderStatusCaptor.capture()
        );
        Assert.assertEquals(tailorIdCaptor.getValue(), TAILOR_ID);
        Assert.assertEquals(orderIdCaptor.getValue(), ORDER_ID);
        Assert.assertEquals(orderStatusCaptor.getValue(), OrderStatusEnum.Incoming);

        Mockito.verify(orderRepository).save(orderCaptor.capture());
        Assert.assertEquals(orderCaptor.getValue(), expectedOrder);
    }

    @Test
    public void acceptTailorOrder_notFound() {
        Mockito.when(orderRepository.findByTailorIdAndIdAndStatus(
                ArgumentMatchers.anyString(),
                ArgumentMatchers.anyString(),
                ArgumentMatchers.any(OrderStatusEnum.class)
        )).thenReturn(Mono.error(NotFoundException::new));

        try {
            command.execute(createRejectTailorOrderRequest()).block();
        } catch (NotFoundException exception) {
            Mockito.verify(orderRepository).findByTailorIdAndIdAndStatus(
                    tailorIdCaptor.capture(),
                    orderIdCaptor.capture(),
                    orderStatusCaptor.capture()
            );
            Assert.assertEquals(tailorIdCaptor.getValue(), TAILOR_ID);
            Assert.assertEquals(orderIdCaptor.getValue(), ORDER_ID);
            Assert.assertEquals(orderStatusCaptor.getValue(), OrderStatusEnum.Incoming);
        }
    }

    private RejectTailorOrderRequest createRejectTailorOrderRequest() {
        return RejectTailorOrderRequest.builder()
                .id(ORDER_ID)
                .tailorId(TAILOR_ID)
                .build();
    }

    private Order createOrder(OrderStatusEnum status) {
        return Order.builder()
                .id(ORDER_ID)
                .tailorId(TAILOR_ID)
                .status(status)
                .build();
    }
}
