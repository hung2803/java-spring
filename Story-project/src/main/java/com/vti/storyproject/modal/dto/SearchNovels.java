package com.vti.storyproject.modal.dto;

import lombok.Data;

@Data
public class SearchNovels extends BaseRequest{

    private String novelsTitle;
    private String categoryName;
    private String author;
    private String status;
}
