package com.vti.storyproject.config.exception;

public enum ErrorResponseEnum {
    EXISTED_USERNAME(400, "Username Không tồn tại"),
    NOT_FOUND_ACCOUNT(404, "Không tìm thấy account"),
    NOT_FOUND_NOVELS(500, "Truyện Đã tồn tại"),
    NOT_FOUND_REVIEW(404, "Không tìm thấy Review truyện"),
    NOT_FOUND_UPDATE_NOVELS(404, "tên đã tồn tại hoặc id sai"),

    EXISTED_PASWORD_STRONG(400, "password không tồn tại tồn tại");

    public final int status;
    public final String massage;
    ErrorResponseEnum(int status, String message) {
        this.status = status;
        this.massage = message;
    }
}
