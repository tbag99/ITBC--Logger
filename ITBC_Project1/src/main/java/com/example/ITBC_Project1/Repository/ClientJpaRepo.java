package com.example.ITBC_Project1.Repository;

import com.example.ITBC_Project1.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;
@Repository

public interface ClientJpaRepo extends JpaRepository<User, UUID> {

    List<User> findAll();

    User findByUsername(String username);

    @Query(value = "SELECT COUNT(*) FROM clients WHERE username=:username", nativeQuery = true)
    Integer isDuplicateName(@Param("username") String username);


    @Query(value = "SELECT COUNT(*) FROM clients WHERE email=:email", nativeQuery = true)
    Integer isDuplicateEmail(@Param("email") String email);

    @Query(value = "SELECT COUNT(*) FROM clients WHERE password=:password", nativeQuery = true)
    Integer isPasswordExist(@Param("password") String password);
}
