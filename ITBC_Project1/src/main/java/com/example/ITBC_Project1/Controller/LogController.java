package com.example.ITBC_Project1.Controller;


import com.example.ITBC_Project1.Repository.LogRepo;
import com.example.ITBC_Project1.Token.TokenDao;
import com.example.ITBC_Project1.entity.Log;
import com.example.ITBC_Project1.entity.LogType;
import com.example.ITBC_Project1.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
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


    @PostMapping("/api/logs/create")
    public ResponseEntity<String> createLog(@RequestBody Log log, @RequestHeader UUID authorization, User user) {
        if (!(tokenDao.canCreate(authorization))) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Incorrect token");
        }

        if (!(log.getLogType().toString().equals("Warning") || log.getLogType().toString().equals("Error") ||
                log.getLogType().toString().equals("OK"))) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Incorect logType");
        }
        if (log.getMessage().length() > 1024) {
            return ResponseEntity.status(HttpStatus.PAYLOAD_TOO_LARGE).body("Messeage should be less than 1024");
        }

        log.setLocalDate(LocalDate.now());
        log.setId(UUID.randomUUID());
        logRepo.createLog(log);
        return ResponseEntity.status(HttpStatus.CREATED).body("Created");
    }

    @GetMapping("/api/logs/search")
    public ResponseEntity<List<Log>> searchLogs(@RequestHeader UUID authorization, @RequestParam Map<String, String> request) {
        List<Log> allLogs = logRepo.searchLog();
        List<Log> searchLog = new ArrayList<>();
        LocalDate date;
//can search missing
        if (!(tokenDao.canCreate(authorization))) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        }
//try catch to add
        if (request.get("dateFrom") != null) {
            date = LocalDate.parse(request.get("dateFrom"));
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }

        if (request.get("dateTo") != null) {
            date = LocalDate.parse(request.get("dateTo"));
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }

        for (var log : allLogs) {
            if ((request.get("message") == null) || (request.get("message").equals(log.getMessage()))) {
                if ((request.get("logType") == null) || ((request.get("logType").equals("OK")) || (request.get("logType").equals("Error")) || (request.get("logType").equals("Warning")))) {
                    if ((request.get("dateTo") == null) || log.getLocalDate().isBefore(LocalDate.parse(request.get("dateTo")))) {
                        if ((request.get("dateFrom") == null) || log.getLocalDate().isAfter(LocalDate.parse(request.get("dateFrom")))) {


                            searchLog.add(log);
                        }
                    }
                }
            }


        }
        return ResponseEntity.status(HttpStatus.OK).body(searchLog);


    }
}