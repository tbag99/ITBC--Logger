package com.example.ITBC_Project1.Controller;


import com.example.ITBC_Project1.Repository.ClientJpaRepo;
import com.example.ITBC_Project1.Repository.ClientRepo;
import com.example.ITBC_Project1.Token.TokenDao;
import com.example.ITBC_Project1.config.PasswordValidation;
import com.example.ITBC_Project1.entity.User;
import com.example.ITBC_Project1.entity.UserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
public class ClientController {


    private final ClientRepo clientRepo;

    private final ClientJpaRepo clientJpaRepo;
    private final TokenDao tokenDao;
    private final BCryptPasswordEncoder encoder;

    private final PasswordValidation passwordValidation;

    @Autowired
    public ClientController(ClientRepo clientRepo, ClientJpaRepo clientJpaRepo, TokenDao tokenDao, BCryptPasswordEncoder encoder, PasswordValidation passwordValidation) {
        this.clientRepo = clientRepo;
        this.clientJpaRepo = clientJpaRepo;
        this.tokenDao = tokenDao;
        this.encoder = encoder;
        this.passwordValidation = passwordValidation;
    }


    @PostMapping("/api/clients/register")
    public ResponseEntity<String> registar(@RequestBody User user) {

        if (clientJpaRepo.isDuplicateName(user.getUsername()) != 0 || clientJpaRepo.isDuplicateEmail(user.getEmail()) != 0) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Username or Email already exist");
        }


        if (user.getUsername().length() < 3 || !(passwordValidation.isPasswordValid(user.getPassword()))
                || !(user.getEmail().contains("@")) || !(user.getEmail().contains("."))) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Bad credentials");
        }


        user.setId(UUID.randomUUID());
        user.setUserRole(UserRole.CLIENT);
        user.setPassword(encoder.encode(user.getPassword()));
       // user.setLogCount(0);


        clientRepo.insertUser(user);
        return ResponseEntity.status(HttpStatus.CREATED).body("Client created");
    }


    @GetMapping("/api/clients")
    public ResponseEntity<List> getAllClients(@RequestHeader UUID  authorization) {

        if (tokenDao.canCreate(authorization)) {
            if (!(tokenDao.isAdmin(authorization))) {


                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
            }
        }


        return ResponseEntity.status(HttpStatus.ACCEPTED).body(clientRepo.getAllClients());

    }


    @PostMapping("/api/clients/login")
    public ResponseEntity<String> loginClinet(@RequestBody User user) {

        String password = user.getPassword();
        String account = user.getUsername();


        if (clientJpaRepo.isPasswordExist(password) == 0 || clientJpaRepo.isAccountExist(account) == 0) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Email/Username or password incorrect");
        }

        if (clientJpaRepo.isAccountExist(account) == 1) {
            if (clientJpaRepo.isPasswordExist(password) == 0) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Incorect password");
            }
        }
        if (tokenDao.contains(account)) {




            return ResponseEntity.status(HttpStatus.OK).body(" Token = " + tokenDao.getToken(account));
        }
        if (tokenDao.generateToken(account) != 1) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Token Not Created");
        }

        return ResponseEntity.status(HttpStatus.OK).body("Token created " + account +
                " token = " + tokenDao.getToken(account));
    }


    @PatchMapping("/api/clients/{clientId}/reset-password")

    public ResponseEntity<String> changeClientpassword(@RequestBody User user, @RequestParam(name = "clientId") UUID id, @RequestHeader UUID authorization) {


        if (!(tokenDao.isAdmin(authorization))) {

            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Correct token, but not admin");
        }

        if (user.getPassword().equals("")) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body("No content");
        }
        clientRepo.changePassword(id, encoder.encode(user.getPassword()));
        return ResponseEntity.status(HttpStatus.ACCEPTED).body("Successful change");


    }
}