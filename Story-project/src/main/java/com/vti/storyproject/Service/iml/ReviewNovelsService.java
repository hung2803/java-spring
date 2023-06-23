package com.vti.storyproject.Service.iml;

import com.vti.storyproject.Repository.AccountRepository;
import com.vti.storyproject.Repository.NovelsRepository;
import com.vti.storyproject.Repository.ReviewNovelsRepository;
import com.vti.storyproject.Service.IReviewNovelsService;
import com.vti.storyproject.modal.dto.ReviewCreateDto;
import com.vti.storyproject.modal.dto.ReviewUpdateDto;
import com.vti.storyproject.modal.entity.Account;
import com.vti.storyproject.modal.entity.Novels;
import com.vti.storyproject.modal.entity.ReviewsNovels;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReviewNovelsService implements IReviewNovelsService {


    // gọi tới Repository
    @Autowired
    private ReviewNovelsRepository reviewNovelsRepository;

    @Autowired // đánh dấu đây cũng là một bean
    private AccountRepository accountRepository;

    @Autowired
    private NovelsRepository novelsRepository;

    // khởi tạo một constructor không tham số :
    public ReviewNovelsService(ReviewNovelsRepository reviewNovelsRepository) {

        this.reviewNovelsRepository = reviewNovelsRepository;
    }

    @Override
    public List<ReviewsNovels> getAll() {
        return reviewNovelsRepository.findAll();
    }

    @Override
    public ReviewsNovels create(ReviewCreateDto createDto) {

        ReviewsNovels reviewsNovels = new ReviewsNovels();
        Optional<Account> accountOptional = accountRepository.findById(createDto.getAccountId());
        Optional<Novels> novelsOptional = novelsRepository.findById(createDto.getNovelsId());
        if (novelsOptional.isPresent()) {
            reviewsNovels.setNovelsId(novelsOptional.get());
        }
        if (accountOptional.isPresent()) {
            reviewsNovels.setAccount(accountOptional.get());
        }
        reviewsNovels.setDatePost(createDto.getDatePost());
        reviewsNovels.setContent(createDto.getContent());
        reviewsNovels.setRating(createDto.getRating());
        return reviewNovelsRepository.save(reviewsNovels);

    }

    @Override
    public ReviewsNovels update(ReviewUpdateDto updateDto) {
        ReviewsNovels reviewsNovels = getById(updateDto.getId());
        if (reviewsNovels != null) {
            BeanUtils.copyProperties(updateDto, reviewsNovels);
            return reviewNovelsRepository.save(reviewsNovels);
        }
        return null;
    }

    @Override
    public ReviewsNovels getById(int id) {
        Optional<ReviewsNovels> reviewsNovelsOptional = reviewNovelsRepository.findById(id);
        if (reviewsNovelsOptional.isPresent()) {
            ReviewsNovels reviewsNovels = reviewsNovelsOptional.get();
            return reviewsNovels;
        }
        return null;
    }

    @Override
    public ReviewsNovels getNovelById(int account, int novelsId) {
        ReviewsNovels reviewsNovels = reviewNovelsRepository.findReviewsNovelsById(account, novelsId);
        return reviewsNovels;
    }

    @Override
    public boolean delete(int id) {
        ReviewsNovels reviewsNovels  = getById(id);
        if (reviewsNovels != null) {
            reviewNovelsRepository.delete(reviewsNovels);
            return true;
        }
        return false;
    }
}
