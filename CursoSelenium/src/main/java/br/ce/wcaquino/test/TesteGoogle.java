package br.ce.wcaquino.test;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class TesteGoogle {

	@Test
	public void teste() {

		WebDriver driver = new FirefoxDriver();
		// driver.manage().window().setPosition(new Point(100, 100));
		// driver.manage().window().setSize(new Dimension(1200, 765));
		// Primeiro parâmentro largura e depois altura
		driver.manage().window().maximize();
		driver.get("https://www.google.com.br");
		Assert.assertEquals("Google", driver.getTitle());
		// valor atual vs valor esperado
		driver.quit();
	}

	@Test
	public void StartandoChromeDriver() {

		WebDriver driver = new ChromeDriver();
		driver.get("https://www.google.com.br");
		Assert.assertEquals("Google", driver.getTitle());
		// valor atual vs valor esperado
		driver.quit();
	}

}
