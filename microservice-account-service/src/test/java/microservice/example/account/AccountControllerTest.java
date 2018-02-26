package microservice.example.account;

import static microservice.example.account.OperationType.DEPOSIT;
import static microservice.example.account.OperationType.WITHDRAWL;
import static org.mockito.Mockito.doReturn;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import microservice.example.account.Account;
import microservice.example.account.AccountController;
import microservice.example.account.AccountService;
import microservice.example.account.Amount;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(SpringRunner.class)
@WebMvcTest(AccountController.class)
public class AccountControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private AccountService accountService;

	private ObjectMapper mapper = new ObjectMapper();
	private Amount amount;
	private Account accountWithdraw;
	private Account accountDeposit;

	@Before
	public void init() {
		amount = new Amount(300L);
		accountWithdraw = Account.builder().id(1L).customerNumber(75000000101L)
				.solde(2500).accountNumber(75000000001L).build();
		accountDeposit = Account.builder().id(2L).customerNumber(75000000102L)
				.solde(250).accountNumber(75000000002L).build();

	}
	
	@Test
	public void should_make_deposit_by_account_number() throws Exception {
		doReturn(accountDeposit).when(accountService).updateSolde(amount,
				"75000000001", DEPOSIT);

		mockMvc.perform(
				put("/account/75000000001/operation/DEPOSIT/").content(
						mapper.writeValueAsString(amount)).contentType(
						MediaType.APPLICATION_JSON_VALUE))
				.andExpect(status().isOk())
				.andExpect(
						content()
								.json("{'id':2, 'accountNumber': 75000000002, 'solde': 250, 'customerNumber': 75000000102}"));
	}

	@Test
	public void should_make_withdraw_by_account_number() throws Exception {
		doReturn(accountWithdraw).when(accountService).updateSolde(amount,
				"75000000002", WITHDRAWL);

		mockMvc.perform(
				put("/account/75000000002/operation/WITHDRAWL/").content(
						mapper.writeValueAsString(amount)).contentType(
						MediaType.APPLICATION_JSON_VALUE))
				.andExpect(status().isOk())
				.andExpect(
						content()
								.json("{'id':1, 'accountNumber': 75000000001, 'solde': 2500, 'customerNumber': 75000000101}"));
	}

}
