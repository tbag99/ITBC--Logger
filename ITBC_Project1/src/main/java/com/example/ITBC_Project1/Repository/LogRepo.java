package com.example.ITBC_Project1.Repository;

import com.example.ITBC_Project1.entity.Log;
import org.springframework.stereotype.Repository;

@Repository
public interface LogRepo {
    void createLog(Log log);
}
