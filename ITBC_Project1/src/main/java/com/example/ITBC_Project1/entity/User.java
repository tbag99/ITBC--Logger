package com.example.ITBC_Project1.entity;

import javax.persistence.*;
import java.util.UUID;


@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    private UUID id;
    @Column(name = "username", unique = true)
    private String username;
    @Column(name = "email", unique = true)
    private String email;
    @Column(name = "password", unique = true)
    private String password;
    @Enumerated(EnumType.STRING)
    @Column(name = "userRole")
    private UserRole userRole;



//@Column(name="logCount")
//private int logCount ;
//


    public User() {
    }


    public User(String username, String email, String password, UserRole userRole) {
        this.id = UUID.randomUUID();
        this.username = username;
        this.email = email;
        this.password = password;
        this.userRole = userRole;
        //  this.logCount = logCount;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public UserRole getUserRole() {
        return userRole;
    }

    public void setUserRole(UserRole userRole) {
        this.userRole = userRole;
    }

    //  public int getLogCount() {
    //      return logCount;
    //  }

    //  public void setLogCount(int logCount) {
    //      this.logCount = logCount;
    //  }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
