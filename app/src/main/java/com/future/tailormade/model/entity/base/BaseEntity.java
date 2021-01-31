package com.future.tailormade.model.entity.base;

import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

@Data
public class BaseEntity {

    @CreatedDate
    private Long createdAt;

    @LastModifiedDate
    private Long updatedAt;
}
