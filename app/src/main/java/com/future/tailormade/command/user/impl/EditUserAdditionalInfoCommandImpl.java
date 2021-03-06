package com.future.tailormade.command.user.impl;

import com.future.tailormade.command.user.EditUserAdditionalInfoCommand;
import com.future.tailormade.exceptions.NotFoundException;
import com.future.tailormade.model.entity.user.User;
import com.future.tailormade.payload.request.user.EditUserAdditionalInfoRequest;
import com.future.tailormade.payload.response.user.EditUserAdditionalInfoResponse;
import com.future.tailormade.repository.UserRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class EditUserAdditionalInfoCommandImpl implements EditUserAdditionalInfoCommand {

    @Autowired
    private UserRepository userRepository;
    
    @Override
    public Mono<EditUserAdditionalInfoResponse> execute(EditUserAdditionalInfoRequest request) {
        return findUser(request.getId())
                .flatMap(user -> updateUser(user, request))
                .map(this::createResponse);
    }

    private Mono<User> findUser(String id) {
        return userRepository.findById(id)
                .switchIfEmpty(Mono.error(NotFoundException::new));
    }

    private EditUserAdditionalInfoResponse createResponse(User user) {
        EditUserAdditionalInfoResponse response = EditUserAdditionalInfoResponse.builder().build();
        BeanUtils.copyProperties(user, response);
        return response;
    }

    private Mono<User> updateUser(User user, EditUserAdditionalInfoRequest request) {
        BeanUtils.copyProperties(request, user);
        return userRepository.save(user);
    }
}
