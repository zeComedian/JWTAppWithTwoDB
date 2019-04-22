package com.andersen.maks.jwt.web;

import com.andersen.maks.jwt.domain.AppFeedback;
import com.andersen.maks.jwt.domain.AppUser;
import com.andersen.maks.jwt.repository.AppUserRepository;
import com.andersen.maks.jwt.repository.MongoDBRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;


@RestController
@RequestMapping(value = "/api")
public class AppUserRestController {

    //Save the uploaded file to this folder
    private static String UPLOADED_FOLDER = "D://temp//";

    @Autowired
    private MongoDBRepository mrep;


    @Autowired
    private AppUserRepository appUserRepository;


    private static String UPLOAD_DIR = System.getProperty("user.home") + "/test";


    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @RequestMapping(value = "/users", method = RequestMethod.GET)
    public List<AppUser> users() {
        return appUserRepository.findAll();
    }


    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @RequestMapping(value = "/users/{id}", method = RequestMethod.GET)
    public ResponseEntity<AppUser> userById(@PathVariable Long id) {
        AppUser appUser = appUserRepository.findOne(id);
        if (appUser == null) {
            return new ResponseEntity<AppUser>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<AppUser>(appUser, HttpStatus.OK);
        }
    }


    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @RequestMapping(value = "/users/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<AppUser> deleteUser(@PathVariable Long id) {
        AppUser appUser = appUserRepository.findOne(id);
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String loggedUsername = auth.getName();
        if (appUser == null) {
            return new ResponseEntity<AppUser>(HttpStatus.NO_CONTENT);
        } else if (appUser.getUsername().equalsIgnoreCase(loggedUsername)) {
            throw new RuntimeException("You cannot delete your account");
        } else {
            appUserRepository.delete(appUser);
            return new ResponseEntity<AppUser>(appUser, HttpStatus.OK);
        }

    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @RequestMapping(value = "/users", method = RequestMethod.POST)
    public ResponseEntity<AppUser> createUser(@RequestBody AppUser appUser) {
        if (appUserRepository.findOneByUsername(appUser.getUsername()) != null) {
            throw new RuntimeException("Username already exist");
        }
        return new ResponseEntity<AppUser>(appUserRepository.save(appUser), HttpStatus.CREATED);
    }


//    @RequestMapping(value = "/upload", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE) // //new annotation since 4.3
//    public void upload(@RequestBody AppFeedback appFeedback) {
//        AppFeedback appFeedback1 = new AppFeedback();
//        appFeedback1.setText(appFeedback.getText());
//        appFeedback1.setFileDatas(appFeedback.getFile());
//
//        mrep.save(appFeedback1);
//    }

    @RequestMapping(value="/upload", method=RequestMethod.POST)
    public void handleFileUpload(@RequestParam(value="file") MultipartFile file){
        if (file.isEmpty()) {
            System.out.println("Empty file");
        }
        try {

            // Get the file and save it somewhere
            byte[] bytes = file.getBytes();
            Path path = Paths.get(UPLOADED_FOLDER + file.getOriginalFilename());
            Files.write(path, bytes);

            System.out.println("message" +
                    "You successfully uploaded '" + file.getOriginalFilename() + "'");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @RequestMapping(value = "/users", method = RequestMethod.PUT)
    public AppUser updateUser(@RequestBody AppUser appUser) {
        if (appUserRepository.findOneByUsername(appUser.getUsername()) != null
                && appUserRepository.findOneByUsername(appUser.getUsername()).getId() != appUser.getId()) {
            throw new RuntimeException("Username already exist");
        }
        return appUserRepository.save(appUser);
    }

}
