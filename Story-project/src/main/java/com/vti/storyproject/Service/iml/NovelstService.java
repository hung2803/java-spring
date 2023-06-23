package com.vti.storyproject.Service.iml;

import com.vti.storyproject.Repository.AccountRepository;
import com.vti.storyproject.Repository.NovelsRepository;
import com.vti.storyproject.Repository.ReviewNovelsRepository;
import com.vti.storyproject.Repository.Speciflcation.NovelsSpecification;
import com.vti.storyproject.Service.INovelsService;
import com.vti.storyproject.modal.dto.NovelsCreateDto;
import com.vti.storyproject.modal.dto.NovelsUpdateDto;
import com.vti.storyproject.modal.dto.SearchNovels;
import com.vti.storyproject.modal.entity.Account;
import com.vti.storyproject.modal.entity.Novels;
import com.vti.storyproject.modal.entity.ReviewsNovels;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class NovelstService implements INovelsService {
    @Autowired
    private NovelsRepository novelsRepository;
    @Autowired
    private ReviewNovelsRepository reviewNovelsRepository;

    public NovelstService(NovelsRepository novelsRepository) {

        this.novelsRepository = novelsRepository;
    }

    @Override
    public List<Novels> getAll() {

        return novelsRepository.findAll();
    }

    @Override
    public Page<Novels> search(SearchNovels searchNovels) {
        PageRequest pageRequest = null;
        if ("DESC".equals(searchNovels.getSortType())) {
            // giá trị page mà thư viện mong muốn để vào trong đầu tiên : 0
            // giá trị mình mong muốn để lấy trang đầu tiên :1-1
            pageRequest = PageRequest.of(searchNovels.getPage() - 1, searchNovels.getSize(), Sort.by(searchNovels.getSortField()).descending());
        } else {
            pageRequest = PageRequest.of(searchNovels.getPage() - 1, searchNovels.getSize(), Sort.by(searchNovels.getSortField()).ascending());
        }
        checkRatting();
        Specification<Novels> condition = NovelsSpecification.buildCondition(searchNovels);
        return novelsRepository.findAll(condition, pageRequest);
    }

    public void checkRatting(){
        List<Novels> novelsList = getAll();
        Novels novels = new Novels();
        for (int i = 0; i < novelsList.size(); i++){
            novels = getById(novelsList.get(i).getId());
            List<ReviewsNovels> reviewsNovels = reviewNovelsRepository.findReviewsNovelsById(novelsList.get(i).getId());
            if(reviewsNovels != null){
                int total = 0;
                for (int j = 0; j < reviewsNovels.size(); j++){
                    if (reviewsNovels.get(j).getRating() != ""){

                        total += Integer.parseInt(reviewsNovels.get(j).getRating());
                    }
                }
                novels.setTotalRating((double) (total / reviewsNovels.size()));
            }
        }
        novelsRepository.save(novels);
    }

    @Override
    public Novels create(NovelsCreateDto createDto) {

        Novels novels = new Novels();
        novels.setNovelsTitle(createDto.getNovelsTitle());
        novels.setImage(createDto.getImage());
        novels.setAuthor(createDto.getAuthor());
        novels.setStatus(createDto.getStatus());
        BeanUtils.copyProperties(createDto, novels);
        return novelsRepository.save(novels);

    }

    @Override
    public Novels getById(int id) {
        Optional<Novels> novelsOptional = novelsRepository.findById(id);
        if (novelsOptional.isPresent()) {
            Novels novels = novelsOptional.get();
            return novels;
        }
        return null;
    }

    @Override
    public Novels update(NovelsUpdateDto updateDto) {
        Novels novels = getById(updateDto.getId());
        if (novels != null) {
            BeanUtils.copyProperties(updateDto, novels);
            return novelsRepository.save(novels);
        }
        return null;
    }

    @Override
    public boolean delete(int id) {
        Novels novels = getById(id);
        if (novels != null) {
            novelsRepository.delete(novels);
            return true;
        }
        return false;
    }
}
