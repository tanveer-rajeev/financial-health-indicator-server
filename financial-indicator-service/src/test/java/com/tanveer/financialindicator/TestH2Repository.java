package com.tanveer.financialindicator;

import com.tanveer.financialindicator.model.FinancialData;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TestH2Repository extends JpaRepository<FinancialData,Integer> {
}
