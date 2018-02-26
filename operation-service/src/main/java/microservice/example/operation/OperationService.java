package microservice.example.operation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OperationService {

	@Autowired
	private OperationRepository operationRepository;

	public List<Operation> findByAccountNumber(String accountNumber) {
		return operationRepository.findByAccountNumber(accountNumber);
	}
}

