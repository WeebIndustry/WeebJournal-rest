package com.weebindustry.weebjournal.services;

import com.weebindustry.weebjournal.exceptions.ResourceNotFoundException;
import com.weebindustry.weebjournal.models.Tag;
import com.weebindustry.weebjournal.repositories.PostRepository;
import com.weebindustry.weebjournal.repositories.TagRepository;
import com.weebindustry.weebjournal.repositories.UserRepository;
import com.weebindustry.weebjournal.util.HelperServiceOneToMany;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service("PostTagService")
public class PostTagServiceImpl {

}
