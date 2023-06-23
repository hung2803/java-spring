package com.vti.storyproject.modal.dto;

import com.vti.storyproject.modal.entity.Role;
import lombok.Data;

import java.util.Date;

// Dto này chứa những thuộc tính mình muốn thêm vào Account  khi tạo mới
// @Data:trong thư viện loombook thay cho cách gọi thường getter-setter
@Data
public class AccountCreateDto {

    private String username;
    private String password;
    private Date dateOfBirth;
    private Role role;
    private String address;
    private String fullName;
    private String phoneNumber;
    private String email;
    private String information;
}
