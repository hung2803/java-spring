package com.vti.storyproject.Controller;

import com.vti.storyproject.Service.iml.AccountService;
import com.vti.storyproject.modal.dto.AccountCreateDto;
import com.vti.storyproject.modal.dto.AccountUpdateDto;
import com.vti.storyproject.modal.entity.Account;
import com.vti.storyproject.modal.entity.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController // Dùng để tạo các  IPA
@RequestMapping("api/v1/account") //khai báo đường dẫn chung trong đối tượng :
@CrossOrigin("*") // để frond-end gọi tới back-end
public class AccountController {
//  gọi tới Service :
    @Autowired
    private AccountService accountService;

// các chức năng :

//    + chức năng IPA : gọi danh sách  account:
    @GetMapping("/get-all")  // tạo một IPA và đường dẫn ( " get-all") sẽ tự cộng chuỗi với đường dẫn chung
    public List<Account> getAll(){

        return accountService.getAll();
    }

// + chức năng IPA : tạo mới account
//     phương thức crate accoun là " POSTMapping
    @PostMapping("/create")
//     khai báo RequestBody để đặt biến truyền vào ở trong body
    public Account create(@RequestBody AccountCreateDto createDto){

        return accountService.create(createDto);
    }


    @GetMapping("/{id}")
    public Account getById(@PathVariable int id){

        return accountService.getById(id);
    }


    @PutMapping("/update")
    public Account update(@RequestBody AccountUpdateDto accountUpdateDto){

        return accountService.update(accountUpdateDto);
    }

    @DeleteMapping("/{id}")
//    @PreAuthorize(value = "hasAuthority('ADMIN')")
    public String delete(@PathVariable int id){
      if (accountService.delete(id)){
          return "Đã xóa thành công";
      }
        return " không tồn tại";
    }

}
