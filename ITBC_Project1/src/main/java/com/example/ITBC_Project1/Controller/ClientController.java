package com.example.ITBC_Project1.Controller;


import com.example.ITBC_Project1.Repository.ClientJpaRepo;
import com.example.ITBC_Project1.Repository.ClientRepo;
import com.example.ITBC_Project1.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
public class ClientController {


    private final ClientRepo clientRepo;

    private final ClientJpaRepo clientJpaRepo;

    @Autowired

    public ClientController(ClientRepo clientRepo, ClientJpaRepo clientJpaRepo) {
        this.clientRepo = clientRepo;
        this.clientJpaRepo = clientJpaRepo;
    }

//@Post - Registar
    //Endpoint URL: /api/clients/register
    //Request body
    //Responses:
    //201 - Registered
    //400 - Bad Request
    //email must be valid
    //username at least 3 characters
    //password at least 8 characters and one letter and one number
    //409 - Conflict
    //username already exists
    //email already exists


    @PostMapping("/api/clients/register")
    public ResponseEntity<Void> registar(@RequestBody User user) {
        if (clientJpaRepo.isDuplicateName(user.getUsername()) != 0 || clientJpaRepo.isDuplicateEmail(user.getEmail()) != 0) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
        }

        if (user.getUsername().length() < 3 || user.getPassword().length() < 8
                || (user.getEmail().contains("@") && user.getEmail().contains("."))) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }


        user.setId(UUID.randomUUID());

        clientRepo.insertClient(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(null);
    }



    @GetMapping("/api/clients/all")
    public List<User> getAllClients(){
       return clientJpaRepo.findAll();
    }


    //@Post - Login
    //Endpoint URL: /api/clients/login
    //Request body
    //Response:
    // 200 - ok
    //400 - Bad Request

    @PostMapping("/api/clients/login")
    public ResponseEntity<Void> loginClinet(@RequestBody String username, String password) {
        if (clientJpaRepo.isDuplicateName(username) == 0 || clientJpaRepo.isPasswordExist(password) == 0) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
        return ResponseEntity.status(HttpStatus.OK).body(null);
    }
}



