package com.weebindustry.weebjournal.util;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.*;

public interface HelperService<C> {
    
    List<C> list();

    Page<C> findAll(Pageable pageable);

    C findById(Long id);

    C create(C type);

    C update(Long id,C type);

    void delete(Long id);
}