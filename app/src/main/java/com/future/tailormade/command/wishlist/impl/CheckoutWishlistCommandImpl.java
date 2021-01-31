package com.future.tailormade.command.wishlist.impl;

import com.future.tailormade.command.wishlist.CheckoutWishlistCommand;
import com.future.tailormade.exceptions.NotFoundException;
import com.future.tailormade.model.entity.order.Order;
import com.future.tailormade.model.entity.order.OrderDesign;
import com.future.tailormade.model.entity.order.OrderMeasurement;
import com.future.tailormade.model.entity.wishlist.Wishlist;
import com.future.tailormade.model.entity.wishlist.WishlistDesign;
import com.future.tailormade.payload.request.wishlist.CheckoutWishlistMeasurementRequest;
import com.future.tailormade.payload.request.wishlist.CheckoutWishlistRequest;
import com.future.tailormade.payload.response.wishlist.CheckoutWishlistResponse;
import com.future.tailormade.repository.OrderRepository;
import com.future.tailormade.repository.WishlistRepository;
import com.future.tailormade.service.SequenceService;
import com.future.tailormade.utils.SequenceGeneratorUtil;
import org.joda.time.DateTime;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class CheckoutWishlistCommandImpl implements CheckoutWishlistCommand {

    @Autowired
    private WishlistRepository wishlistRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private SequenceService sequenceService;

    @Override
    public Mono<CheckoutWishlistResponse> execute(CheckoutWishlistRequest request) {
        return generateOrderId(request.getWishlistId())
                .flatMap(id -> findWishlist(request.getUserId(), request.getWishlistId(), id))
                .map(orderIdWishlistPair -> createOrder(orderIdWishlistPair.getFirst(),
                        orderIdWishlistPair.getSecond(), request))
                .flatMap(orderRepository::save)
                .flatMap(order -> deleteWishlist(request.getWishlistId(), order))
                .map(this::createResponse);
    }

    private Order createOrder(String orderId, Wishlist wishlist, CheckoutWishlistRequest request) {
        Order order = Order.builder().build();
        Long timeStampNow = DateTime.now().getMillis();
        BeanUtils.copyProperties(wishlist, order);
        order.setId(orderId);
        order.setCreatedAt(timeStampNow);
        order.setUpdatedAt(timeStampNow);
        order.setMeasurement(createOrderMeasurement(request.getMeasurements()));
        order.setDesign(createOrderDesign(wishlist.getDesign()));
        return order;
    }

    private OrderDesign createOrderDesign(WishlistDesign wishlistDesign) {
        OrderDesign orderDesign = OrderDesign.builder().build();
        BeanUtils.copyProperties(wishlistDesign, orderDesign);
        return orderDesign;
    }

    private OrderMeasurement createOrderMeasurement(
            CheckoutWishlistMeasurementRequest measurementRequest) {
        OrderMeasurement measurement = OrderMeasurement.builder().build();
        BeanUtils.copyProperties(measurementRequest, measurement);
        return measurement;
    }

    private CheckoutWishlistResponse createResponse(Order order) {
        CheckoutWishlistResponse response = CheckoutWishlistResponse.builder().build();
        BeanUtils.copyProperties(order, response);
        return response;
    }

    private Mono<Pair<String, Wishlist>> findWishlist(String userId, String id, String orderId) {
        return wishlistRepository.findByUserIdAndId(userId, id)
                .switchIfEmpty(Mono.error(NotFoundException::new))
                .map(wishlist -> Pair.of(orderId, wishlist));
    }

    private Mono<String> generateOrderId(String wishlistId) {
        String orderTitle = wishlistId
                .substring(wishlistId.indexOf("_") + 1, wishlistId.lastIndexOf("_"));
        return sequenceService.generateId(orderTitle, SequenceGeneratorUtil.ORDER);
    }

    private Mono<Order> deleteWishlist(String id, Order order) {
        return wishlistRepository.deleteById(id).thenReturn(order);
    }
}
