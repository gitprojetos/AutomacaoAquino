import static org.junit.Assert.assertEquals;

import java.util.Iterator;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

public class TesteCampoTreinamento {

	@Test
	public void teste() {
		WebDriver driver = new FirefoxDriver();
		driver.manage().window().maximize();
		driver.get("file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html");
		driver.close();

	}

	@Test
	public void deveInteragirComTextField() {
		WebDriver driver = new FirefoxDriver();
		driver.manage().window().maximize();
		driver.get("file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html");
		driver.findElement(By.id("elementosForm:nome")).sendKeys("Meu primeiro teste");
		Assert.assertEquals("Meu primeiro teste",
				driver.findElement(By.id("elementosForm:nome")).getAttribute("value"));
		driver.close();
	}

	@Test
	public void deveInteragirComTextArea() {
		WebDriver driver = new FirefoxDriver();
		driver.manage().window().maximize();
		driver.get("file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html");
		driver.findElement(By.id("elementosForm:sugestoes")).sendKeys("Interagindo com TextArea");
		String campoTextAreaPreenchido = driver.findElement(By.id("elementosForm:sugestoes")).getAttribute("value");
		Assert.assertEquals("Interagindo com TextArea", campoTextAreaPreenchido);
		driver.close();
	}

	@Test
	public void deveInteragirComRadioButton() {
		WebDriver driver = new FirefoxDriver();
		driver.manage().window().maximize();
		driver.get("file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html");
		driver.findElement(By.id("elementosForm:sexo:0")).click();
		Assert.assertTrue(driver.findElement(By.id("elementosForm:sexo:0")).isSelected());
		driver.close();

	}

	@Test
	public void deveInteragirComCheckBox() {
		WebDriver driver = new FirefoxDriver();
		driver.manage().window().maximize();
		driver.get("file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html");
		driver.findElement(By.id("elementosForm:comidaFavorita:0")).click();
		Assert.assertTrue(driver.findElement(By.id("elementosForm:comidaFavorita:0")).isSelected());
		driver.close();

	}

	@Test
	public void deveInteragirComCombox() {
		WebDriver driver = new FirefoxDriver();
		driver.manage().window().maximize();
		driver.get("file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html");
		WebElement element = driver.findElement(By.id("elementosForm:escolaridade"));
		Select select = new Select(element);
		select.selectByVisibleText("Mestrado");
		Assert.assertEquals("Mestrado", select.getFirstSelectedOption().getText());
		driver.close();
	}

	@Test
	public void deveVerificaValoresCombo() {
		WebDriver driver = new FirefoxDriver();
		driver.manage().window().maximize();
		driver.get("file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html");
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

		driver.close();
	}

	@Test
	public void deveVerificaValoresComboMultipaEscolha() {
		WebDriver driver = new FirefoxDriver();
		driver.manage().window().maximize();
		driver.get("file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html");
		WebElement element = driver.findElement(By.id("elementosForm:esportes"));
		Select select = new Select(element);
		select.selectByVisibleText("Natacao");
		select.selectByVisibleText("Corrida");
		select.selectByVisibleText("O que eh esporte?");
		List<WebElement> options = select.getAllSelectedOptions();
		Assert.assertEquals(3, options.size());

		select.deselectByVisibleText("Corrida");
		List<WebElement> optionsNovo = select.getAllSelectedOptions();
		Assert.assertEquals(2, optionsNovo.size());

		driver.close();
	}

	@Test
	public void deveInteragirComBotao() {
		WebDriver driver = new FirefoxDriver();
		driver.manage().window().maximize();
		driver.get("file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html");
		driver.findElement(By.id("buttonSimple")).click();
		String textBotãoClidado = driver.findElement(By.id("buttonSimple")).getAttribute("value");
		Assert.assertEquals("Obrigado!", textBotãoClidado);
		driver.close();
	}

	@Test
	public void deveInteragirComLink() {
		WebDriver driver = new FirefoxDriver();
		driver.manage().window().maximize();
		driver.get("file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html");
		// driver.findElement(By.xpath("//a[@onclick='javascript:voltou()']")).click();
		driver.findElement(By.linkText("Voltar")).click();
		driver.close();

	}

	@Test
	public void deveBuscarTextosNaPagina() {
		WebDriver driver = new FirefoxDriver();
		driver.manage().window().maximize();
		driver.get("file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html");
//		System.out.println(driver.findElement(By.tagName("Body")).getText());
//		Assert.assertTrue(driver.findElement(By.tagName("Body")).getText().contains("Campo de Treinamento"));

		Assert.assertEquals("Campo de Treinamento", driver.findElement(By.tagName("h3")).getText());
		Assert.assertEquals("Cuidado onde clica, muitas armadilhas...",
				driver.findElement(By.className("facilAchar")).getText());
		driver.close();

	}

}