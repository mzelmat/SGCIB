package microservice.example.account;

import java.time.LocalDate;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AccountBean {
	private final long id;
	private final LocalDate accountDate;
	private final Long amount;
	private final OperationType operationType;
	private final long accountNumber;
}