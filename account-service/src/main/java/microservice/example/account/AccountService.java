package microservice.example.account;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AccountService {

	@Autowired
	private AccountRepository accountRepository;

	private Optional<Account> fetchByAccountNumber(String accountNumber) {
		return Optional
				.ofNullable(accountRepository.findByAccountNumber(accountNumber));
	}

	@Transactional
	public Account updateSolde(Amount amount, String accountNum,
			OperationType deposit) {
		Account account = fetchByAccountNumber(accountNum).orElseThrow(
				() -> new AccountNotFoundException(
						"Account not found, please check your account number"));
		switch (deposit) {
		case DEPOSIT:
			return accountRepository.save(Account.builder().id(account.getId())
					.solde(account.getSolde() + amount.getValue())
					.accountNumber(account.getAccountNumber())
					.customerNumber(account.getCustomerNumber()).build());
		case WITHDRAWL:
			if (amount.getValue() > account.getSolde()) {
				throw new SoldeInsuffisantException(
						"Votre solde est insuffisant, l'opération est annulée.");
			}
			return accountRepository.save(Account.builder().id(account.getId())
					.solde(account.getSolde() - amount.getValue())
					.accountNumber(account.getAccountNumber())
					.customerNumber(account.getCustomerNumber()).build());
		default:
			throw new IllegalArgumentException(
					"Le type d'opération donné ne correspondant à aucun type.");
		}
	}
}
