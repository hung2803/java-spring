package com.vti.storyproject.Controller;


import com.vti.storyproject.Service.iml.NovelstService;
import com.vti.storyproject.Service.iml.ReviewNovelsService;
import com.vti.storyproject.modal.dto.NovelsCreateDto;
import com.vti.storyproject.modal.dto.NovelsUpdateDto;
import com.vti.storyproject.modal.dto.ReviewCreateDto;
import com.vti.storyproject.modal.dto.ReviewUpdateDto;
import com.vti.storyproject.modal.entity.Novels;
import com.vti.storyproject.modal.entity.ReviewsNovels;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController // Dùng để tạo các  IPA
@RequestMapping("api/v1/review") //khai báo đường dẫn chung trong đối tượng :
@CrossOrigin("*") // để frond-end gọi tới back-end
public class ReviewsNovelsController {

    @Autowired
    private ReviewNovelsService reviewNovelsService;


    @GetMapping("/get-all")
    public List<ReviewsNovels> getAll(){

        return reviewNovelsService.getAll();
    }


    @PostMapping("/create")
    public ReviewsNovels create(@RequestBody ReviewCreateDto createDto){

        return reviewNovelsService.create(createDto);
    }


    @GetMapping("/{id}")
    public ReviewsNovels getById(@PathVariable int id){

        return reviewNovelsService.getById(id);
    }

    @PostMapping("/{account}/{novelsId}")
    public ReviewsNovels getNovelsById(@PathVariable int account,@PathVariable int novelsId){

        return reviewNovelsService.getNovelById(account, novelsId);
    }


    @PutMapping("/update")
    public ReviewsNovels update(@RequestBody ReviewUpdateDto updateDto){

        return reviewNovelsService.update(updateDto);
    }

    @DeleteMapping("/{id}")
//    @PreAuthorize(value = "hasAuthority('ADMIN')")
    public String delete(@PathVariable int id){
        if (reviewNovelsService.delete(id)){
            return "Đã xóa thành công";
        }
        return " không tồn tại";
    }
}
