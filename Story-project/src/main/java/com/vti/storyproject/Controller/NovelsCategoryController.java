package com.vti.storyproject.Controller;


import com.vti.storyproject.Service.iml.AccountService;
import com.vti.storyproject.Service.iml.NovelsCategoryService;
import com.vti.storyproject.modal.dto.AccountCreateDto;
import com.vti.storyproject.modal.dto.AccountUpdateDto;
import com.vti.storyproject.modal.dto.NovelsCategoryCreateDto;
import com.vti.storyproject.modal.dto.NovelsCategoryUpdateDto;
import com.vti.storyproject.modal.entity.Account;
import com.vti.storyproject.modal.entity.NovelsCategory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController // Dùng để tạo các  IPA
@RequestMapping("api/v1/category") //khai báo đường dẫn chung trong đối tượng :
@CrossOrigin("*") // để frond-end gọi tới back-end
public class NovelsCategoryController {

    //  gọi tới Service :
    @Autowired
    private NovelsCategoryService novelsCategoryService;

// các chức năng :

    //    + chức năng IPA : gọi danh sách  account:
    @GetMapping("/get-all")  // tạo một IPA và đường dẫn ( " get-all") sẽ tự cộng chuỗi với đường dẫn chung
    public List<NovelsCategory> getAll(){

        return novelsCategoryService.getAll();
    }

    // + chức năng IPA : tạo mới account
//     phương thức crate accoun là " POSTMapping
    @PostMapping("/create")
//     khai báo RequestBody để đặt biến truyền vào ở trong body
    public NovelsCategory create(@RequestBody NovelsCategoryCreateDto createDto){

        return novelsCategoryService.create(createDto);
    }


    @GetMapping("/{id}")
    public NovelsCategory getById(@PathVariable int id){

        return novelsCategoryService.getById(id);
    }


    @PutMapping("/update")
    public NovelsCategory update(@RequestBody NovelsCategoryUpdateDto UpdateDto){

        return novelsCategoryService.update(UpdateDto);
    }


}
