package com.weebindustry.weebjournal.util;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface HelperServiceOneToMany<C> {
    
    Page<C> getManyByOne(Long id, Pageable pageable);

    C create(Long id, C type);

    C update(Long idOne, Long idMany, C type);

    void delete(Long idOne, Long idMany);
    
}