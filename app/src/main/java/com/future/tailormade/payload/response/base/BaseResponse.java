package com.future.tailormade.payload.response.base;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class BaseResponse {

    private Timestamp timestamp;

    private Integer errorCode;

    private String errorMessage;

    private Boolean success;
}
