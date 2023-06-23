package com.vti.storyproject.modal.entity;

import lombok.Data;

import javax.persistence.*;
import java.awt.*;

//BẢNG THỂ LOẠI TRUYỆN :
@Entity // sử dụng  giao tiếp với Database
@Table(name = "NOVELS_CATEGORY")
@Data
public class NovelsCategory extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private int id;


    @Column(name = "CATEGORY", length = 254, unique = true, nullable = false)
    private String categoryName;


}
