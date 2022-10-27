package com.example.ITBC_Project1.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.UUID;
@Entity
@Table(name = "Logs")
@Getter
@Setter
public class Log {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private UUID id;
    @Column(name = "message" ,unique = true,length = 1024)
    private String message;

    @Column(name = "LocalDate")
    private LocalDate localDate;
    @Enumerated(EnumType.STRING)
    @Column(name = "logType" )
    private  LogType logType;



    public Log(String message,LocalDate localDate,LogType logType) {
        this.id = UUID.randomUUID();
        this.message= message;
        this.localDate = localDate;
        this.logType = logType;

    }


    public Log() {

    }
}
