package com.vti.storyproject.modal.dto;

import lombok.Data;

@Data
public class BaseRequest {
    protected int page;
    private int size;
    private String sortField;
    private String sortType; // ASC, DISC xắp xếp thứ tự tăng dần hoặc giảm dần

}
