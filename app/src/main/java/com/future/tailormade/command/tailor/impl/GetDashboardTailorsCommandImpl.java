package com.future.tailormade.command.tailor.impl;

import com.future.tailormade.command.tailor.GetDashboardTailorsCommand;
import com.future.tailormade.model.entity.user.User;
import com.future.tailormade.model.enums.RoleEnum;
import com.future.tailormade.payload.request.tailor.GetDashboardTailorsRequest;
import com.future.tailormade.payload.response.base.BasePagingResponse;
import com.future.tailormade.payload.response.base.helper.ResponseHelper;
import com.future.tailormade.payload.response.tailor.GetDashboardTailorsResponse;
import com.future.tailormade.repository.UserRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class GetDashboardTailorsCommandImpl implements GetDashboardTailorsCommand {

    @Autowired
    private UserRepository userRepository;

    @Override
    public Mono<BasePagingResponse<GetDashboardTailorsResponse>> execute(
            GetDashboardTailorsRequest request)
    {
        return getTailors(request)
                .map(this::createResponse)
                .collectList()
                .map(ResponseHelper::createPagingResponse)
                .flatMap(this::getTailorsCount);
    }

    private Flux<User> getTailors(GetDashboardTailorsRequest request) {
        Pageable pageable = ResponseHelper
                .createPageable(request.getPage(), request.getItemPerPage());
        return userRepository.findAllByRole(RoleEnum.ROLE_TAILOR, pageable)
                .switchIfEmpty(Flux.empty());
    }

    private Mono<BasePagingResponse<GetDashboardTailorsResponse>> getTailorsCount(
            BasePagingResponse<GetDashboardTailorsResponse> pagingResponse) {
        return userRepository.countAllByRole(RoleEnum.ROLE_TAILOR)
                .map(count -> ResponseHelper
                        .setPagingResponseTotalItem(pagingResponse, count.intValue()));
    }

    private GetDashboardTailorsResponse createResponse(User tailor) {
        GetDashboardTailorsResponse response = GetDashboardTailorsResponse.builder().build();
        BeanUtils.copyProperties(tailor, response);
        return response;
    }
}
