package br.ce.wcaquino.test;
import java.io.InterruptedIOException;
import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import br.ce.wcaquino.core.DSL;
import br.ce.wcaquino.core.DriverFactory;
import br.ce.wcaquino.page.CampoTreinamentoPage;

public class TesteSincronismo {

	private DSL dsl;
	private CampoTreinamentoPage page;

	@Before
	public void inicializa() {
		DriverFactory.getDriver().get("file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html");
		page = new CampoTreinamentoPage();
		dsl = new DSL();
	}

	@SuppressWarnings("deprecation")
	@Test
	public void deveUtilizarEsperaImplicita() {
		DriverFactory.getDriver().manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		dsl.clicarBotão("buttonDelay");
		dsl.escreve("novoCampo", "Meu teste");
		DriverFactory.getDriver().manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
	}

	@Test
	public void deveUtilizarEsperaExplicita() throws InterruptedIOException {
		dsl.clicarBotão("buttonDelay");
		//WebDriverWait wait = new WebDriverWait(driver, 30);
		//wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.id("novoCampo")));
		dsl.escreve("novoCampo", "Meu teste");
	}

	@After
	public void finaliza() {
		DriverFactory.killDriver();
	}

}
