package microservice.example.account;

public class AccountNotFoundException extends RuntimeException {

    private String message;

    public AccountNotFoundException(String message) {
        this.message = message;
    }
}
