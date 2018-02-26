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
	public Account updateSolde(MonetaryAmount amount, String accountNum,
			OperationType deposit) {
		Account account = fetchByAccountNumber(accountNum).orElseThrow(
				() -> new AccountNotFoundException(
						"Account not found"));
		switch (deposit) {
		case DEPOSIT:
			return accountRepository.save(Account.builder().id(account.getId())
					.solde(account.getSolde().add(amount.getAmount()))
					.accountNumber(account.getAccountNumber())
					.customerNumber(account.getCustomerNumber()).build());
		case WITHDRAWL:
			if (amount.getAmount().compareTo(account.getSolde()) > 0) {
				throw new SoldeInsuffisantException(
						"Votre solde est insuffisant");
			}
			return accountRepository.save(Account.builder().id(account.getId())
					.solde(account.getSolde().subtract(amount.getAmount()))
					.accountNumber(account.getAccountNumber())
					.customerNumber(account.getCustomerNumber()).build());
		default:
			throw new IllegalArgumentException(
					"Le type d'opération donné ne correspondant");
		}
	}
}

