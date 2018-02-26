package microservice.example.operation;

import lombok.Getter;

public enum OperationType {

	DEPOSIT("DEPOSIT"), WITHDRAWL("WITHDRAWL");

	@Getter
	private String value;

	OperationType(String value) {
		this.value = value;
	}
}
