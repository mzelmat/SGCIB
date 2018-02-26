package microservice.example.operation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OperationController {

	private final OperationService operationService;

	@Autowired
	public OperationController(OperationService operationService) {
		this.operationService = operationService;
	}

	@GetMapping("/operations/{accountNumber}/history")
	public List<Operation> getAccountHistory(@PathVariable String accountNumber) {
		return operationService.findByAccountNumber(accountNumber);
	}
}
