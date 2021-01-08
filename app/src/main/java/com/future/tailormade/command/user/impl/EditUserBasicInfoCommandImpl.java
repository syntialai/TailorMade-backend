package com.future.tailormade.command.user.impl;

import com.future.tailormade.command.user.EditUserBasicInfoCommand;
import com.future.tailormade.model.entity.user.User;
import com.future.tailormade.payload.request.user.EditUserBasicInfoRequest;
import com.future.tailormade.payload.response.user.EditUserBasicInfoResponse;
import com.future.tailormade.repository.UserRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class EditUserBasicInfoCommandImpl implements EditUserBasicInfoCommand {

    @Autowired
    private UserRepository userRepository;

    @Override
    public Mono<EditUserBasicInfoResponse> execute(EditUserBasicInfoRequest request) {
        return userRepository.findById(request.getId())
                .flatMap(user -> updateUser(user, request))
                .map(this::createResponse);
    }

    private EditUserBasicInfoResponse createResponse(User user) {
        EditUserBasicInfoResponse response = EditUserBasicInfoResponse.builder().build();

        BeanUtils.copyProperties(user, response);

        return response;
    }

    private Mono<User> updateUser(User user, EditUserBasicInfoRequest request) {
        BeanUtils.copyProperties(request, user);

        return userRepository.save(user);
    }
}
