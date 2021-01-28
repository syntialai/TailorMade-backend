package com.future.tailormade.model.entity.user;

import com.future.tailormade.constants.CollectionConstants;
import com.future.tailormade.exceptions.NotFoundException;
import com.future.tailormade.model.entity.base.BaseEntity;
import com.future.tailormade.model.entity.base.Location;
import com.future.tailormade.model.enums.GenderEnum;
import com.future.tailormade.model.enums.RoleEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = CollectionConstants.USER_COLLECTION)
public class User extends BaseEntity implements UserDetails {

    @Id
    private String id;

    private String phoneNumber;

    private String email;

    private String password;

    private String name;

    private Long birthDate;

    private GenderEnum gender;

    private RoleEnum role;

    private String image;

    private Location location;

    private Education education;

    private Occupation occupation;

    private List<TailorDesign> designs = Collections.emptyList();

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
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public void addDesign(TailorDesign design) {
        this.designs.add(design);
    }

    public boolean deleteDesign(String designId) {
        return this.designs.removeIf(tailorDesign -> tailorDesign.getId().equals(designId));
    }

    public void editDesign(TailorDesign editedDesign) {
        TailorDesign tailorDesign = this.designs.stream()
                .filter(design -> design.getId().equals(editedDesign.getId()))
                .findAny()
                .orElseThrow(NotFoundException::new);
        deleteDesign(tailorDesign.getId());
        addDesign(editedDesign);
    }
}
