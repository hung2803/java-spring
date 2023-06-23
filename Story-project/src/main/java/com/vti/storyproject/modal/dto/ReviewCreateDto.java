package com.vti.storyproject.modal.dto;

import lombok.Data;

import java.util.Date;

@Data
public class ReviewCreateDto {

    private String content;
    private Date datePost;
    private String rating;
    private int accountId;
    private int novelsId;
}
