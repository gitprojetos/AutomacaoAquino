import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class TesteCadastro {

	private WebDriver driver;
	private DSL dsl;
	private CampoTreinamentoPage page;

	@Before
	public void inicializa() {
		driver = new FirefoxDriver();
		driver.manage().window().maximize();
		driver.get("file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html");
		dsl = new DSL(driver);
		page = new CampoTreinamentoPage(driver);
	}

	@Test
	public void testeCadastroComSucesso() {
		page.setNome("Jose Cristiano");
		page.setsobrenome("da Silva Lucena");
		page.setSexoMasculino();
		page.setComidaFavoritaCarne();
		page.setComidaFavoritaPizza();
		page.setComboEscolaridade("Superior");
		page.setComboSport("Futebol");
		page.setBotaoCadastrar();

		// Validações

		Assert.assertTrue("Jose Cristinao", page.getNomeCadastro().endsWith("Jose Cristiano"));
		Assert.assertTrue("da Silva Lucena", page.getSobreNomeCadastro().endsWith("da Silva Lucena"));
		Assert.assertEquals("Sexo: Masculino", page.getSexoMasculinoCadastrado());
		Assert.assertEquals("Comida: Carne Pizza", page.getComidaCadastrada());
		Assert.assertEquals("Escolaridade: superior", page.getEscolaridadeCadastrada());
		Assert.assertEquals("Esportes: Futebol", page.getEsportCadastrado());

	}

	@After
	public void finaliza() {
		driver.quit();
	}

}
