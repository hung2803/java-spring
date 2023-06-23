package com.vti.storyproject.Repository;

import com.vti.storyproject.modal.entity.Novels;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface NovelsRepository extends JpaRepository<Novels, Integer>, JpaSpecificationExecutor<Novels> {
}
