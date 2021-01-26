package com.future.tailormade.controller;

import com.blibli.oss.command.CommandExecutor;
import com.blibli.oss.common.response.Response;
import com.blibli.oss.common.response.ResponseHelper;
import com.future.tailormade.command.tailor.AddTailorDesignCommand;
import com.future.tailormade.command.tailor.DeleteTailorDesignByIdCommand;
import com.future.tailormade.command.tailor.EditTailorDesignCommand;
import com.future.tailormade.command.tailor.GetDashboardTailorsCommand;
import com.future.tailormade.command.tailor.GetTailorByIdCommand;
import com.future.tailormade.command.tailor.GetTailorDesignsCommand;
import com.future.tailormade.command.tailor.GetTailorsCommand;
import com.future.tailormade.constants.ApiPath;
import com.future.tailormade.payload.request.tailor.AddTailorDesignRequest;
import com.future.tailormade.payload.request.tailor.DeleteTailorDesignRequest;
import com.future.tailormade.payload.request.tailor.EditTailorDesignRequest;
import com.future.tailormade.payload.request.tailor.GetDashboardTailorsRequest;
import com.future.tailormade.payload.request.tailor.GetTailorDesignsRequest;
import com.future.tailormade.payload.request.tailor.GetTailorsRequest;
import com.future.tailormade.payload.response.tailor.AddOrEditTailorDesignResponse;
import com.future.tailormade.payload.response.tailor.GetDashboardTailorsResponse;
import com.future.tailormade.payload.response.tailor.GetTailorDesignsResponse;
import com.future.tailormade.payload.response.tailor.GetTailorsResponse;
import com.future.tailormade.payload.response.user.GetUserByIdResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
    @PreAuthorize("hasRole('USER')")
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

    @GetMapping(ApiPath.SEARCH_TAILOR)
    @PreAuthorize("hasRole('USER')")
    public Mono<Response<List<GetTailorsResponse>>> getTailors(
            @RequestParam("name") String name,
            @RequestParam("page") int page,
            @RequestParam("itemPerPage") int itemPerPage
    ) {
        GetTailorsRequest request = GetTailorsRequest.builder()
                .keyword(name)
                .page(page)
                .itemPerPage(itemPerPage)
                .build();
        return commandExecutor.execute(GetTailorsCommand.class, request)
                .map(tailors -> com.future.tailormade.payload.response.base.helper
                        .ResponseHelper.ok(
                                tailors.getData(),
                                page,
                                itemPerPage,
                                tailors.getTotalItem()
                        )
                )
                .subscribeOn(Schedulers.elastic());
    }

    @GetMapping(ApiPath.TAILORS_ID)
    public Mono<Response<GetUserByIdResponse>> getTailorById(
            @PathVariable("tailorId") String tailorId
    ) {
        return commandExecutor.execute(GetTailorByIdCommand.class, tailorId)
                .map(ResponseHelper::ok)
                .subscribeOn(Schedulers.elastic());
    }

    @GetMapping(ApiPath.TAILORS_ID_DESIGNS)
    public Mono<Response<List<GetTailorDesignsResponse>>> getTailorDesigns(
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

    @PostMapping(value = ApiPath.TAILORS_ID_DESIGNS,
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasRole('TAILOR')")
    public Mono<Response<AddOrEditTailorDesignResponse>> addTailorDesign(
            @PathVariable("tailorId") String tailorId,
            @RequestBody AddTailorDesignRequest request
    ) {
        request.setTailorId(tailorId);
        return commandExecutor.execute(AddTailorDesignCommand.class, request)
                .map(ResponseHelper::ok)
                .subscribeOn(Schedulers.elastic());
    }

    @PutMapping(value = ApiPath.TAILORS_ID_DESIGNS_ID,
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasRole('TAILOR')")
    public Mono<Response<AddOrEditTailorDesignResponse>> editTailorDesignById(
            @PathVariable("tailorId") String tailorId,
            @PathVariable("id") String id,
            @RequestBody EditTailorDesignRequest request
    ) {
        request.setTailorId(tailorId);
        request.setId(id);
        return commandExecutor.execute(EditTailorDesignCommand.class, request)
                .map(ResponseHelper::ok)
                .subscribeOn(Schedulers.elastic());
    }

    @DeleteMapping(ApiPath.TAILORS_ID_DESIGNS_ID)
    @PreAuthorize("hasRole('TAILOR')")
    public Mono<Response<Object>> deleteTailorDesignById(
            @PathVariable("tailorId") String tailorId,
            @PathVariable("id") String id
    ) {
        DeleteTailorDesignRequest request = DeleteTailorDesignRequest.builder()
                .id(id)
                .tailorId(tailorId)
                .build();
        return commandExecutor.execute(DeleteTailorDesignByIdCommand.class, request)
                .thenReturn(ResponseHelper.ok())
                .subscribeOn(Schedulers.elastic());
    }
}
