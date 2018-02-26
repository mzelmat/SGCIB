package microservice.example.account;

import java.math.BigDecimal;
import java.time.LocalDate;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AccountBean {
	private final long id;
	private final LocalDate accountDate;
	private final BigDecimal amount;
	private final OperationType operationType;
	private final long accountNumber;
	
	public AccountBean() {
		this.id = 0;
		this.accountDate = null;
		this.amount = null;
		this.operationType = null;
		this.accountNumber = 0;
	}
	
	public AccountBean(long id, LocalDate accountDate, BigDecimal amount,
			OperationType operationType, long accountNumber) {
		this.id = id;
		this.accountDate = accountDate;
		this.amount = amount;
		this.operationType = operationType;
		this.accountNumber = accountNumber;
	}
	
	
}
