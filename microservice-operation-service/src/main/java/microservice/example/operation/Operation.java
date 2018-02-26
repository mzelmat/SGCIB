package microservice.example.operation;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
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

@Table(name = Operation.TABLE_NAME)
@Entity
@Data
@Builder
@EqualsAndHashCode(of = "id")
@ToString(of = "id")
public class Operation implements Serializable {

	static final String TABLE_NAME = "OPERATION";

	static final String ID_COLUMN_NAME = "id";

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = ID_COLUMN_NAME)
	@Getter
	private final long id;

	private final LocalDate accountDate;

	private final Long amount;

	@NotNull
	@Enumerated(EnumType.STRING)
	private final OperationType operationType;

	@NotNull
	@Column(unique = true)
	private final long accountNumber;

	public Operation() {
		this.id = 0;
		this.accountDate = null;
		this.amount = null;
		this.operationType = null;
		this.accountNumber = 0;
	}

	public Operation(long id, LocalDate accountDate, Long amount,
			OperationType operationType, long accountNumber) {
		this.id = id;
		this.accountDate = accountDate;
		this.amount = amount;
		this.operationType = operationType;
		this.accountNumber = accountNumber;
	}

}
