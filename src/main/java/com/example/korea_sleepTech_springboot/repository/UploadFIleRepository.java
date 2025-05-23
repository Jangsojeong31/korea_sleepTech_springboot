package com.example.korea_sleepTech_springboot.repository;

import com.example.korea_sleepTech_springboot.entity.UploadFile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface UploadFIleRepository extends JpaRepository<UploadFile, Long> {
    List<UploadFile> findByTargetTypeAndTargetId(String targetType, Long targetId);

}
