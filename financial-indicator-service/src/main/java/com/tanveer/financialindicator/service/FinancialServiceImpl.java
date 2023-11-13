package com.tanveer.financialindicator.service;

import com.tanveer.financialindicator.Exception.CustomException;
import com.tanveer.financialindicator.model.FinancialData;
import com.tanveer.financialindicator.repository.FinancialRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FinancialServiceImpl implements FinancialService {

    private final FinancialRepository financialRepository;

    @Override
    public double calculateFinancialHealthScore(Integer id) throws CustomException {
        FinancialData financialData = financialRepository.findByUserId(id).orElseThrow(() -> new CustomException("User not found by given id"));
        return (financialData.getMonthlyIncome() - financialData.getMonthlyExpenses()) -
                (financialData.getTotalDebts() - financialData.getTotalAssets());
    }

    @Override
    public FinancialData saveFinancialData(FinancialData financialData) {
        return financialRepository.save(financialData);
    }

    @Override
    @Transactional
    public FinancialData updateFinancialData(FinancialData requestedData, Integer id) throws CustomException {
        FinancialData data = financialRepository.findByUserId(id).orElseThrow(() -> new CustomException("Data not found by given id"));
        if (requestedData.getMonthlyIncome() > 0 && data.getMonthlyIncome() != requestedData.getMonthlyExpenses()){
            data.setMonthlyIncome(requestedData.getMonthlyIncome());
        }
        if (requestedData.getMonthlyExpenses() > 0 && data.getMonthlyExpenses() != requestedData.getMonthlyExpenses()){
            data.setMonthlyExpenses(requestedData.getMonthlyExpenses());
        }

        if (requestedData.getTotalDebts() > 0 && data.getTotalDebts() != requestedData.getTotalDebts()){
            data.setTotalDebts(requestedData.getTotalDebts());
        }

        if (requestedData.getTotalAssets() > 0 && data.getTotalAssets() != requestedData.getTotalAssets()){
            data.setTotalAssets(requestedData.getTotalAssets());
        }
        return data;
    }

    @Override
    public FinancialData getDataByUserId(Integer id) throws CustomException {
        return financialRepository.findByUserId(id).orElseThrow(() -> new CustomException("User not found by given id"));
    }
}
