package com.future.tailormade.payload.response.base.helper;

import com.blibli.oss.common.response.Response;
import org.springframework.http.HttpStatus;

public class ResponseHelper {

    public static <T> Response<T> unauthorized() {
        return com.blibli.oss.common.response.ResponseHelper
                .status(HttpStatus.UNAUTHORIZED);
    }

    public static <T> Response<T> notFound() {
        return com.blibli.oss.common.response.ResponseHelper
                .status(HttpStatus.NOT_FOUND);
    }
}
