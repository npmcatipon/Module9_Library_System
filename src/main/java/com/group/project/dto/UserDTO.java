package com.group.project.dto;

import java.util.List;

import com.group.project.entity.Role;

public class UserDTO {

    private Long id;
    private String username;
    private List<Role> roles;
    private Boolean enabled;

    public UserDTO() {
    }

    public UserDTO(Long id, String username, List<Role> roles, Boolean enabled) {
        this.id = id;
        this.username = username;
        this.roles = roles;
        this.enabled = enabled;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }
}
