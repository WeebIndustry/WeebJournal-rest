package com.weebindustry.weebjournal.controllers;

import com.weebindustry.weebjournal.dtos.tags.TagCreatableDTO;
import com.weebindustry.weebjournal.dtos.tags.TagUpdatableDTO;
import com.weebindustry.weebjournal.dtos.users.UserCreatableDTO;
import com.weebindustry.weebjournal.dtos.users.UserUpdatableDTO;
import com.weebindustry.weebjournal.exceptions.RequestParamValueNotDefinedException;
import com.weebindustry.weebjournal.exceptions.ResourceNotFoundException;
import com.weebindustry.weebjournal.models.Tag;
import com.weebindustry.weebjournal.models.User;
import com.weebindustry.weebjournal.util.DTO;
import com.weebindustry.weebjournal.util.HelperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/tags/")
public class TagController {

    private final HelperService<Tag> service;

    public TagController(HelperService<Tag> service) {
        this.service = service;
    }

    @GetMapping("/")
    public ResponseEntity<?> getAllTags(Pageable pageable, @RequestParam(required = false, defaultValue = "false") String hasPagable) {
        if (hasPagable.equals("true")) {
            return ResponseEntity.ok(service.findAll(pageable));
        }
        else if(hasPagable.equals("false")) {
            return ResponseEntity.ok(service.list());
        }
        else {
            throw new RequestParamValueNotDefinedException("hasPagable", hasPagable);
        }

    }

    @GetMapping("/{id}")
    public ResponseEntity<Tag> findById(@PathVariable(value = "id") Long id) throws ResourceNotFoundException {
        return ResponseEntity.ok(service.findById(id));
    }


    @PostMapping("/")
    public ResponseEntity<Tag> createTag(@Valid @RequestBody @DTO(TagCreatableDTO.class) Tag tag) {
        return ResponseEntity.ok(service.create(tag));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Tag> updateTag(@PathVariable Long id, @Valid @RequestBody @DTO(TagUpdatableDTO.class) Tag tag) {
        return ResponseEntity.ok(service.update(id, tag));
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteTag(@PathVariable(value = "id") Long id) {
        service.delete(id);
        return ResponseEntity.ok().build();
    }
}
