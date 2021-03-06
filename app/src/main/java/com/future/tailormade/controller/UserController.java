package com.future.tailormade.controller;

import com.blibli.oss.command.CommandExecutor;
import com.blibli.oss.common.response.Response;
import com.blibli.oss.common.response.ResponseHelper;
import com.future.tailormade.command.user.EditUserAdditionalInfoCommand;
import com.future.tailormade.command.user.EditUserBasicInfoCommand;
import com.future.tailormade.command.user.GetUserByIdCommand;
import com.future.tailormade.constants.ApiPath;
import com.future.tailormade.payload.request.user.EditUserAdditionalInfoRequest;
import com.future.tailormade.payload.request.user.EditUserBasicInfoRequest;
import com.future.tailormade.payload.response.user.EditUserAdditionalInfoResponse;
import com.future.tailormade.payload.response.user.EditUserBasicInfoResponse;
import com.future.tailormade.payload.response.user.GetUserByIdResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

@RestController
public class UserController {

    @Autowired
    private CommandExecutor commandExecutor;

    @GetMapping(ApiPath.USERS_ID)
    public Mono<Response<GetUserByIdResponse>> getUserById(@PathVariable("id") String id) {
        return commandExecutor.execute(GetUserByIdCommand.class, id)
                .map(ResponseHelper::ok)
                .subscribeOn(Schedulers.elastic());
    }

    @PutMapping(value = ApiPath.USERS_ID_UPDATE_BASIC_INFO,
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public Mono<Response<EditUserBasicInfoResponse>> updateUserBasicInfo(
            @PathVariable("id") String id,
            @RequestBody EditUserBasicInfoRequest request)
    {
        request.setId(id);
        return commandExecutor.execute(EditUserBasicInfoCommand.class, request)
                .map(ResponseHelper::ok)
                .subscribeOn(Schedulers.elastic());
    }

    @PutMapping(value = ApiPath.USERS_ID_UPDATE_MORE_INFO,
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public Mono<Response<EditUserAdditionalInfoResponse>> updateUserAdditionalInfo(
            @PathVariable("id") String id,
            @RequestBody EditUserAdditionalInfoRequest request)
    {
        request.setId(id);
        return commandExecutor.execute(EditUserAdditionalInfoCommand.class, request)
                .map(ResponseHelper::ok)
                .subscribeOn(Schedulers.elastic());
    }
}
