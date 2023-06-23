package com.vti.storyproject.modal.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import java.util.Date;

@Data
@MappedSuperclass // đế đánh dấu clas này cũng là một phần trong các clas khác

// BẢNG CHUNG CỦA CÁC BẢNG KHÁC :
public class BaseEntity {
    @Column(name = "CREATE_DATE")
//    Protected  ngoài packit mình vẫn gọi được khi kế thừa.
    protected Date createDate;

    @Column(name = "CREATE_BY")
    protected String createBy;

    @Column(name = "UPDATE_DATE")
    protected Date UpdateDate;

    @Column(name = "UPDATE_BY")
    protected String updateCBy;

    /**
     * Hàm này gọi khi entity được thêm mới
     */
    @PrePersist
    public void onPrePersist() {
        this.createDate = new Date();
        this.createBy = "ADMIN Create";
    }

    /**
     *    Hàm này gọi khi entity được update
     */
    @PreUpdate
    public void onPreUpdate() {
        this.UpdateDate = new Date();
//        Fix cứng người tạo
        this.updateCBy = "ADMIN update";
    }
}
