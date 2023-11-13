package com.tanveer.financialindicator.repository;

import com.tanveer.financialindicator.model.FinancialData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FinancialRepository extends JpaRepository<FinancialData,Integer> {

    Optional<FinancialData> findByUserId(Integer id);
}
