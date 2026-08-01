package com.athar.bms.godown.service;

import com.athar.bms.godown.dto.GodownRequest;
import com.athar.bms.godown.dto.GodownResponse;

import java.util.List;

public interface GodownService {

    GodownResponse createGodown(GodownRequest request);

    List<GodownResponse> getAllGodowns();

    GodownResponse getGodownById(Long id);

    GodownResponse updateGodown(Long id, GodownRequest request);

    void deleteGodown(Long id);

}