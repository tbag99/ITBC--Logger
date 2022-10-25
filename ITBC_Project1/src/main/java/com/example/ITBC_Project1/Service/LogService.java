package com.example.ITBC_Project1.Service;


import com.example.ITBC_Project1.entity.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.UUID;
//@Service
//public class LogService  {
//
//    private final LogRepo logrepo;
//    @Autowired
//
//    public LogService(LogRepo logrepo) {
//        this.logrepo = logrepo;
//    }
//
//    public ResponseEntity<Void> createLog(Log log){
//
//        log.setId(UUID.randomUUID());
//        logrepo.insertLog(log.getId(),log.getMessesage(), log.getLogType().ordinal());
//        return ResponseEntity.status(HttpStatus.CREATED).body(null);
//    }
//}
