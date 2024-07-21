package com.shopdoors.service.product;

import com.shopdoors.dao.entity.product.molding.Baseboard;
import com.shopdoors.dao.repository.product.BaseboardRepository;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
@Getter
public class BaseboardService {
    private final BaseboardRepository baseboardRepository;

    public List<Baseboard> getAllBaseboards() {
        return baseboardRepository.findAll();
    }

    public Baseboard getBaseboardById(Long id) {
        return baseboardRepository.findById(id).orElseThrow();
    }
}
