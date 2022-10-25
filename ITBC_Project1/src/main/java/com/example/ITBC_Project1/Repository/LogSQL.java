package com.example.ITBC_Project1.Repository;

import com.example.ITBC_Project1.entity.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class LogSQL implements LogRepo{

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public void createLog(Log log,String token) {
        String action = "INSERT INTO log ([id],[messesage],[logType]) VALUES ('"
                + log.getId() + "','" +log.getMessesage() + "','" + log.getLogType()+ "')";

        jdbcTemplate.execute(action);

    }
}