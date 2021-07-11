package com.nixvoid.memoamautas.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;


@Data
public class UserDetailsImpl implements UserDetails {
    private String username;
    private String password;

    @JsonIgnore
    private Integer personCode;
    private String name;
    private String first_surname;
    private String second_surname;
    private String birthday;
    private String email;
    private String role;

    @Override
    @JsonIgnore
    public Collection<? extends GrantedAuthority> getAuthorities() {
        String authority;
        if(this.role.equals("22222222")) authority="teacher";
        else authority="student";

        return Collections.singletonList(new SimpleGrantedAuthority("ROLE_"+authority));
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.username;
    }

    @Override
    @JsonIgnore
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    @JsonIgnore
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    @JsonIgnore
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

}
