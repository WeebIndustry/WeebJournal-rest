package com.weebindustry.weebjournal.services;

import com.weebindustry.weebjournal.exceptions.ResourceNotFoundException;
import com.weebindustry.weebjournal.models.Tag;
import com.weebindustry.weebjournal.repositories.TagRepository;
import com.weebindustry.weebjournal.util.HelperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TagServiceImpl implements HelperService<Tag> {

    private final TagRepository repo;

    @Autowired
    public TagServiceImpl(TagRepository repo) {
        this.repo = repo;
    }

    @Override
    public List<Tag> list() {
        return repo.findAll();
    }

    @Override
    public Page<Tag> findAll(Pageable pageable) {
        return repo.findAll(pageable);
    }

    @Override
    public Tag findById(Long id) {
        Optional<Tag> result = repo.findById(id);
        if (!result.isPresent()) {
            ResponseEntity.badRequest().build();
            throw new ResourceNotFoundException("User not found :: " + id);
        }
        return result.get();
    }

    @Override
    public Tag create(Tag type) {
        return repo.save(type);
    }

    @Override
    public Tag update(Long id, Tag type) {
        if (!repo.findById(id).isPresent()) {
            ResponseEntity.badRequest().build();
            throw new ResourceNotFoundException("User not found :: " + id);
        }
        return repo.save(type);
    }

    @Override
    public void delete(Long id) {
        if (!repo.findById(id).isPresent()) {
            ResponseEntity.badRequest().build();
            throw new ResourceNotFoundException("User not found :: " + id);
        }

        repo.findById(id);
    }
}
