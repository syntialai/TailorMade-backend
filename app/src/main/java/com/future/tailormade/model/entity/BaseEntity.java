package com.future.tailormade.model.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;

import java.util.Date;

@Data
public class BaseEntity {

    @Id
    private String id;

    private Date createdAt;

    private Date updatedAt;
}
