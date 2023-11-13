package com.tanveer.financialindicator.controller;

import com.tanveer.financialindicator.Dto.ApiResponse;
import com.tanveer.financialindicator.Exception.CustomException;
import com.tanveer.financialindicator.model.FinancialData;
import com.tanveer.financialindicator.service.FinancialServiceImpl;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/data")
public class FinancialController {

    private final FinancialServiceImpl financialService;

    @PostMapping("/save")
    public ResponseEntity<ApiResponse> saveData(@Valid @RequestBody FinancialData financialData){
        return ResponseEntity.ok()
                .body(ApiResponse.builder()
                        .success("SUCCESS")
                        .message("Successfully save data")
                        .data(financialService.saveFinancialData(financialData))
                        .build());
    }

    @PutMapping("/{userId}")
    public ResponseEntity<ApiResponse> updateData(@Valid @RequestBody FinancialData financialData,
                                                  @PathVariable Integer userId) throws CustomException {
        return ResponseEntity.ok()
                .body(ApiResponse.builder()
                        .success("SUCCESS")
                        .message("Successfully save data")
                        .data(financialService.updateFinancialData(financialData,userId))
                        .build());
    }

    @GetMapping("/calculateHealth/{id}")
    public ResponseEntity<ApiResponse> calculateHealth(@Valid @PathVariable Integer id) throws CustomException {
        return ResponseEntity.ok()
                .body(ApiResponse.builder()
                        .success("SUCCESS")
                        .message("Successfully calculate health")
                        .data(financialService.calculateFinancialHealthScore(id))
                        .build());
    }

    @GetMapping("/{userId}")
    public ResponseEntity<ApiResponse> getData(@PathVariable Integer userId) throws CustomException {
        return ResponseEntity.ok()
                .body(ApiResponse.builder()
                        .success("SUCCESS")
                        .message("Successfully get user data")
                        .data(financialService.getDataByUserId(userId))
                        .build());
    }
}
