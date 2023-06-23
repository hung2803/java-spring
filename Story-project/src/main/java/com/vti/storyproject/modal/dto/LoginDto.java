package com.vti.storyproject.modal.dto;

import com.vti.storyproject.modal.entity.Role;
import lombok.Data;

@Data
public class LoginDto {
    private int Id;
    private String username;
    private Role role;
    private String fullName;

    private String userAgent; //  tên trình duyệt đang sử dụng
    private String token;
}
