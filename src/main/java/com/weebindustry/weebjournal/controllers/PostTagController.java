package com.weebindustry.weebjournal.controllers;

import com.weebindustry.weebjournal.exceptions.ParentEntityNotFound;
import com.weebindustry.weebjournal.models.Post;
import com.weebindustry.weebjournal.models.Tag;
import com.weebindustry.weebjournal.util.HelperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/")
public class PostTagController {

    @Autowired
    @Qualifier("PostService")
    private HelperService<Post> postService;

    @Autowired
    @Qualifier("TagService")
    private HelperService<Tag> tagService;

}
