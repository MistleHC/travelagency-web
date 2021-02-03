package com.gmail.mistle.ibo.travelagency.service.impl;

import com.gmail.mistle.ibo.travelagency.dao.StatusDAO;
import com.gmail.mistle.ibo.travelagency.exceptions.NotFoundException;
import com.gmail.mistle.ibo.travelagency.model.Status;
import com.gmail.mistle.ibo.travelagency.service.StatusService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@Slf4j
@AllArgsConstructor
public class StatusServiceImpl implements StatusService {
    private final StatusDAO statusDAO;

    @Override
    public Status findById(Long id) {
        return statusDAO
                .findById(id)
                .orElseThrow(NotFoundException::new);
    }
}
