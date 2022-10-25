//package com.example.ITBC_Project1.Service;
//
//import com.example.ITBC_Project1.Repository.ClientJpaRepo;
//import com.example.ITBC_Project1.custom.CustomUserDetails;
//import com.example.ITBC_Project1.entity.User;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.stereotype.Service;
//
//@Service
//public class CustomUserServiceDetails implements UserDetailsService {
//
//    private final ClientJpaRepo clientJpaRepo;
//    @Autowired
//    public CustomUserServiceDetails(ClientJpaRepo clientJpaRepo) {
//        this.clientJpaRepo = clientJpaRepo;
//    }
//
//
//
//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        User user =clientJpaRepo.findByUsername(username);
//        if(user==null) throw new UsernameNotFoundException("User not found");
//        return new CustomUserDetails(user);
//    }
//}
