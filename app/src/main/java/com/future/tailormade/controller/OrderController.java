package com.future.tailormade.controller;

import com.blibli.oss.command.CommandExecutor;
import com.blibli.oss.common.response.Response;
import com.blibli.oss.common.response.ResponseHelper;
import com.future.tailormade.command.order.AcceptTailorOrderCommand;
import com.future.tailormade.command.order.RejectTailorOrderCommand;
import com.future.tailormade.constants.ApiPath;
import com.future.tailormade.payload.request.order.AcceptTailorOrderRequest;
import com.future.tailormade.payload.request.order.RejectTailorOrderRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

@RestController
public class OrderController {

    @Autowired
    private CommandExecutor commandExecutor;

    @PutMapping(value = ApiPath.TAILORS_ID_ORDERS_ID_ACCEPT,
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public Mono<Response<Object>> acceptOrderByTailor(
            @PathVariable("tailorId") String tailorId,
            @PathVariable("id") String id
    ) {
        AcceptTailorOrderRequest request = AcceptTailorOrderRequest.builder()
                .tailorId(tailorId)
                .id(id)
                .build();
        return commandExecutor.execute(AcceptTailorOrderCommand.class, request)
                .thenReturn(ResponseHelper.ok())
                .subscribeOn(Schedulers.elastic());
    }

    @PutMapping(value = ApiPath.TAILORS_ID_ORDERS_ID_REJECT,
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public Mono<Response<Object>> rejectOrderByTailor(
            @PathVariable("tailorId") String tailorId,
            @PathVariable("id") String id
    ) {
        RejectTailorOrderRequest request = RejectTailorOrderRequest.builder()
                .tailorId(tailorId)
                .id(id)
                .build();
        return commandExecutor.execute(RejectTailorOrderCommand.class, request)
                .thenReturn(ResponseHelper.ok())
                .subscribeOn(Schedulers.elastic());
    }
}
