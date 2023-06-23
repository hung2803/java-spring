package com.vti.storyproject.Service.iml;

import com.vti.storyproject.Repository.AccountRepository;
import com.vti.storyproject.Repository.NovelsCategoryRepository;
import com.vti.storyproject.Repository.NovelsRepository;
import com.vti.storyproject.Service.INovelsCategoryService;
import com.vti.storyproject.config.exception.AppException;
import com.vti.storyproject.config.exception.ErrorResponseEnum;
import com.vti.storyproject.modal.dto.NovelsCategoryCreateDto;
import com.vti.storyproject.modal.dto.NovelsCategoryUpdateDto;
import com.vti.storyproject.modal.dto.NovelsCreateDto;
import com.vti.storyproject.modal.entity.Account;
import com.vti.storyproject.modal.entity.Novels;
import com.vti.storyproject.modal.entity.NovelsCategory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class NovelsCategoryService implements INovelsCategoryService {

    @Autowired
    private NovelsCategoryRepository novelscategoryRepository;

    @Autowired
    private NovelsRepository novelsRepository;



    // khởi tạo một constructor không tham số :
    public NovelsCategoryService(NovelsRepository novelsRepository, NovelsCategoryRepository categoryRepository) {

        this.novelscategoryRepository = categoryRepository;
        this.novelsRepository = novelsRepository;
    }

    @Override
    public List<NovelsCategory> getAll() {
        return novelscategoryRepository.findAll();
    }


    @Override
    public NovelsCategory create(NovelsCategoryCreateDto createDto) {
        Optional<NovelsCategory> categoryOptional = novelscategoryRepository.findByCategoryName(createDto.getCategoryName());
        if (!categoryOptional.isEmpty()){
            throw new AppException(ErrorResponseEnum.NOT_FOUND_NOVELS);

        }
        NovelsCategory category = new NovelsCategory();
        BeanUtils.copyProperties(createDto, category);
        return novelscategoryRepository.save(category);
    }

    public NovelsCategory update(NovelsCategoryUpdateDto categoryUpdateDto) {
        NovelsCategory category = getById(categoryUpdateDto.getId());
        Optional<NovelsCategory> categoryOptional = novelscategoryRepository.findByCategoryName(categoryUpdateDto.getCategoryName());
        if (categoryOptional.isEmpty() && categoryUpdateDto != null) {
            throw new AppException(ErrorResponseEnum.NOT_FOUND_UPDATE_NOVELS);
        }
            BeanUtils.copyProperties(categoryUpdateDto, category);
            return novelscategoryRepository.save(category);

    }

    @Override
    public NovelsCategory getById(int id) {
        Optional<NovelsCategory> categoryOptional = novelscategoryRepository.findById(id);
        if (categoryOptional.isPresent()) {
            NovelsCategory category = categoryOptional.get();
            return category;
        }
        return null;
    }

    @Override
    public boolean delete(int id) {
        NovelsCategory category = getById(id);
        if (category != null) {
            novelscategoryRepository.delete(category);
            return true;
        }
        return false;
    }


}
