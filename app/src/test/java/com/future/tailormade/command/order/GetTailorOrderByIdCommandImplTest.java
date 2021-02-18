package com.future.tailormade.command.order;

import com.future.tailormade.BaseTest;
import com.future.tailormade.command.order.impl.GetTailorOrderByIdCommandImpl;
import com.future.tailormade.exceptions.NotFoundException;
import com.future.tailormade.model.entity.order.Order;
import com.future.tailormade.payload.request.order.GetTailorOrderByIdRequest;
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

public class GetTailorOrderByIdCommandImplTest extends BaseTest {

    @InjectMocks
    private GetTailorOrderByIdCommandImpl command;

    @Mock
    private OrderRepository orderRepository;

    @Captor
    private ArgumentCaptor<String> tailorIdCaptor;

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
    public void getTailorOrderById_success() {
        Order expectedResponse = createOrder();

        Mockito.when(orderRepository
                .findByTailorIdAndId(ArgumentMatchers.anyString(), ArgumentMatchers.anyString())
        ).thenReturn(Mono.just(expectedResponse));

        Order actualResponse = command.execute(createGetTailorOrderByIdRequest()).block();

        Mockito.verify(orderRepository)
                .findByTailorIdAndId(tailorIdCaptor.capture(), orderIdCaptor.capture());
        Assert.assertEquals(tailorIdCaptor.getValue(), TAILOR_ID);
        Assert.assertEquals(orderIdCaptor.getValue(), ORDER_ID);

        Assert.assertEquals(expectedResponse, actualResponse);
    }

    @Test
    public void getTailorOrderById_notFound() {
        Mockito.when(orderRepository
                .findByTailorIdAndId(ArgumentMatchers.anyString(), ArgumentMatchers.anyString())
        ).thenReturn(Mono.empty());

        try {
            command.execute(createGetTailorOrderByIdRequest()).block();
        } catch (NotFoundException exception) {
            Mockito.verify(orderRepository)
                    .findByTailorIdAndId(tailorIdCaptor.capture(), orderIdCaptor.capture());
            Assert.assertEquals(tailorIdCaptor.getValue(), TAILOR_ID);
            Assert.assertEquals(orderIdCaptor.getValue(), ORDER_ID);
        }
    }

    private GetTailorOrderByIdRequest createGetTailorOrderByIdRequest() {
        return GetTailorOrderByIdRequest.builder()
                .tailorId(TAILOR_ID)
                .id(ORDER_ID)
                .build();
    }

    private Order createOrder() {
        return Order.builder()
                .id(ORDER_ID)
                .tailorId(TAILOR_ID)
                .build();
    }
}
