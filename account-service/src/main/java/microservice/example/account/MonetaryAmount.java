package microservice.example.account;

import java.math.BigDecimal;

import javax.validation.constraints.NotNull;

import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.Value;

@Value
@Builder
@NoArgsConstructor(force = true)
public class MonetaryAmount {

	@NotNull
	private final BigDecimal amount;

	public MonetaryAmount(BigDecimal amount) {
		this.amount = validate(amount);
	}

	private BigDecimal validate(BigDecimal amount) {
		if (amount.doubleValue() > 0) {
			return amount;
		}
		throw new IllegalArgumentException("Amount should be positif");
	}
}
