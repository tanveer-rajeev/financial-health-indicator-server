package com.tanveer.financialindicator.service;

import com.tanveer.financialindicator.Exception.CustomException;
import com.tanveer.financialindicator.model.FinancialData;

public interface FinancialService {
    double calculateFinancialHealthScore(Integer userId) throws CustomException;
    FinancialData saveFinancialData(FinancialData financialData);
    FinancialData updateFinancialData(FinancialData financialData,Integer id) throws CustomException;

    FinancialData getDataByUserId(Integer id) throws CustomException;
}
