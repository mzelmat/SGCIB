package microservice.example.account;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Table(name = Account.TABLE_NAME)
@Entity
@Data
@Builder
@EqualsAndHashCode(of = "id")
@ToString(of = "id")
public class Account implements Serializable {

	static final String TABLE_NAME = "ACCOUNT";

	static final String ID_COLUMN_NAME = "id";

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = ID_COLUMN_NAME)
	@Getter
	private final long id;

	@NotNull
	@Column(unique = true)
	private final long accountNumber;

	private final BigDecimal solde;

	@NotNull
	@Column(unique = true)
	private final long customerNumber;

	public Account() {
		this.id = 0;
		this.accountNumber = 0;
		this.solde = null;
		this.customerNumber = 0;
	}

	public Account(long id, long accountNumber, BigDecimal solde, long customerNumber) {
		this.id = id;
		this.accountNumber = accountNumber;
		this.solde = solde;
		this.customerNumber = customerNumber;
	}

}
