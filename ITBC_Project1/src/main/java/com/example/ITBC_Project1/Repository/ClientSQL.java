package com.example.ITBC_Project1.Repository;

import com.example.ITBC_Project1.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public class ClientSQL implements ClientRepo {


    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public void insertUser(User user) {

        String action = "INSERT INTO users ([id],[username],[email],[password],[userRole]) VALUES ('"
                + user.getId() + "','" + user.getUsername() + "','"
                + user.getEmail() + "','" + user.getPassword() + "','"
                + user.getUserRole() +  "')";

        jdbcTemplate.execute(action);
    }

    @Override
    public List<User> getAllClients() {
        String query = "SELECT * FROM users WHERE userRole LIKE 'CLIENT' ";


        return jdbcTemplate.query(
                query,
                BeanPropertyRowMapper.newInstance(User.class)
        );
    }

    @Override
    public void changePassword(UUID clientId,String password) {
        

        String action  ="UPDATE users SET [password]='"+password+"' WHERE id='"+clientId+"'";
       jdbcTemplate.execute(action);

    }

//    @Override
//    public void updateLogCount(int count,String username) {
//        String action  ="UPDATE users SET [logCount]='"+count+"' WHERE [username]='"+username+"'";
//        jdbcTemplate.execute(action);
//    }
}
