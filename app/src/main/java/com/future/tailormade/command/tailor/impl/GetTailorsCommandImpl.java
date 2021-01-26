package com.future.tailormade.command.tailor.impl;

import com.future.tailormade.command.tailor.GetTailorsCommand;
import com.future.tailormade.model.entity.user.User;
import com.future.tailormade.model.enums.RoleEnum;
import com.future.tailormade.payload.request.tailor.GetTailorsRequest;
import com.future.tailormade.payload.response.base.BasePagingResponse;
import com.future.tailormade.payload.response.base.helper.ResponseHelper;
import com.future.tailormade.payload.response.tailor.GetTailorsResponse;
import com.future.tailormade.repository.UserRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class GetTailorsCommandImpl implements GetTailorsCommand {

    @Autowired
    private UserRepository userRepository;

    @Override
    public Mono<BasePagingResponse<GetTailorsResponse>> execute(GetTailorsRequest request) {
        return getAllUsers(request)
                .map(this::createResponse)
                .collectList()
                .map(ResponseHelper::createPagingResponse)
                .flatMap(pagingResponse -> getTailorsCount(request.getKeyword(), pagingResponse));
    }

    private Flux<User> getAllUsers(GetTailorsRequest request) {
        Pageable pageable = ResponseHelper
                .createPageable(request.getPage(), request.getItemPerPage());
        return userRepository.findAllByRoleAndNameStartsWith(
                        RoleEnum.ROLE_TAILOR,
                        request.getKeyword(),
                        pageable
                )
                .switchIfEmpty(Flux.empty());
    }

    private Mono<BasePagingResponse<GetTailorsResponse>> getTailorsCount(
            String keyword, BasePagingResponse<GetTailorsResponse> pagingResponse) {
        return userRepository
                .countAllByRoleAndNameStartsWith(RoleEnum.ROLE_TAILOR, keyword)
                .map(count -> ResponseHelper
                        .setPagingResponseTotalItem(pagingResponse, count.intValue()));
    }

    private GetTailorsResponse createResponse(User user) {
        GetTailorsResponse response = GetTailorsResponse.builder().build();
        BeanUtils.copyProperties(user, response);
        return response;
    }
}
