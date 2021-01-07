package com.future.tailormade.command.tailor.impl;

import com.future.tailormade.command.tailor.EditTailorDesignCommand;
import com.future.tailormade.payload.request.tailor.EditTailorDesignRequest;
import com.future.tailormade.payload.response.tailor.AddOrEditTailorDesignResponse;
import com.future.tailormade.repository.DesignRepository;
import com.future.tailormade.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class EditTailorDesignCommandImpl implements EditTailorDesignCommand {

    @Autowired
    private DesignRepository designRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public Mono<AddOrEditTailorDesignResponse> execute(EditTailorDesignRequest request) {
        return null;
    }
}
