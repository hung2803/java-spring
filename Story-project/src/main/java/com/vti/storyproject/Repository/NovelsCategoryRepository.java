package com.vti.storyproject.Repository;

import com.vti.storyproject.modal.entity.NovelsCategory;
import lombok.Data;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface NovelsCategoryRepository extends JpaRepository<NovelsCategory, Integer> {
    Optional<NovelsCategory> findByCategoryName(String categoryName);
}
