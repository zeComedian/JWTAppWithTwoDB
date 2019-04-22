package com.andersen.maks.jwt.domain;


import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.web.multipart.MultipartFile;


import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Document
public class AppFeedback {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String id;

    // Upload files.
    private MultipartFile file;

    public AppFeedback(MultipartFile file) {
        this.file = file;
    }

    public AppFeedback() {

    }

    public MultipartFile getFile() {
        return file;
    }

    public void setFile(MultipartFile file) {
        this.file = file;
    }
}
