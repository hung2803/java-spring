package com.vti.storyproject.Controller;

import com.vti.storyproject.Service.iml.NovelstService;
import com.vti.storyproject.modal.dto.NovelsCreateDto;
import com.vti.storyproject.modal.dto.NovelsUpdateDto;
import com.vti.storyproject.modal.dto.SearchNovels;
import com.vti.storyproject.modal.entity.Novels;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController // Dùng để tạo các  IPA
@RequestMapping("api/v1/novels")
@CrossOrigin("*")
public class NovelsController {

    @Autowired
    private NovelstService novelstService;


    @GetMapping("/get-all")
    public List<Novels> getAll(){

        return novelstService.getAll();
    }


    @PostMapping("/create")
    public Novels create(@RequestBody NovelsCreateDto createDto){

        return novelstService.create(createDto);
    }

    //Search :
    @PostMapping("/search")
    public Page<Novels> search(@RequestBody SearchNovels searchNovels){

        return novelstService.search(searchNovels);
    }

    @GetMapping("/{id}")
    public Novels getById(@PathVariable int id){

        return novelstService.getById(id);
    }


    @PutMapping("/update")
    public Novels update(@RequestBody NovelsUpdateDto updateDto){

        return novelstService.update(updateDto);
    }

    @DeleteMapping("delete/{id}")
//    @PreAuthorize(value = "hasAuthority('ADMIN')")
    public String delete(@PathVariable int id){
        if (novelstService.delete(id)){
            return "Đã xóa thành công";
        }
        return " không tồn tại";
    }
}
