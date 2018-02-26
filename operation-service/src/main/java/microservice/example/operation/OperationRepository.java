package microservice.example.operation;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface OperationRepository extends JpaRepository<Operation, Long> {

	List<Operation> findByAccountNumber(String accountNumber);
}
