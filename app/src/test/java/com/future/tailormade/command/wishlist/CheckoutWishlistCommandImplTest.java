package com.future.tailormade.command.wishlist;

import com.future.tailormade.BaseTest;
import com.future.tailormade.command.wishlist.impl.CheckoutWishlistCommandImpl;
import com.future.tailormade.exceptions.NotFoundException;
import com.future.tailormade.model.entity.order.Order;
import com.future.tailormade.model.entity.order.OrderDesign;
import com.future.tailormade.model.entity.order.OrderMeasurement;
import com.future.tailormade.model.entity.wishlist.Wishlist;
import com.future.tailormade.model.entity.wishlist.WishlistDesign;
import com.future.tailormade.model.enums.OrderStatusEnum;
import com.future.tailormade.payload.request.wishlist.CheckoutWishlistMeasurementRequest;
import com.future.tailormade.payload.request.wishlist.CheckoutWishlistRequest;
import com.future.tailormade.payload.response.wishlist.CheckoutWishlistResponse;
import com.future.tailormade.repository.OrderRepository;
import com.future.tailormade.repository.WishlistRepository;
import com.future.tailormade.service.SequenceService;
import com.future.tailormade.utils.SequenceGeneratorUtil;
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

public class CheckoutWishlistCommandImplTest extends BaseTest {

    private static final String WISHLIST_GENERATED_ID = "WSLT_USER_DESI_0001";
    private static final String DESIGN_IMAGE = "Design image";
    private static final String TITLE_GENERATED = "USER_DESI";
    private static final Double DESIGN_PRICE = 50000.0;
    private static final Double DESIGN_DISCOUNT = 0.0;
    private static final String DESIGN_SIZE = "S";
    private static final String DESIGN_COLOR = "Yellow";

    private static final Float CHEST = 10f;
    private static final Float HIPS = 11f;
    private static final Float WAIST = 12f;
    private static final Float NECK_TO_WAIST = 13f;
    private static final Float INSEAM = 14f;

    private static final OrderStatusEnum ORDER_STATUS = OrderStatusEnum.Incoming;

    @InjectMocks
    private CheckoutWishlistCommandImpl command;

    @Mock
    private WishlistRepository wishlistRepository;

    @Mock
    private OrderRepository orderRepository;

    @Mock
    private SequenceService sequenceService;

    @Captor
    private ArgumentCaptor<String> userIdCaptor;

    @Captor
    private ArgumentCaptor<String> orderTypeCaptor;

    @Captor
    private ArgumentCaptor<String> wishlistIdCaptor;

    @Captor
    private ArgumentCaptor<String> titleIdCaptor;

    @Captor
    private ArgumentCaptor<Order> orderCaptor;

    @Override
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Override
    public void tearDown() {
        Mockito.verifyNoMoreInteractions(wishlistRepository);
    }

    @Test
    public void checkoutWishlistCommand_success() {
        Wishlist wishlist = createWishlist();
        Order order = createOrder();
        CheckoutWishlistResponse expectedResponse = createCheckoutWishlistResponse();

        Mockito.when(sequenceService.generateId(
                ArgumentMatchers.anyString(), ArgumentMatchers.anyString())
        ).thenReturn(Mono.just(ORDER_ID));
        Mockito.when(wishlistRepository
                .findByUserIdAndId(ArgumentMatchers.anyString(), ArgumentMatchers.anyString())
        ).thenReturn(Mono.just(wishlist));
        Mockito.when(orderRepository.save(
                ArgumentMatchers.any(Order.class))).thenReturn(Mono.just(order));
        Mockito.when(wishlistRepository.deleteById(
                ArgumentMatchers.anyString())).thenReturn(Mono.empty());

        CheckoutWishlistResponse actualResponse =
                command.execute(createCheckoutWishlistRequest()).block();

        Mockito.verify(sequenceService)
                .generateId(titleIdCaptor.capture(), orderTypeCaptor.capture());
        Assert.assertEquals(titleIdCaptor.getValue(), TITLE_GENERATED);
        Assert.assertEquals(orderTypeCaptor.getValue(), SequenceGeneratorUtil.ORDER);

        Mockito.verify(wishlistRepository)
                .findByUserIdAndId(userIdCaptor.capture(), wishlistIdCaptor.capture());
        Assert.assertEquals(userIdCaptor.getValue(), USER_ID);
        Assert.assertEquals(wishlistIdCaptor.getValue(), WISHLIST_GENERATED_ID);

        Mockito.verify(orderRepository).save(orderCaptor.capture());
        Order orderValue = orderCaptor.getValue();
        assertOrder(order, orderValue);

        Mockito.verify(wishlistRepository).deleteById(wishlistIdCaptor.capture());
        Assert.assertEquals(wishlistIdCaptor.getValue(), WISHLIST_GENERATED_ID);

        Assert.assertEquals(expectedResponse, actualResponse);
    }

