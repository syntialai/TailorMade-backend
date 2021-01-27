package com.future.tailormade.service;

import com.future.tailormade.payload.request.image.AddImageRequest;
import reactor.core.publisher.Mono;

public interface AmazonClientService {

    Mono<String> uploadFile(AddImageRequest request);
}
