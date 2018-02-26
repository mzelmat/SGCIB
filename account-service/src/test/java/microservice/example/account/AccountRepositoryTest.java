package microservice.example.account;

import static org.assertj.core.api.Assertions.assertThat;

import java.math.BigDecimal;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@AutoConfigureTestDatabase(replace = Replace.NONE)
@DataJpaTest
public class AccountRepositoryTest {

	@Autowired
	private AccountRepository accountRepository;
	@Autowired
	private TestEntityManager testEntityManager;

	private Account expectedAccount;

	@Before
	public void init() {

		expectedAccount = Account.builder().accountNumber("1100555589")
				.solde(BigDecimal.valueOf(500)).customerNumber(1100555589)
				.build();
		testEntityManager.persist(expectedAccount);

	}

	@Test
	public void should_fetch_account_by_account_number() {
		Account resulting = accountRepository.findByAccountNumber("1100555589");

		assertThat(resulting).isNotNull().isEqualToComparingFieldByField(
				expectedAccount);
	}

	@Test
	public void should_add_deposit_in_account_number() throws Exception {
		Account accountWithNewSolde = Account.builder()
				.customerNumber(1100551189).accountNumber("1100555599")
				.solde(BigDecimal.valueOf(950)).build();

		accountRepository.save(accountWithNewSolde);

		Account actual = (Account) testEntityManager
				.getEntityManager()
				.createQuery(
						"SELECT OBJECT(account) FROM Account account WHERE accountNumber = '1100555599' ")
				.getSingleResult();

		assertThat(actual.getSolde()).isEqualTo(BigDecimal.valueOf(950));
	}
}
