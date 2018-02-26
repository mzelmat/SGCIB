package microservice.example.account;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class AccountController {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	private final  OperationServiceProxy proxy;

	private final AccountService accountService;

	@Autowired
	public AccountController(AccountService accountService, OperationServiceProxy operationService) {
		this.accountService = accountService;
		this.proxy = operationService;
	}

	@PutMapping("/account/{accountNumber}/operation/{operationType}")
	public Account updateSolde(@PathVariable String accountNumber,
			@PathVariable OperationType operationType,
			@RequestBody Amount amount) {
		return accountService.updateSolde(amount, accountNumber, operationType);
	}

	@GetMapping("/accounts/{accountNumber}/history")
	public List<AccountBean> getHistories(@PathVariable String accountNumber) {
		Map<String, String> uriVariables = new HashMap<>();
		uriVariables.put("accountNumber", accountNumber);
		ResponseEntity<List> responseEntity = new RestTemplate().getForEntity(
				"http://localhost:8000/operations/{accountNumber}/history",
				List.class, uriVariables);
		return responseEntity.getBody();
	}

	@GetMapping("/accounts-feign/{accountNumber}/history")
	public List<AccountBean> getHistoriesFeign(@PathVariable long accountNumber) {
		List<AccountBean> response = proxy.getHistory(accountNumber);
		logger.info("{}", response);
		return response;
	}
}
