package microservice.example.operation;

import static com.google.common.collect.ImmutableList.of;
import static microservice.example.operation.OperationType.DEPOSIT;
import static microservice.example.operation.OperationType.WITHDRAWL;
import static org.mockito.Mockito.doReturn;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.math.BigDecimal;
import java.time.LocalDate;

import microservice.example.operation.Operation;
import microservice.example.operation.OperationController;
import microservice.example.operation.OperationService;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

@RunWith(SpringRunner.class)
@WebMvcTest(OperationController.class)
public class OperationControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private OperationService operationService;

	private Operation deposit;
	private Operation withdrawl;

	@Before
	public void init() {

		deposit = Operation.builder().id(1L).amount(BigDecimal.valueOf(2500))
				.accountDate(LocalDate.of(2017, 10, 10)).accountNumber("75000000001")
				.operationType(DEPOSIT).build();
		withdrawl = Operation.builder().id(2L).amount(BigDecimal.valueOf(250))
				.accountDate(LocalDate.of(2018, 02, 14)).accountNumber("75000000001")
				.operationType(WITHDRAWL).build();
	}

	@Test
	public void should_fetch_all_account_history() throws Exception {
		doReturn(of(deposit, withdrawl)).when(operationService)
				.findByAccountNumber("75000000001");

		mockMvc.perform(
				get("/operations/75000000001/history/").contentType(
						MediaType.APPLICATION_JSON_VALUE))
				.andExpect(status().isOk())
				.andExpect(
						content()
								.json("[{'id':1, accountDate:[2017,10,10], amount:2500, operationType:'DEPOSIT', accountNumber:\"75000000001\"},"
										+ " {'id':2, accountDate:[2018,2,14], amount:250, operationType:'WITHDRAWL', accountNumber:\"75000000001\"}"
										+ "]"));
	}
}
