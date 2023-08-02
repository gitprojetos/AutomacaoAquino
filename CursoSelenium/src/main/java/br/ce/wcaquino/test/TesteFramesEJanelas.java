package br.ce.wcaquino.test;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import br.ce.wcaquino.core.DSL;
import br.ce.wcaquino.core.DriverFactory;

public class TesteFramesEJanelas {

	public DSL dsl;

	@Before
	public void inicializa() {
		DriverFactory.getDriver().get("file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html");
		dsl = new DSL();
	}

	@Test
	public void testeCadastroComSucesso() {
		DriverFactory.getDriver().switchTo().frame("frame1");
		DriverFactory.getDriver().findElement(By.id("frameButton")).click();
		String textAlertFrame = DriverFactory.getDriver().switchTo().alert().getText();
		Assert.assertEquals("Frame OK!", textAlertFrame);
		DriverFactory.getDriver().switchTo().alert().accept();
		DriverFactory.getDriver().switchTo().defaultContent();
		DriverFactory.getDriver().findElement(By.id("elementosForm:nome")).sendKeys(textAlertFrame);

	}

	@Test
	public void deveInteragirComJanelas() {
		DriverFactory.getDriver().findElement(By.id("buttonPopUpEasy")).click();
		DriverFactory.getDriver().switchTo().window("Popup");
		DriverFactory.getDriver().findElement(By.tagName("textarea")).sendKeys("Deu certo");
		DriverFactory.getDriver().close();
		DriverFactory.getDriver().switchTo().window("");
		DriverFactory.getDriver().findElement(By.id("elementosForm:sugestoes")).sendKeys("E agora ");

	}

	@Test
	public void deveInteragirComJanelasSemTitulo() {
		DriverFactory.getDriver().findElement(By.id("buttonPopUpHard")).click();
		System.out.println(DriverFactory.getDriver().getWindowHandle());
		System.out.println(DriverFactory.getDriver().getWindowHandles());
		DriverFactory.getDriver().switchTo().window((String) DriverFactory.getDriver().getWindowHandles().toArray()[1]);
		DriverFactory.getDriver().findElement(By.tagName("textarea")).sendKeys("Deu certo");
		DriverFactory.getDriver().switchTo().window((String) DriverFactory.getDriver().getWindowHandles().toArray()[0]);
		DriverFactory.getDriver().findElement(By.id("elementosForm:sugestoes")).sendKeys("E agora!");

	}

	@Test
	public void deveInteragirComFrameEcondido() {
		WebElement frame = DriverFactory.getDriver().findElement(By.id("frame2"));
		dsl.executarJS("window.scrollBy(0,arguments[0])", frame.getLocation().y);
		DriverFactory.getDriver().switchTo().frame("frame2");
		DriverFactory.getDriver().findElement(By.id("frameButton")).click();
		String msg = DriverFactory.getDriver().switchTo().alert().getText();
		Assert.assertEquals("Frame OK!", msg);
		DriverFactory.getDriver().switchTo().alert().accept();
		DriverFactory.getDriver().switchTo().defaultContent();
	}

	@After
	public void finaliza() {
		DriverFactory.killDriver();;
	}

}
