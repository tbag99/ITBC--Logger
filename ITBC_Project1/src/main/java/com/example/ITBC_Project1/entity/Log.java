package com.example.ITBC_Project1.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.UUID;
@Entity
@Table(name = "log")
@Getter
@Setter
public class Log {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private UUID id;
    @Column(name = "messesage" ,unique = true,length = 1024)
    private String messesage;
  @Enumerated
    private  LogType logType;



    public Log(String messesage,LogType logType) {
        this.id = UUID.randomUUID();
        this.messesage = messesage;
        this.logType = logType;

    }

    public Log() {

    }
}
