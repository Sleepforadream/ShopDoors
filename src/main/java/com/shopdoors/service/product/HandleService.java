package com.shopdoors.service.product;

import com.shopdoors.dao.entity.product.furniture.Handle;
import com.shopdoors.dao.repository.product.HandleRepository;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
@Getter
public class HandleService {
    private final HandleRepository handleRepository;

    public List<Handle> getAllHandles() {
        return handleRepository.findAll();
    }

    public Handle getHandleById(Long id) {
        return handleRepository.findById(id).orElseThrow();
    }
}
