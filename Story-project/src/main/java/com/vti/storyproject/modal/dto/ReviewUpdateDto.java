package com.vti.storyproject.modal.dto;


import lombok.Data;

import java.util.Date;

@Data
public class ReviewUpdateDto {

    private int id;
    private String content;
    private Date datePost;
    private String rating;
    private int accountId;
    private int novelsId;
}
