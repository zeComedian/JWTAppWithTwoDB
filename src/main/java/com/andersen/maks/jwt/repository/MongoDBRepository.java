package com.andersen.maks.jwt.repository;

import com.andersen.maks.jwt.domain.AppFeedback;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.web.multipart.MultipartFile;

public interface MongoDBRepository extends MongoRepository<AppFeedback, String> {
    void save(MultipartFile file);
}