    @Test
    public void checkoutWishlistCommand_notFound() {
        Mockito.when(sequenceService.generateId(
                ArgumentMatchers.anyString(), ArgumentMatchers.anyString())
        ).thenReturn(Mono.just(ORDER_ID));
        Mockito.when(wishlistRepository
                .findByUserIdAndId(ArgumentMatchers.anyString(), ArgumentMatchers.anyString())
        ).thenReturn(Mono.empty());

        try {
            command.execute(createCheckoutWishlistRequest()).block();
        } catch (NotFoundException exception) {
            Mockito.verify(sequenceService)
                    .generateId(titleIdCaptor.capture(), orderTypeCaptor.capture());
            Assert.assertEquals(titleIdCaptor.getValue(), TITLE_GENERATED);
            Assert.assertEquals(orderTypeCaptor.getValue(), SequenceGeneratorUtil.ORDER);

            Mockito.verify(wishlistRepository)
                    .findByUserIdAndId(userIdCaptor.capture(), wishlistIdCaptor.capture());
            Assert.assertEquals(userIdCaptor.getValue(), USER_ID);
            Assert.assertEquals(wishlistIdCaptor.getValue(), WISHLIST_GENERATED_ID);
        }
    }

    private void assertOrder(Order expectedOrder, Order actualOrder) {
        Assert.assertEquals(expectedOrder.getDesign(), actualOrder.getDesign());
        Assert.assertEquals(expectedOrder.getQuantity(), actualOrder.getQuantity());
        Assert.assertEquals(expectedOrder.getId(), actualOrder.getId());
        Assert.assertEquals(expectedOrder.getMeasurement(), actualOrder.getMeasurement());
        Assert.assertEquals(expectedOrder.getStatus(), actualOrder.getStatus());
        Assert.assertEquals(expectedOrder.getTailorId(), actualOrder.getTailorId());
        Assert.assertEquals(expectedOrder.getTailorName(), actualOrder.getTailorName());
        Assert.assertEquals(expectedOrder.getUserId(), actualOrder.getUserId());
        Assert.assertEquals(expectedOrder.getUserName(), actualOrder.getUserName());
        Assert.assertEquals(expectedOrder.getTotalDiscount(), actualOrder.getTotalDiscount());
        Assert.assertEquals(expectedOrder.getTotalPrice(), actualOrder.getTotalPrice());
    }

    private CheckoutWishlistRequest createCheckoutWishlistRequest() {
        return CheckoutWishlistRequest.builder()
                .userId(USER_ID)
                .wishlistId(WISHLIST_GENERATED_ID)
                .measurements(createCheckoutWishlistMeasurementRequest())
                .build();
    }

    private CheckoutWishlistMeasurementRequest createCheckoutWishlistMeasurementRequest() {
        return CheckoutWishlistMeasurementRequest.builder()
                .chest(CHEST)
                .hips(HIPS)
                .inseam(INSEAM)
                .neckToWaist(NECK_TO_WAIST)
                .waist(WAIST)
                .build();
    }

    private CheckoutWishlistResponse createCheckoutWishlistResponse() {
        return CheckoutWishlistResponse.builder()
                .id(ORDER_ID)
                .userId(USER_ID)
                .userId(USER_ID)
                .tailorId(TAILOR_ID)
                .quantity(WISHLIST_QUANTITY)
                .totalDiscount(DESIGN_DISCOUNT * WISHLIST_QUANTITY)
                .totalPrice(DESIGN_PRICE * WISHLIST_QUANTITY)
                .design(createOrderDesign())
                .build();
    }

    private Order createOrder() {
        return Order.builder()
                .id(ORDER_ID)
                .userId(USER_ID)
                .userName(USER_NAME)
                .tailorId(TAILOR_ID)
                .tailorName(TAILOR_NAME)
                .quantity(WISHLIST_QUANTITY)
                .totalDiscount(DESIGN_DISCOUNT * WISHLIST_QUANTITY)
                .totalPrice(DESIGN_PRICE * WISHLIST_QUANTITY)
                .design(createOrderDesign())
                .measurement(createOrderMeasurement())
                .status(ORDER_STATUS)
                .build();
    }

    private OrderDesign createOrderDesign() {
        return OrderDesign.builder()
                .id(DESIGN_ID)
                .title(DESIGN_TITLE)
                .image(DESIGN_IMAGE)
                .price(DESIGN_PRICE)
                .discount(DESIGN_DISCOUNT)
                .size(DESIGN_SIZE)
                .color(DESIGN_COLOR)
                .tailorId(TAILOR_ID)
                .tailorName(TAILOR_NAME)
                .build();
    }

    private OrderMeasurement createOrderMeasurement() {
        return OrderMeasurement.builder()
                .chest(CHEST)
                .hips(HIPS)
                .inseam(INSEAM)
                .neckToWaist(NECK_TO_WAIST)
                .waist(WAIST)
                .build();
    }

    private Wishlist createWishlist() {
        return Wishlist.builder()
                .id(WISHLIST_GENERATED_ID)
                .userId(USER_ID)
                .userName(USER_NAME)
                .tailorId(TAILOR_ID)
                .tailorName(TAILOR_NAME)
                .quantity(WISHLIST_QUANTITY)
                .design(createWishlistDesign())
                .build();
    }

    private WishlistDesign createWishlistDesign() {
        return WishlistDesign.builder()
                .tailorId(TAILOR_ID)
                .tailorName(TAILOR_NAME)
                .id(DESIGN_ID)
                .title(DESIGN_TITLE)
                .image(DESIGN_IMAGE)
                .price(DESIGN_PRICE)
                .discount(DESIGN_DISCOUNT)
                .size(DESIGN_SIZE)
                .color(DESIGN_COLOR)
                .build();
    }
}
