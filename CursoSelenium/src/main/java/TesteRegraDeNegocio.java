import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class TesteRegraDeNegocio {

	private WebDriver driver;
	private CampoTreinamentoPage page;

	@Before
	public void inicializa() {
		driver = new FirefoxDriver();
		driver.manage().window().maximize();
		driver.get("file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html");
		page = new CampoTreinamentoPage(driver);
	}

	@Test
	public void campoNomeObrigatorio() {
		page.setBotaoCadastrar();
		Assert.assertEquals("Nome eh obrigatorio", page.getAlertSimples());
		page.setAcceptAlert();

	}

	@Test
	public void campoSobrenomeObrigatorio() {
		page.setNome("Cristiano");
		page.setBotaoCadastrar();
		Assert.assertEquals("Sobrenome eh obrigatorio", page.getAlertSimples());
		page.setAcceptAlert();

	}

	@Test
	public void campoSexoObrigatorio() {
		page.setNome("Cristiano");
		page.setsobrenome("da Silva Lucena");
		page.setBotaoCadastrar();
		Assert.assertEquals("Sexo eh obrigatorio", page.getAlertSimples());
		page.setAcceptAlert();

	}

	@Test
	public void validarRegraDoVegetariano() {
		page.setNome("Cristiano");
		page.setsobrenome("da Silva Lucena");
		page.setSexoMasculino();
		page.setComidaFavoritaCarne();
		page.setComidaVegetariano();
		page.setBotaoCadastrar();
		Assert.assertEquals("Tem certeza que voce eh vegetariano?", page.getAlertSimples());
		page.setAcceptAlert();

	}

	@Test
	public void validarRegraDaEscolhaDoSport() {
		page.setNome("Cristiano");
		page.setsobrenome("da Silva Lucena");
		page.setSexoMasculino();
		page.setComidaFavoritaCarne();
		page.setComboSport("Corrida", "O que eh esporte?");
		page.setBotaoCadastrar();
		Assert.assertEquals("Voce faz esporte ou nao?", page.getAlertSimples());
		page.setAcceptAlert();

	}
	
	@After
	public void finaliza() {
		driver.quit();
	}

}
