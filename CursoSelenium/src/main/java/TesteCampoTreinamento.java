import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

public class TesteCampoTreinamento {

	private WebDriver driver;
	private DSL dsl;

	@Before
	public void inicializa() {
		driver = new FirefoxDriver();
		driver.manage().window().maximize();
		driver.get("file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html");
		dsl = new DSL(driver);
	}

	@Test
	public void deveInteragirComTextField() {
		dsl.escreve("elementosForm:nome", "Meu primeiro teste");
		Assert.assertEquals("Meu primeiro teste", dsl.obterValorCampo("elementosForm:nome", "value"));
	}

	@Test
	public void deveInteragirComTextArea() {
		dsl.escreve("elementosForm:sugestoes", "Interagindo com TextArea");
		String campoTextAreaPreenchido = dsl.obterValorCampo("elementosForm:sugestoes", "value");
		Assert.assertEquals("Interagindo com TextArea", campoTextAreaPreenchido);

	}

	@Test
	public void deveInteragirComRadioButton() {
		dsl.clicarRadio("elementosForm:sexo:0");
		Assert.assertTrue(dsl.checarRadioMarcado("elementosForm:sexo:0"));

	}

	@Test
	public void deveInteragirComCheckBox() {
		dsl.clicarCheckBox("elementosForm:comidaFavorita:0");
		Assert.assertTrue(dsl.checarCheckBoxMarcado("elementosForm:comidaFavorita:0"));

	}

	@Test
	public void deveInteragirComCombox() {
		dsl.selecionarComboByVisibleText("elementosForm:escolaridade", "Mestrado");
		Assert.assertEquals("Mestrado", dsl.obterValorNoComboSelecionado("elementosForm:escolaridade"));

	}

	@Test
	public void deveVerificaValoresCombo() {
		WebElement element = driver.findElement(By.id("elementosForm:escolaridade"));
		Select select = new Select(element);
		List<WebElement> options = select.getOptions();
		assertEquals(8, options.size());

		boolean encontrou = false;

		for (WebElement webElement : options) {
			if (webElement.getText().equals("Mestrado")) {
				encontrou = true;
			}
		}
		Assert.assertTrue(encontrou);

	}

	@Test
	public void deveVerificaValoresComboMultipaEscolha() {
		dsl.selecionarComboByVisibleText("elementosForm:esportes", "Natacao");
		dsl.selecionarComboByVisibleText("elementosForm:esportes", "Corrida");
		dsl.selecionarComboByVisibleText("elementosForm:esportes", "O que eh esporte?");

		WebElement element = driver.findElement(By.id("elementosForm:esportes"));
		Select select = new Select(element);
		List<WebElement> options = select.getAllSelectedOptions();
		Assert.assertEquals(3, options.size());

		select.deselectByVisibleText("Corrida");
		List<WebElement> optionsNovo = select.getAllSelectedOptions();
		Assert.assertEquals(2, optionsNovo.size());

	}

	@Test
	public void deveInteragirComBotao() {
		dsl.clicarBotão("buttonSimple");
		String textBotãoClidado = dsl.obterTexto("buttonSimple");
		Assert.assertEquals("Obrigado!", textBotãoClidado);

	}

	@Test
	public void deveInteragirComLink() {
		// driver.findElement(By.xpath("//a[@onclick='javascript:voltou()']")).click();
		dsl.clicarLink("Voltar");

	}

	@Test
	public void deveBuscarTextosNaPagina() {
		// System.out.println(driver.findElement(By.tagName("Body")).getText());
//		Assert.assertTrue(driver.findElement(By.tagName("Body")).getText().contains("Campo de Treinamento"));

		Assert.assertEquals("Campo de Treinamento", dsl.obterTexto(By.tagName("h3")));
		Assert.assertEquals("Cuidado onde clica, muitas armadilhas...", dsl.obterTexto("facilAchar"));

	}

	@After
	public void finaliza() {
		driver.quit();
	}

}