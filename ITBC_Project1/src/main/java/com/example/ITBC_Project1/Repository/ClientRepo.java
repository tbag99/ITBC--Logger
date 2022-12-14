package com.example.ITBC_Project1.Repository;

import com.example.ITBC_Project1.entity.User;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ClientRepo {

    void insertUser(User user);

    void changePassword(UUID clientId,String password);

 //   void updateLogCount(int count,String username);

List<User> getAllClients();


}
