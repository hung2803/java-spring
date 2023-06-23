package com.vti.storyproject.Service;

import com.vti.storyproject.modal.dto.*;
import com.vti.storyproject.modal.entity.Account;
import com.vti.storyproject.modal.entity.Novels;
import org.springframework.data.domain.Page;

import java.util.List;

public interface INovelsService {


    List<Novels> getAll();

    Page<Novels> search(SearchNovels searchNovels);

    Novels create(NovelsCreateDto createDto);

    Novels getById(int id);

    Novels update(NovelsUpdateDto updateDto);

    boolean delete(int id);
}
