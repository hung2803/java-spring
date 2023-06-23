package com.vti.storyproject.modal.dto;

import lombok.Data;

@Data
public class NovelsUpdateDto {

    private int id;
    private String novelsTitle;
    private String image;
    private int accountId;
    private String status;
}

