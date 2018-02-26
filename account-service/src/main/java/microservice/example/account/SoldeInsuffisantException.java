package microservice.example.account;

public class SoldeInsuffisantException extends RuntimeException {

    private String message;

    public SoldeInsuffisantException(String message) {
        this.message = message;
    }
}
