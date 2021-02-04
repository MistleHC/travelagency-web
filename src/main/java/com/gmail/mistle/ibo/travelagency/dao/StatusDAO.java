package com.gmail.mistle.ibo.travelagency.dao;

import com.gmail.mistle.ibo.travelagency.model.Status;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface StatusDAO extends CrudRepository<Status, Long> {
    Optional<Status> findById(Long id);
}
