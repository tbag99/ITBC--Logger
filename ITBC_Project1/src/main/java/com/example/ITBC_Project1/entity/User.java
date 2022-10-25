package com.example.ITBC_Project1.entity;

import javax.persistence.*;
import java.util.UUID;


@Entity
@Table(name = "clients")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
private UUID id;
@Column(name = "username" ,unique = true,length = 50)
private String username;
    @Column(name = "email" ,unique = true,length = 50)
private String email;
    @Column(name = "password" ,unique = true,length =80)
private String password ;
//@Enumerated(EnumType.STRING)
//@Column(name = "userRole" , length =80)
//private UserRole userRole;






    public User() {
    }


    public User(String username, String email, String password) {

        this.username = username;
        this.email = email;
        this.password = password;

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

//    public UserRole getUserRole() {
//        return userRole;
//    }
//
//    public void setUserRole(UserRole userRole) {
//        this.userRole = userRole;
//    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
