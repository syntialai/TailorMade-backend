package com.future.tailormade.command.user.impl;

import com.future.tailormade.command.user.GetUserByIdCommand;
import com.future.tailormade.exceptions.NotFoundException;
import com.future.tailormade.model.entity.user.User;
import com.future.tailormade.payload.response.user.GetUserByIdResponse;
import com.future.tailormade.repository.UserRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class GetUserByIdCommandImpl implements GetUserByIdCommand {

    @Autowired
    private UserRepository userRepository;

    @Override
    public Mono<GetUserByIdResponse> execute(String request) {
        return findUser(request).map(this::createResponse);
    }

    private Mono<User> findUser(String id) {
        return userRepository.findById(id)
                .switchIfEmpty(Mono.error(NotFoundException::new));
    }

    private GetUserByIdResponse createResponse(User user) {
        GetUserByIdResponse userByIdResponse = GetUserByIdResponse.builder().build();
        BeanUtils.copyProperties(user, userByIdResponse);
        return userByIdResponse;
    }
}
