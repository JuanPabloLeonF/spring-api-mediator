package dev.juanleon.spring_api.user.application.dto;

import java.util.UUID;

public class ResponseUser {

    private UUID id;
    private String userName;
    private String name;
    private String rol;
    private String password;

    public ResponseUser() {
    }

    public ResponseUser(UUID id, String userName, String name, String rol, String password) {
        this.id = id;
        this.userName = userName;
        this.name = name;
        this.rol = rol;
        this.password = password;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
