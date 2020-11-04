package com.future.tailormade.model.entity.user;

import com.future.tailormade.constants.UserConstants;
import com.future.tailormade.model.enums.GenderEnum;
import com.future.tailormade.model.enums.RoleEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = UserConstants.USER_COLLECTION)
public class User implements UserDetails {

    @Id
    private String id;

    private String phoneNumber;

    private String email;

    private String password;

    private String name;

    private String birthDate;

    private GenderEnum gender;

    private RoleEnum role;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getUsername() {
        return getEmail();
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }
}
