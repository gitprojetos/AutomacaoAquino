package br.ce.wcaquino.test;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class TesteAlert {

	WebDriver driver;

	@Before
	public void inicializa() {
		driver = new FirefoxDriver();
		driver.manage().window().maximize();
		driver.get("file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html");
	}

	@Test
	public void deveInteragirComAlertSimples() {
		driver.findElement(By.id("alert")).click();
		String textoAlert = driver.switchTo().alert().getText();
		Assert.assertEquals("Alert Simples", textoAlert);
		driver.switchTo().alert().accept();
		driver.findElement(By.id("elementosForm:nome")).sendKeys(textoAlert);

	}

	@Test
	public void deveInteragirComAlertComfirma() {
		driver.findElement(By.id("confirm")).click();
		String textAlertConfirmSimples = driver.switchTo().alert().getText();
		Assert.assertEquals("Confirm Simples", textAlertConfirmSimples);
		driver.switchTo().alert().accept();
		driver.switchTo().alert().accept();

		driver.findElement(By.id("confirm")).click();
		driver.switchTo().alert().dismiss();
		driver.switchTo().alert().accept();

	}

	@Test
	public void deveInteragirComAlertPrompt() {
		driver.findElement(By.id("prompt")).click();
		Alert alert = driver.switchTo().alert();
		String textoAlertPrompt = alert.getText();
		Assert.assertEquals("Digite um numero", textoAlertPrompt);
		alert.sendKeys("123");
		alert.accept();
		Assert.assertEquals("Era 123?", alert.getText());
		alert.accept();
		Assert.assertEquals(":D", alert.getText());
		alert.accept();

	}

	@After
	public void finaliza() {
		driver.quit();
	}

}
