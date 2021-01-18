package com.future.tailormade.command.tailor.impl;

import com.future.tailormade.command.tailor.GetTailorByIdCommand;
import com.future.tailormade.exceptions.NotFoundException;
import com.future.tailormade.model.entity.user.User;
import com.future.tailormade.model.enums.RoleEnum;
import com.future.tailormade.payload.response.user.GetUserByIdResponse;
import com.future.tailormade.repository.UserRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class GetTailorByIdCommandImpl implements GetTailorByIdCommand {

    @Autowired
    private UserRepository userRepository;

    @Override
    public Mono<GetUserByIdResponse> execute(String request) {
        return findTailor(request).map(this::createResponse);
    }

    private Mono<User> findTailor(String id) {
        return userRepository.findByIdAndRole(id, RoleEnum.ROLE_TAILOR)
                .switchIfEmpty(Mono.error(NotFoundException::new));
    }

    private GetUserByIdResponse createResponse(User user) {
        GetUserByIdResponse userByIdResponse = GetUserByIdResponse.builder().build();
        BeanUtils.copyProperties(user, userByIdResponse);
        return userByIdResponse;
    }
}
