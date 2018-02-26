package microservice.example.operation;

import static microservice.example.operation.OperationType.DEPOSIT;
import static microservice.example.operation.OperationType.WITHDRAWL;
import static org.assertj.core.api.Assertions.assertThat;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@AutoConfigureTestDatabase(replace = Replace.NONE)
@DataJpaTest
public class OperationRepositoryTest {

	@Autowired
	private OperationRepository operationRepository;
	@Autowired
	private TestEntityManager testEntityManager;

	private Operation deposit;
	private Operation withdrawl;

	@Before
	public void init() {
		deposit = Operation.builder().amount(BigDecimal.valueOf(50))
				.accountDate(LocalDate.of(2017, 10, 10))
				.accountNumber("75000AA0001").operationType(DEPOSIT).build();
		withdrawl = Operation.builder().amount(BigDecimal.valueOf(300))
				.accountDate(LocalDate.of(2018, 02, 14))
				.accountNumber("75000AA0001").operationType(WITHDRAWL).build();
		testEntityManager.persist(deposit);
		testEntityManager.persist(withdrawl);

	}

	@Test
	public void should_load_all_operations_by_account_number() {
		List<Operation> result = operationRepository
				.findByAccountNumber("75000AA0001");

		assertThat(result).isNotNull().isNotEmpty().hasSize(2)
				.containsExactly(deposit, withdrawl);
	}

}