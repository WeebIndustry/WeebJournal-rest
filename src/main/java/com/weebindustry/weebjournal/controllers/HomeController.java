package com.weebindustry.weebjournal.controllers;

import com.weebindustry.weebjournal.models.URL;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class HomeController {

    public static final String BASE_URL = "https://weebjournal-rest.herokuapp.com";

    public List<URL> allGetMethodURLs() {
        List<URL> urls = new ArrayList<URL>(){
            {
                new URL("get all users", BASE_URL + "/api/users");
                new URL("get user by id", BASE_URL + "/api/users/{id}");
                new URL("get all posts", BASE_URL + "/api/posts");
                new URL("get all posts by user's id", BASE_URL + "/api/users/{userId}/posts");
                new URL("get all tags", BASE_URL + "/api/tags");
                new URL("get tag by id", BASE_URL + "/api/tags/{id}");
                new URL("get all comments by post's id", BASE_URL + "/api/posts/{id}/comments");
            }
        };
        return urls;
    }
    @GetMapping(value = "")
    @ResponseBody
    public String home() {

        return "This is the WeebJournal";
    }
}
