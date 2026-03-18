package dev.juanleon.spring_api.user.infrastructure.api.output.database.entity;

import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(name = "users")
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @Column(name = "user_name",unique = true, nullable = false)
    private String userName;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String rol;
    @Column(nullable = false)
    private String password;

    public UserEntity() {
    }

    public UserEntity(UUID id, String userName, String name, String rol, String password) {
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
