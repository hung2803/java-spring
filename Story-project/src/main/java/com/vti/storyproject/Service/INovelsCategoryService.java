package com.vti.storyproject.Service;

import com.vti.storyproject.modal.dto.NovelsCategoryCreateDto;
import com.vti.storyproject.modal.dto.NovelsCreateDto;
import com.vti.storyproject.modal.entity.Novels;
import com.vti.storyproject.modal.entity.NovelsCategory;

import java.util.List;

public interface INovelsCategoryService {

    List<NovelsCategory> getAll();

    NovelsCategory create(NovelsCategoryCreateDto createDto);

    NovelsCategory getById(int id);

    boolean delete(int id);
}
