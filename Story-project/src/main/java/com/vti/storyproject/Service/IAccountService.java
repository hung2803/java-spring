package com.vti.storyproject.Service;

import com.vti.storyproject.modal.dto.AccountCreateDto;
import com.vti.storyproject.modal.dto.AccountUpdateDto;
import com.vti.storyproject.modal.entity.Account;

import java.util.List;

public interface IAccountService {

    // Bảng service thao tác các chức năng :

    List<Account> getAll();

// xác định kiểu dự liệu trả về là gì - khi tạo mới mình sẽ lấy kiểu dữ liệu truyền vào của nó
    Account create(AccountCreateDto createDto);

    Account getById(int id);

    Account update(AccountUpdateDto accountDto);

    boolean delete(int id);
}
