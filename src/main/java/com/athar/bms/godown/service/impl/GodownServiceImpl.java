package com.athar.bms.godown.service.impl;

import com.athar.bms.godown.dto.GodownRequest;
import com.athar.bms.godown.dto.GodownResponse;
import com.athar.bms.godown.entity.Godown;
import com.athar.bms.godown.repository.GodownRepository;
import com.athar.bms.godown.service.GodownService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GodownServiceImpl implements GodownService {

    private final GodownRepository godownRepository;

    @Override
    public GodownResponse createGodown(GodownRequest request) {

        if (godownRepository.existsByName(request.getName())) {
            throw new RuntimeException("Godown already exists");
        }

        Godown godown = Godown.builder()
                .name(request.getName())
                .location(request.getLocation())
                .description(request.getDescription())
                .build();

        Godown savedGodown = godownRepository.save(godown);

        return GodownResponse.builder()
                .id(savedGodown.getId())
                .name(savedGodown.getName())
                .location(savedGodown.getLocation())
                .description(savedGodown.getDescription())
                .isActive(savedGodown.getIsActive())
                .build();
    }

    @Override
    public List<GodownResponse> getAllGodowns() {

        return godownRepository.findAll()
                .stream()
                .map(godown -> GodownResponse.builder()
                        .id(godown.getId())
                        .name(godown.getName())
                        .location(godown.getLocation())
                        .description(godown.getDescription())
                        .isActive(godown.getIsActive())
                        .build())
                .toList();
    }

    @Override
    public GodownResponse getGodownById(Long id) {

        Godown godown = godownRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Godown not found"));

        return GodownResponse.builder()
                .id(godown.getId())
                .name(godown.getName())
                .location(godown.getLocation())
                .description(godown.getDescription())
                .isActive(godown.getIsActive())
                .build();
    }

    @Override
    public GodownResponse updateGodown(Long id, GodownRequest request) {

        Godown godown = godownRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Godown not found"));

        if (!godown.getName().equals(request.getName())
                && godownRepository.existsByName(request.getName())) {
            throw new RuntimeException("Godown already exists");
        }

        godown.setName(request.getName());
        godown.setLocation(request.getLocation());
        godown.setDescription(request.getDescription());

        Godown updatedGodown = godownRepository.save(godown);

        return GodownResponse.builder()
                .id(updatedGodown.getId())
                .name(updatedGodown.getName())
                .location(updatedGodown.getLocation())
                .description(updatedGodown.getDescription())
                .isActive(updatedGodown.getIsActive())
                .build();
    }

    @Override
    public void deleteGodown(Long id) {

        Godown godown = godownRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Godown not found"));

        godownRepository.delete(godown);
    }
}