package com.example.ITBC_Project1.Controller;


import com.example.ITBC_Project1.Repository.LogRepo;
import com.example.ITBC_Project1.Repository.LogSQL;
import com.example.ITBC_Project1.entity.Log;
import com.example.ITBC_Project1.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
public class LogController {

    private final LogRepo logRepo;

    @Autowired
    public LogController(LogRepo logRepo) {
        this.logRepo = logRepo;
    }


    //@Post - Create Log
//Endpoint URL: /api/logs/create
//Request Body,Request Headers
//Response:
//201 - Created
//400 - Bad Request , Incorrect logType
//401 - Unauthorized , Incorrect token
//413 - Payload too large, Message should be less than 1024

//    @PostMapping("/api/logs/create")
//    public ResponseEntity<Void> create(@RequestBody Log log) {
////if(log.getMessesage().length() > 1024){
////    return ResponseEntity.status(HttpStatus.PAYLOAD_TOO_LARGE).body(null);
////}
////if(log.getLogType() > 3){
////    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
////}
////
////log.setId(UUID.randomUUID());
////logRepo.createLog(log);
////return ResponseEntity.status(HttpStatus.CREATED).body(null);
////    }
////}
////
//    }
//
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