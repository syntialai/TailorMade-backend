package com.future.tailormade.model.entity.base;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Location {

    private String address;

    private String distinct;

    private String city;

    private String country;

    private String postCode;
}
