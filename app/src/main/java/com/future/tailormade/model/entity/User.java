package com.future.tailormade.model.entity;

import com.future.tailormade.config.UserConstants;
import com.future.tailormade.model.enums.GenderEnum;
import com.future.tailormade.model.enums.RoleEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = UserConstants.USER_COLLECTION)
public class User {

    @Id
    private String id;

    private String phoneNumber;

    private String email;

    private String name;

    private String birthDate;

    private GenderEnum gender;

    private RoleEnum role;
}
