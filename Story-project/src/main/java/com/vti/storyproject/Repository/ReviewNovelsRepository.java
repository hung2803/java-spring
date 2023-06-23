package com.vti.storyproject.Repository;

import com.vti.storyproject.modal.entity.ReviewsNovels;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewNovelsRepository extends JpaRepository<ReviewsNovels, Integer> {
    @Query(value = "SELECT * FROM review_novels rw WHERE  rw.account_id = :account AND rw.novels_id = :novelsId", nativeQuery = true)
    ReviewsNovels findReviewsNovelsById(@Param("account") int account,@Param("novelsId") int novelsId);

    @Query(value = "SELECT * FROM review_novels rw WHERE rw.novels_id = :novelsId", nativeQuery = true)
    List<ReviewsNovels> findReviewsNovelsById(@Param("novelsId") int novelsId);
}
