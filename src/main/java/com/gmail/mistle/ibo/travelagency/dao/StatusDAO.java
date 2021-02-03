package com.gmail.mistle.ibo.travelagency.dao;

import com.gmail.mistle.ibo.travelagency.model.Status;

import java.util.Optional;

public interface StatusDAO {
    Optional<Status> findById(Long id);
}
