package com.future.tailormade.command.tailor.impl;

import com.future.tailormade.command.tailor.DeleteTailorDesignByIdCommand;
import com.future.tailormade.exceptions.NotFoundException;
import com.future.tailormade.model.entity.user.User;
import com.future.tailormade.model.enums.RoleEnum;
import com.future.tailormade.payload.request.tailor.DeleteTailorDesignRequest;
import com.future.tailormade.repository.DesignRepository;
import com.future.tailormade.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class DeleteTailorDesignByIdCommandImpl implements DeleteTailorDesignByIdCommand {

    @Autowired
    private DesignRepository designRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public Mono<Void> execute(DeleteTailorDesignRequest request) {
        return deleteDesign(request).then(deleteTailorDesign(request));
    }

    private Mono<Void> deleteDesign(DeleteTailorDesignRequest request) {
        return designRepository.deleteByTailorIdAndId(request.getTailorId(), request.getId());
    }

    private Mono<Void> deleteTailorDesign(DeleteTailorDesignRequest request) {
        return userRepository.findByIdAndRole(request.getTailorId(), RoleEnum.ROLE_TAILOR)
                .switchIfEmpty(Mono.error(NotFoundException::new))
                .flatMap(tailor -> updateTailor(request.getId(), tailor))
                .then();
    }

    private Mono<User> updateTailor(String id, User tailor) {
        tailor.deleteDesign(id);
        return userRepository.save(tailor);
    }
}
