package com.future.tailormade.controller;

import com.blibli.oss.command.CommandExecutor;
import com.blibli.oss.common.response.Response;
import com.blibli.oss.common.response.ResponseHelper;
import com.future.tailormade.command.tailor.GetDashboardTailorsCommand;
import com.future.tailormade.command.tailor.GetTailorByIdCommand;
import com.future.tailormade.command.tailor.GetTailorDesignsCommand;
import com.future.tailormade.constants.ApiPath;
import com.future.tailormade.payload.request.tailor.GetDashboardTailorsRequest;
import com.future.tailormade.payload.request.tailor.GetTailorDesignsRequest;
import com.future.tailormade.payload.response.tailor.GetDashboardTailorsResponse;
import com.future.tailormade.payload.response.tailor.GetTailorDesignsResponse;
import com.future.tailormade.payload.response.user.GetUserByIdResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

import java.util.List;

@RestController
public class TailorController {

    @Autowired
    private CommandExecutor commandExecutor;

    @GetMapping(ApiPath.DASHBOARD_TAILORS)
    public Mono<Response<List<GetDashboardTailorsResponse>>> getDashboardTailors(
            @RequestParam("page") int page,
            @RequestParam("itemPerPage") int itemPerPage
    ) {
        GetDashboardTailorsRequest request = GetDashboardTailorsRequest.builder()
                .page(page)
                .itemPerPage(itemPerPage)
                .build();
        return commandExecutor.execute(GetDashboardTailorsCommand.class, request)
                .map(dashboardTailors -> com.future.tailormade.payload.response.base.helper
                        .ResponseHelper.ok(
                                dashboardTailors.getData(),
                                page,
                                itemPerPage,
                                dashboardTailors.getTotalItem()
                        )
                )
                .subscribeOn(Schedulers.elastic());
    }

    @GetMapping(ApiPath.TAILORS_ID)
    private Mono<Response<GetUserByIdResponse>> getTailorById(
            @PathVariable("tailorId") String tailorId
    ) {
        return commandExecutor.execute(GetTailorByIdCommand.class, tailorId)
                .map(ResponseHelper::ok)
                .subscribeOn(Schedulers.elastic());
    }

    @GetMapping(ApiPath.TAILORS_ID_DESIGNS)
    private Mono<Response<List<GetTailorDesignsResponse>>> getTailorDesigns(
            @PathVariable("tailorId") String tailorId,
            @RequestParam("page") int page,
            @RequestParam("itemPerPage") int itemPerPage
    ) {
        GetTailorDesignsRequest request = GetTailorDesignsRequest.builder()
                .tailorId(tailorId)
                .page(page)
                .itemPerPage(itemPerPage)
                .build();
        return commandExecutor.execute(GetTailorDesignsCommand.class, request)
                .map(tailorDesigns -> com.future.tailormade.payload.response.base.helper
                        .ResponseHelper.ok(
                                tailorDesigns.getData(),
                                page,
                                itemPerPage,
                                tailorDesigns.getTotalItem()
                        ))
                .subscribeOn(Schedulers.elastic());
    }
}
