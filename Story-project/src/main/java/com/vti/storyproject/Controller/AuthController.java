package com.vti.storyproject.Controller;


import com.vti.storyproject.Repository.AccountRepository;
import com.vti.storyproject.config.exception.AppException;
import com.vti.storyproject.config.exception.ErrorResponseEnum;
import com.vti.storyproject.modal.dto.LoginDto;
import com.vti.storyproject.modal.dto.LoginRequest;
import com.vti.storyproject.modal.entity.Account;
import com.vti.storyproject.utils.JWTTokenUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

@RestController
@RequestMapping("api/v1/auth")
@CrossOrigin("*")
public class AuthController {

    @Autowired
    private JWTTokenUtils jwtTokenUtils ;

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    HttpServletRequest httpServletRequest;


    @GetMapping("/login-basic")
    public LoginDto loginDto(@RequestParam String Username, @RequestParam String password){
        // tìm ra được đối tượng account từ usernam
        Optional<Account> accountOptional = accountRepository.findByUsername(Username);
        if (accountOptional.isEmpty()){
            throw new AppException(ErrorResponseEnum.EXISTED_USERNAME);
        }
        Account account = accountOptional.get();

        //so sánh password
        boolean checkPassword = passwordEncoder.matches(password, account.getPassword());
                if(!checkPassword){
                    throw new AppException(ErrorResponseEnum.EXISTED_PASWORD_STRONG);
                }
        LoginDto loginDto = new LoginDto();
        BeanUtils.copyProperties(account, loginDto);
        return loginDto;
    }

    /**
     * Hàm login : cần set authenticated() cho api này
     * @param  :  dối tượng được dinh ra khi đã xác định người dùng
     */
    @PostMapping("/login-jwt")
    public LoginDto loginjwt(@RequestBody LoginRequest request){
        // tìm ra được đối tượng account từ usernam
        Optional<Account> accountOptional = accountRepository.findByUsername(request.getUsername());
        if (accountOptional.isEmpty()){
            throw new AppException(ErrorResponseEnum.EXISTED_USERNAME);
        }
        Account account = accountOptional.get();

        //so sánh password
        boolean checkPassword = passwordEncoder.matches(request.getPassword(), account.getPassword());
        if(!checkPassword){
            throw new AppException(ErrorResponseEnum.EXISTED_PASWORD_STRONG);
        }
        LoginDto loginDto = new LoginDto();
        BeanUtils.copyProperties(account, loginDto);
        loginDto.setUserAgent(httpServletRequest.getHeader("User-Agent"));

        String token = jwtTokenUtils.createAccessToken(loginDto);
        loginDto.setToken(token);
        return loginDto;
    }

}
