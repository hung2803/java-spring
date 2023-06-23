package com.vti.storyproject.modal.entity;


import lombok.Data;

import javax.persistence.*;
import java.util.Date;

//BẢNG REVIEW TRUYỆN :
@Entity // sử dụng  giao tiếp với Database
@Table(name = "REVIEW_NOVELS")
@Data
public class ReviewsNovels extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private int id;

    @Column(name = "CONTENT", length = 254, nullable = false)
    private String content;

    @Column(name = "DATE_POST", length = 254, nullable = false)
    private Date datePost;

    @Column(name = "RATING", length = 100, nullable = false)
    private String rating;

//    cột này quan hệ : 1 account có thể tạo nhiều truyện
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "ACCOUNT_ID")  //Tên cột của khóa ngoại trong DB
    private Account account;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "NOVELS_ID")  //Tên cột của khóa ngoại trong DB
    private Novels novelsId;
}
