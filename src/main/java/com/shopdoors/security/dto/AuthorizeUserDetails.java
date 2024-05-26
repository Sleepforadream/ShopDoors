package com.shopdoors.security.dto;

import com.shopdoors.dao.entity.AuthorizeUser;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

public class AuthorizeUserDetails implements UserDetails {

    private final AuthorizeUser authorizeUser;

    public AuthorizeUserDetails(AuthorizeUser authorizeUser) {
        this.authorizeUser = authorizeUser;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getUsername() {
        return authorizeUser.getEmail();
    }

    @Override
    public String getPassword() {
        return authorizeUser.getPassword();
    }

    public String getNickName() {
        return authorizeUser.getNickName();
    }

    public String getPhoneNumber() {
        return authorizeUser.getPhoneNumber().toString();
    }

    public String getAddress() {
        return authorizeUser.getAddress();
    }

    public String getFirstName() {
        return authorizeUser.getFirstName();
    }

    public String getSecondName() {
        return authorizeUser.getSecondName();
    }

    public String getThirdName() {
        return authorizeUser.getThirdName();
    }

    public String getGender() {
        return authorizeUser.getGender();
    }

    public String getBirthDate() {
        return authorizeUser.getBirthDate().toString();
    }

    public String getRegisterDate() {
        return authorizeUser.getRegisterDate().toString();
    }

    public String getImgPath() {
        return authorizeUser.getImgPath();
    }

    public String getInfo() {
        return authorizeUser.getInfo();
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