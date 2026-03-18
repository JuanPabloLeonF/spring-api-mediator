package dev.juanleon.spring_api.user.application.dto;

import dev.juanleon.spring_api.common.utils.enums.Rols;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class RequestUser {

    @NotBlank
    @Size(min = 3, max = 100, message = "userName must be between 3 and 100 characters")
    private String userName;
    @NotBlank
    @Size(min = 3, max = 100, message = "name must be between 3 and 100 characters")
    private String name;
    @NotBlank
    @Size(min = 3, max = 100, message = "rol must be between 3 and 100 characters")
    @Enumerated(value = EnumType.STRING)
    private Rols rol;
    @NotBlank
    @Size(min = 3, max = 100, message = "password must be between 3 and 100 characters")
    @Pattern(
            regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d).+$",
            message = "password must contain at least one uppercase letter, one lowercase letter, and one number"
    )
    private String password;

    public RequestUser() {
    }

    public RequestUser(String userName, String name, Rols rol, String password) {
        this.userName = userName;
        this.name = name;
        this.rol = rol;
        this.password = password;
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

    public Rols getRol() {
        return rol;
    }

    public void setRol(Rols rol) {
        this.rol = rol;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
