package com.example.ITBC_Project1.Repository;

import com.example.ITBC_Project1.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.UUID;
@Repository

public interface ClientJpaRepo extends JpaRepository<User, UUID> {


    User findByUsername(String username);


    @Transactional
@Query(value ="UPDATE users SET password=:password ",nativeQuery = true)
  Void changePassword(@Param("password")String password);




    @Query(value = "SELECT COUNT(*) FROM users WHERE username=:username", nativeQuery = true)
    Integer isDuplicateName(@Param("username") String username);

    @Query(value = "SELECT COUNT(*) FROM users WHERE username=:account", nativeQuery = true)
    Integer isAccountExist(@Param("account") String username);


    @Query(value = "SELECT COUNT(*) FROM users WHERE email=:email", nativeQuery = true)
    Integer isDuplicateEmail(@Param("email") String email);

    @Query(value = "SELECT COUNT(*) FROM users WHERE [password]=:password", nativeQuery = true)
    Integer isPasswordExist(@Param("password") String password);

    @Query(value = "SELECT COUNT(*) FROM users WHERE userRole=:userRole", nativeQuery = true)
    Integer checkRole(@Param("userRole") String userRole);






}
