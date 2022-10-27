package com.example.ITBC_Project1.Controller;


import com.example.ITBC_Project1.Repository.ClientJpaRepo;
import com.example.ITBC_Project1.Repository.ClientRepo;
import com.example.ITBC_Project1.Token.TokenDao;
import com.example.ITBC_Project1.Token.TokenDaoRepo;
import com.example.ITBC_Project1.entity.User;
import com.example.ITBC_Project1.entity.UserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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
    private final TokenDao tokenDao;
@Autowired
    public ClientController(ClientRepo clientRepo, ClientJpaRepo clientJpaRepo, TokenDao tokenDao) {
        this.clientRepo = clientRepo;
        this.clientJpaRepo = clientJpaRepo;
        this.tokenDao = tokenDao;
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
    public ResponseEntity<String> registar(@RequestBody User user) {


        if (clientJpaRepo.isDuplicateName(user.getUsername()) != 0 || clientJpaRepo.isDuplicateEmail(user.getEmail()) != 0) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Username or Email already exist");
        }

        if (user.getUsername().length() < 3 || user.getPassword().length() < 8
                || (user.getEmail().contains("@") && user.getEmail().contains("."))) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Bad credentials");
        }


        user.setId(UUID.randomUUID());
        user.setUserRole(UserRole.CLIENT);


        clientRepo.insertUser(user);
        return ResponseEntity.status(HttpStatus.CREATED).body("Client created");
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
    public ResponseEntity<String> loginClinet(@RequestBody User user) {
        String password = user.getPassword();
        String account = user.getUsername();



        if (clientJpaRepo.isPasswordExist(password) ==0 || clientJpaRepo.isAccountExist(account) == 0) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("invalid credidentials");
        }

        if(clientJpaRepo.isAccountExist(account)==1){
            if(clientJpaRepo.isPasswordExist(password)==0){
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("incorect password");
            }
        }
if(tokenDao.contains(account)){
    return ResponseEntity.status(HttpStatus.OK).body("token = " +tokenDao.getToken(account));
}
if(tokenDao.generateToken(account)!=1) {
    return ResponseEntity.status(HttpStatus.CONFLICT).body("Token Not Created");
}
        return ResponseEntity.status(HttpStatus.OK).body("Token created " + account +
                "token = " + tokenDao.getToken(account));
    }


}




