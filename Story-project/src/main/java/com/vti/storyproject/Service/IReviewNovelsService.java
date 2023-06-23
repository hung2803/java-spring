package com.vti.storyproject.Service;

import com.vti.storyproject.modal.dto.NovelsCategoryCreateDto;
import com.vti.storyproject.modal.dto.NovelsUpdateDto;
import com.vti.storyproject.modal.dto.ReviewCreateDto;
import com.vti.storyproject.modal.dto.ReviewUpdateDto;
import com.vti.storyproject.modal.entity.Novels;
import com.vti.storyproject.modal.entity.NovelsCategory;
import com.vti.storyproject.modal.entity.ReviewsNovels;

import java.util.List;

public interface IReviewNovelsService {

    List<ReviewsNovels> getAll();

    ReviewsNovels create(ReviewCreateDto createDto);

    ReviewsNovels update(ReviewUpdateDto updateDto);

    ReviewsNovels getById(int id);

    ReviewsNovels getNovelById(int account, int novelsId);

    boolean delete(int id);
}
