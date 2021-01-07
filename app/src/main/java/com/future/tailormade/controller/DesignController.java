package com.future.tailormade.controller;

import com.blibli.oss.command.CommandExecutor;
import com.blibli.oss.common.response.Response;
import com.blibli.oss.common.response.ResponseHelper;
import com.future.tailormade.command.design.GetDesignByIdCommand;
import com.future.tailormade.command.design.GetDesignsCommand;
import com.future.tailormade.constants.ApiPath;
import com.future.tailormade.payload.request.design.GetDesignsRequest;
import com.future.tailormade.payload.response.design.GetDesignByIdResponse;
import com.future.tailormade.payload.response.design.GetDesignsResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

import java.util.List;

@RestController
public class DesignController {

    @Autowired
    private CommandExecutor commandExecutor;

    @GetMapping(ApiPath.DESIGNS)
    public Mono<Response<List<GetDesignsResponse>>> getDesigns(
            @RequestParam("title") String title,
            @RequestParam("page") int page,
            @RequestParam("itemPerPage") int itemPerPage)
    {
        GetDesignsRequest request = GetDesignsRequest.builder()
                .keyword(title)
                .page(page)
                .itemPerPage(itemPerPage)
                .build();
        return commandExecutor.execute(GetDesignsCommand.class, request)
                .map(getDesignsResponses -> com.future.tailormade.payload.response.base.helper
                        .ResponseHelper.ok(getDesignsResponses, page, itemPerPage))
                .subscribeOn(Schedulers.elastic());
    }

    @GetMapping(ApiPath.DESIGNS_ID)
    public Mono<Response<GetDesignByIdResponse>> getDesignById(@PathVariable("id") String id) {
        return commandExecutor.execute(GetDesignByIdCommand.class, id)
                .map(ResponseHelper::ok)
                .subscribeOn(Schedulers.elastic());
    }
}
