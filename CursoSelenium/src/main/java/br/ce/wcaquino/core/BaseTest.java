package br.ce.wcaquino.core;

import java.io.File;

import org.junit.After;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

public class BaseTest {

	@After
	public void finaliza() {
		
		TakesScreenshot ss = (TakesScreenshot)DriverFactory.getDriver();
		File arquivo =  ss.getScreenshotAs(OutputType.FILE);
		//FileUtils.copyFile()
		
		if (Propiedades.FECHAR_BROWSER) {
			DriverFactory.killDriver();
		}

	}

}
