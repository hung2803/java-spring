package com.vti.storyproject.modal.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;


//BẢNG TRUYỆN :
@Entity // sử dụng  giao tiếp với Database
@Table(name = "NOVELS")
@Data
public class Novels extends BaseEntity {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private int id;

    @Column(name = "NOVELS_TITLE", length = 200, unique = true, nullable = false)
    private String novelsTitle;

    @Column(name = "IMAGE", unique = true, nullable = false)
    private String image;

    @Column(name = "CATEGORY", nullable = false)
    private String categoryName;

    @Column(name = "AUTHOR", length = 200, nullable = false)  //Tên cột của khóa ngoại trong DB
    private String author;


    @Column(name = "STATUS", length = 200)
    private String status;

    @Column(name = "TOTALRATING", length = 10)
    private double totalRating;


}
