package com.vti.storyproject.Repository.Speciflcation;

import com.vti.storyproject.modal.dto.SearchNovels;
import com.vti.storyproject.modal.entity.Novels;
import org.springframework.data.jpa.domain.Specification;

public class NovelsSpecification {
    public static Specification<Novels> buildCondition(SearchNovels searchNovels){
        return   Specification.where(buildConditionName(searchNovels));

    }

    public static Specification<Novels> buildConditionName(SearchNovels searchNovels){
        if (searchNovels.getNovelsTitle() != null && !"".equals(searchNovels.getNovelsTitle())){
            // Tạo điều kiện tìm kiếm với name
            return (root, query, cri) -> {
                // root: Chọn cột, field, để tìm kiếm (giá trị là thuộc tính trong java)
                // cri: Khai báo loại so sánh dữ liệu. ( lớn hơn, nhỏ hơn, equal, like,.... )
                return cri.like(root.get("novelsTitle"), "%" + searchNovels.getNovelsTitle() + "%");
            };
        } else {
            return null;
        }
    }
}

