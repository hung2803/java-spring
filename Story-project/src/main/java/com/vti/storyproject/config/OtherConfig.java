package com.vti.storyproject.config;

import com.vti.storyproject.Repository.AccountRepository;
import com.vti.storyproject.modal.entity.Account;
import com.vti.storyproject.modal.entity.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class OtherConfig {

    @Autowired
    private AccountRepository accountRepository;

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public void accountDefault(){
        if(!accountRepository.findByUsername("ADMIN").isPresent()){
            PasswordEncoder encoder = passwordEncoder();
            Account account = new Account();
            account.setUsername("ADMIN");
            account.setEmail("Admin@gmail.com");
            account.setAddress("Nam Định");
            account.setRole(Role.ADMIN);
            account.setPassword( encoder.encode("123456"));
            account.setPhoneNumber("0123456789");
            accountRepository.save(account);
        }
    }

}
