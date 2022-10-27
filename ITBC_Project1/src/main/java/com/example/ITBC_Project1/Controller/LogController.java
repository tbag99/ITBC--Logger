package com.example.ITBC_Project1.Controller;


import com.example.ITBC_Project1.Repository.LogRepo;
import com.example.ITBC_Project1.Token.TokenDao;
import com.example.ITBC_Project1.entity.Log;
import com.example.ITBC_Project1.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.UUID;

@RestController
public class LogController {

    private final LogRepo logRepo;
    private final TokenDao tokenDao;

    @Autowired
    public LogController(LogRepo logRepo, TokenDao tokenDao) {
        this.logRepo = logRepo;
        this.tokenDao = tokenDao;
    }


    //@Post - Create Log
//Endpoint URL: /api/logs/create
//Request Body,Request Headers
//Response:
//201 - Created
//400 - Bad Request , Incorrect logType
//401 - Unauthorized , Incorrect token
//413 - Payload too large, Message should be less than 1024


    @PostMapping("/api/logs/create")
    public ResponseEntity<String> createLog(@RequestBody Log log, @RequestHeader UUID token,User user) {
        if(tokenDao.getToken(user.getUsername()) == token){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Incorrect token");
        }

        if(!(log.getLogType().toString().equals("Warning") ||log.getLogType().toString().equals("Error") ||
                log.getLogType().toString().equals("OK"))){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Incorect logType");
        }
//        if(log.getMessesage().length() > 1024){
//            return ResponseEntity.status(HttpStatus.PAYLOAD_TOO_LARGE).body("Messeage should be less than 1024");
//        }
            log.setLocalDate(LocalDate.now());
        log.setId(UUID.randomUUID());
        logRepo.createLog(log);
        return ResponseEntity.status(HttpStatus.CREATED).body("Created");
    }






////@Get - Search Logs
////Endpoint URL: /api/logs/search
////Request params,Request headers
////Response: 400 - Bad request
//// -Invalid dates
////  -Invalid logType
////401 - Unauthorized
////  -Incorrect token
////}
//}
}