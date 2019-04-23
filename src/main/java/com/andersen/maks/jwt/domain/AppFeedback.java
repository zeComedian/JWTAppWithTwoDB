package com.andersen.maks.jwt.domain;


import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.security.access.method.P;
import org.springframework.web.multipart.MultipartFile;


import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.nio.file.Path;
import java.security.SecureRandom;

@Document(collection = "appFeedback")
public class AppFeedback {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String id;

    private String email;

    private String text;

    private String pathToFile;
    // Upload files.
    private MultipartFile file;

    public AppFeedback(MultipartFile file) {
        this.file = file;
    }


    public AppFeedback(String email, String text, String pathToFile) {
        this.email = email;
        this.text = text;
        this.pathToFile = pathToFile;
    }

    public AppFeedback() {

    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getPathToFile(String string) {
        return pathToFile;
    }

    public void setPathToFile(String pathToFile) {
        this.pathToFile = pathToFile;
    }

    public MultipartFile getFile() {
        return file;
    }

    public void setFile(MultipartFile file) {
        this.file = file;
    }

    @Override
    public String toString() {
        return "AppFeedback{" +
                "email='" + email + '\'' +
                ", text='" + text + '\'' +
                ", pathToFile='" + pathToFile + '\'' +
                '}';
    }
}
