package com.future.tailormade.payload.response.base.helper;

import com.future.tailormade.payload.response.base.*;

import java.util.List;

public class ResponseHelper {

    public static final Integer CODE_OK = 200;
    public static final Integer CODE_BAD_REQUEST = 400;
    public static final Integer CODE_UNAUTHORIZED = 401;
    public static final Integer CODE_NOT_FOUND = 404;

    public static final String STATUS_OK = "OK";
    public static final String STATUS_BAD_REQUEST = "BAD_REQUEST";
    public static final String STATUS_UNAUTHORIZED = "UNAUTHORIZED";
    public static final String STATUS_NOT_FOUND = "NOT_FOUND";

    public static BaseResponse OK() {
        return CreateBaseResponse.createResponse(CODE_OK, STATUS_OK);
    }

    public static <T> BaseResponseSingleObject OK(T data) {
        return CreateBaseResponse.createResponse(CODE_OK, STATUS_OK, data);
    }

    public static <T> BaseResponseSingleList OK(List<T> data) {
        return CreateBaseResponse.createResponse(CODE_OK, STATUS_OK, data);
    }

    public static <T> BaseResponseSingleListPaging OK(
            List<T> data,
            BaseResponsePaging paging
    ) {
        return CreateBaseResponse
                .createResponse(CODE_OK, STATUS_OK, data, paging);
    }
}
