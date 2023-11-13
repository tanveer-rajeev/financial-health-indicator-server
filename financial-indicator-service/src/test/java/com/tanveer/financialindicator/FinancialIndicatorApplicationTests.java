package com.tanveer.financialindicator;

import com.tanveer.financialindicator.model.FinancialData;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.web.client.RestTemplate;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class FinancialIndicatorApplicationTests {

	@LocalServerPort
	private int port;

	@Autowired
	private TestH2Repository testH2Repository;

	private String baseUrl = "http://localhost";
	private static RestTemplate restTemplate;

	@BeforeAll
	public static void init() {
		restTemplate = new RestTemplate();
	}

	@BeforeEach
	public void setUp() {
		baseUrl = baseUrl.concat(":").concat(port + "").concat("/api/v1/data");
	}

	@Test
	void contextLoads() {
	}

	@Test
	public void testAddFinancialData() {
		FinancialData financialData = FinancialData.builder()
				.monthlyIncome(2345)
				.monthlyExpenses(12)
				.totalDebts(33)
				.totalAssets(323)
				.build();
		System.out.println(baseUrl);
		FinancialData response = restTemplate.postForObject(baseUrl+"/save", financialData, FinancialData.class);
		assertEquals(2345,financialData.getMonthlyIncome());
	}

	@Test
	@Sql(statements = "INSERT INTO financial_data (id,monthlyIncome, monthlyExpenses, totalDebts,totalAssets,userId) VALUES (1,33423,2342, 2343, 34000,1)", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "DELETE FROM financial_data WHERE userId = 1", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
	public void getFinancialData(){
		FinancialData financialData = restTemplate.getForObject(baseUrl + "/{id}", FinancialData.class, 1);
		System.out.println(financialData);
		assertAll(
				() -> assertNotNull(financialData),
				() -> assertEquals(1, financialData.getUserId()),
				() -> assertEquals(34000, financialData.getTotalAssets())
		);
	}
}

