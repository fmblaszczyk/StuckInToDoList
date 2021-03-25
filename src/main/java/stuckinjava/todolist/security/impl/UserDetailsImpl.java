package stuckinjava.todolist.security.impl;

import java.util.*;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnore;
import stuckinjava.todolist.general.model.User;
import stuckinjava.todolist.security.model.ERole;
import stuckinjava.todolist.security.model.Privilege;
import stuckinjava.todolist.security.model.Role;

public class UserDetailsImpl implements UserDetails {
    private static final long serialVersionUID = 1L;

    private int id;

    private String username;

    private String email;

    @JsonIgnore
    private String password;

    Set<ERole> roles;

    private Collection<? extends GrantedAuthority> authorities;

    public UserDetailsImpl(int id, String username, String email, String password,Set<ERole> roles,
                           Collection<? extends GrantedAuthority> authorities) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.password = password;
        this.roles = roles;
        this.authorities = authorities;
    }

    public static UserDetailsImpl build(User user) {
        /* Translating roles to privileges in DB */
        List<Privilege> rolePrivileges = new ArrayList<>();
        for (Role role : user.getRoles()) {
            rolePrivileges.addAll(role.getPrivileges());
        }
        List<GrantedAuthority> authorities = rolePrivileges.stream()
                .map(privilege -> new SimpleGrantedAuthority(privilege.getName()))
                .collect(Collectors.toList());

        Set<ERole> rolesNames = user.getRoles().stream()
                .map(role->role.getName())
                .collect(Collectors.toSet());

        return new UserDetailsImpl(
                user.getId(),
                user.getUsername(),
                user.getEmail(),
                user.getPassword(),
                rolesNames,
                authorities);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    public int getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
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

    public Set<ERole> getRoles() {
        return roles;
    }

    public void setRoles(Set<ERole> roles) {
        this.roles = roles;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        UserDetailsImpl user = (UserDetailsImpl) o;
        return Objects.equals(id, user.id);
    }
}