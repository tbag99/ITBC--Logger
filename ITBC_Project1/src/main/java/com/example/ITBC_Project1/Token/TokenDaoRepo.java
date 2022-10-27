package com.example.ITBC_Project1.Token;

import ch.qos.logback.core.subst.Token;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
@Component
public class TokenDaoRepo implements TokenDao {

    private Map<UUID, String> tokenDB = new HashMap<>();

    @Override
    public int generateToken(String username) {


        tokenDB.put(UUID.randomUUID(), username);

        return 1;
    }

//    @Override
//    public List<Token> allTokens() {
//        List<Token> allTokens = new ArrayList<>();
//
//      return tokenDB;
//    }

    @Override
    public boolean contains(String token) {

        return tokenDB.containsValue(token);
    }

    @Override
    public UUID getToken(String username) {
        UUID token = null;

        for (Map.Entry<UUID, String> pair : tokenDB.entrySet()) {
            if (pair.getValue().equals(username)) {
                token = pair.getKey();
            }
        }

            return token;

    }


}
