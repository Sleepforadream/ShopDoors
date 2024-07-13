package com.shopdoors.dto;

import com.shopdoors.dao.entity.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

public record AuthorizeUserDetails(User user) implements UserDetails {

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getUsername() {
        return user.getEmail();
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    public String getNickName() {
        return user.getNickName();
    }

    public String getPhoneNumber() {
        return user.getPhoneNumber().toString();
    }

    public String getAddress() {
        return user.getAddress();
    }

    public String getFirstName() {
        return user.getFirstName();
    }

    public String getSecondName() {
        return user.getSecondName();
    }

    public String getThirdName() {
        return user.getThirdName();
    }

    public String getGender() {
        return user.getGender();
    }

    public String getBirthDate() {
        return user.getBirthDate().toString();
    }

    public String getRegisterDate() {
        return user.getRegisterDate().toString();
    }

    public String getImgPath() {
        return user.getImgPath();
    }

    public String getInfo() {
        return user.getInfo();
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
}