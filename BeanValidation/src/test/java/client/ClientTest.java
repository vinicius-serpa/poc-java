package client;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;

import model.Client;

public class ClientTest {

	private Validator validator;
	 
	@Before
	public void setUp() {
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		this.validator = factory.getValidator();
	}
 
	@Test
	public void naoDeveAceitarSobrenomeCurto() {
		
		Client client = new Client();
		client.setFirstName("Ana");
		client.setLastName("S.");
 
		Set<ConstraintViolation<Client>> restrictions = validator.validate(client);
 
		assertThat(restrictions, hasSize(1));
		assertThat(getNomePrimeiraPropriedade(restrictions), is("lastName"));
	}
 
	private String getNomePrimeiraPropriedade(
			Set<ConstraintViolation<Client>> restrictions) {
		return restrictions.iterator().next().getPropertyPath().iterator().next().getName();
	}
 
	@Test
	public void naoDeveAceitarClienteSemNomeESobrenome() {
		Client client = new Client();
		Set<ConstraintViolation<Client>> restricoes = validator.validate(client);
		assertThat(restricoes, Matchers.hasSize(2));
	}
 
	@Test
	public void devePassarNaValidacaoComNomeESobrenomeInformados() {
		Client client = new Client();
		client.setFirstName("Ana");
		client.setLastName("Silva");
 
		Set<ConstraintViolation<Client>> restricoes = validator.validate(client);
 
		assertThat(restricoes, empty());
	}

}
