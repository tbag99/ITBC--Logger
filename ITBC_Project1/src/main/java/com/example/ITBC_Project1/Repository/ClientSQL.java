package com.example.ITBC_Project1.Repository;

import com.example.ITBC_Project1.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class ClientSQL implements ClientRepo{




    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public void insertUser(User user) {

        String action = "INSERT INTO users ([id],[username],[email],[password],[userRole]) VALUES ('"
                + user.getId() + "','" + user.getUsername() + "','"
                + user.getEmail()+  "','" + user.getPassword()+ "','" + user.getUserRole()+ "')";

        jdbcTemplate.execute(action);
    }


}

