package com.athar.bms.godown.controller;

import com.athar.bms.godown.dto.GodownRequest;
import com.athar.bms.godown.dto.GodownResponse;
import com.athar.bms.godown.service.GodownService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/godowns")
@RequiredArgsConstructor
public class GodownController {

    private final GodownService godownService;

    @PostMapping
    public GodownResponse createGodown(
            @Valid @RequestBody GodownRequest request) {

        return godownService.createGodown(request);
    }

    @GetMapping
    public List<GodownResponse> getAllGodowns() {

        return godownService.getAllGodowns();
    }

    @GetMapping("/{id}")
    public GodownResponse getGodownById(
            @PathVariable Long id) {

        return godownService.getGodownById(id);
    }

    @PutMapping("/{id}")
    public GodownResponse updateGodown(
            @PathVariable Long id,
            @Valid @RequestBody GodownRequest request) {

        return godownService.updateGodown(id, request);
    }

    @DeleteMapping("/{id}")
    public void deleteGodown(
            @PathVariable Long id) {

        godownService.deleteGodown(id);
    }
}