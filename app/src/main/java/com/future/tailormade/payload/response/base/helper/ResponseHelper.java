package com.future.tailormade.payload.response.base.helper;

import com.blibli.oss.common.paging.Paging;
import com.blibli.oss.common.response.Response;
import org.springframework.http.HttpStatus;

public class ResponseHelper {

    public static <T> Response<T> unauthorized() {
        return com.blibli.oss.common.response.ResponseHelper.status(HttpStatus.UNAUTHORIZED);
    }

    public static <T> Response<T> notFound() {
        return com.blibli.oss.common.response.ResponseHelper.status(HttpStatus.NOT_FOUND);
    }

    public static <T> Response<T> ok(T data, int page, int itemPerPage, int totalItem) {
        Response<T> response = com.blibli.oss.common.response.ResponseHelper.ok(data);
        response.setPaging(createPaging(page, itemPerPage, totalItem));
        return response;
    }

    private static Paging createPaging(int page, int itemPerPage, int totalItem) {
        return Paging.builder()
                .page(page)
                .itemPerPage(itemPerPage)
                .totalItem(totalItem)
                .totalPage(totalItem / itemPerPage)
                .build();
    }
}
