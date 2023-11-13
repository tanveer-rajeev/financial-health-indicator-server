package com.tanveer.financialindicator.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "financial_data")
@Builder
public class FinancialData {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Min(message = "negative value not accepted",value = 0)
    private double monthlyIncome;

    @Min(message = "negative value not accepted",value = 0)
    private double monthlyExpenses;

    @Min(message = "negative value not accepted",value = 0)
    private double totalDebts;

    @Min(message = "negative value not accepted",value = 0)
    private double totalAssets;

    @Min(message = "greater than zero",value = 1)
    private Integer userId;
}
