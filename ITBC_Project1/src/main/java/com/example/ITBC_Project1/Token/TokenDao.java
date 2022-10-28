package com.example.ITBC_Project1.Token;

import ch.qos.logback.core.subst.Token;

import java.util.List;
import java.util.UUID;

public interface TokenDao {
    int generateToken(String username);


    boolean contains(String account);

    UUID getToken(String username);

    boolean isAdmin(UUID id);

    boolean canCreate(UUID id);

}
