package com.example.ITBC_Project1.Repository;

import com.example.ITBC_Project1.entity.User;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientRepo {

    void insertClient(User user);



}
