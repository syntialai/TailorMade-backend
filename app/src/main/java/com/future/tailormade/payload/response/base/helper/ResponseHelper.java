package com.future.tailormade.payload.response.base.helper;

import com.blibli.oss.common.paging.Paging;
import com.blibli.oss.common.response.Response;
import com.future.tailormade.payload.response.base.BasePagingResponse;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;

import java.util.List;

public class ResponseHelper {

    public static <T> Response<T> unauthorized() {
        return com.blibli.oss.common.response.ResponseHelper.status(HttpStatus.UNAUTHORIZED);
    }

    public static <T> Response<T> notFound() {
        return com.blibli.oss.common.response.ResponseHelper.status(HttpStatus.NOT_FOUND);
    }

    public static <T> Response<T> created() {
        return com.blibli.oss.common.response.ResponseHelper.status(HttpStatus.CREATED);
    }

    public static <T> Response<T> ok(T data, int page, int itemPerPage, int totalItem) {
        Response<T> response = com.blibli.oss.common.response.ResponseHelper.ok(data);
        response.setPaging(createPaging(page, itemPerPage, totalItem));
        return response;
    }

    public static Pageable createPageable(int page, int size) {
        return PageRequest.of(page, size);
    }

    public static <T> BasePagingResponse<T> createPagingResponse(List<T> response) {
        return BasePagingResponse.<T>builder()
                .data(response)
                .build();
    }

    public static <T> BasePagingResponse<T> setPagingResponseTotalItem(
            BasePagingResponse<T> pagingResponse, Integer totalItem)
    {
        pagingResponse.setTotalItem(totalItem);
        return pagingResponse;
    }

    private static Paging createPaging(int page, int itemPerPage, int totalItem) {
        int totalPage = totalItem / itemPerPage;
        if ((totalItem % itemPerPage) > 0) {
            totalPage += 1;
        }
        return Paging.builder()
                .page(page)
                .itemPerPage(itemPerPage)
                .totalItem(totalItem)
                .totalPage(totalPage)
                .build();
    }
}
