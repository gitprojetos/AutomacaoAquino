import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class DSL {

	private WebDriver driver;

	public DSL(WebDriver driver) {
		this.driver = driver;
	}

	public void escreve(String idCampo, String texto) {
		driver.findElement(By.id(idCampo)).clear();
		driver.findElement(By.id(idCampo)).sendKeys(texto);
	}

	public String obterValorCampo(String idCampo, String propriedadeElemento) {
		return driver.findElement(By.id(idCampo)).getAttribute(propriedadeElemento);
	}

	public void clicarRadio(String idCampo) {
		driver.findElement(By.id(idCampo)).click();
	}

	public void clicarCheckBox(String idCampo) {
		driver.findElement(By.id(idCampo)).click();
	}

	public boolean checarRadioMarcado(String idCampo) {
		return driver.findElement(By.id(idCampo)).isSelected();
	}

	public boolean checarCheckBoxMarcado(String idCampo) {
		return driver.findElement(By.id(idCampo)).isSelected();
	}

	public void selecionarComboByVisibleText(String idCampo, String valor) {
		WebElement element = driver.findElement(By.id(idCampo));
		Select select = new Select(element);
		select.selectByVisibleText(valor);
	}

	public String obterValorNoComboSelecionado(String idCampo) {
		WebElement element = driver.findElement(By.id(idCampo));
		Select select = new Select(element);
		return select.getFirstSelectedOption().getText();
	}

	public void clicarBotão(String id) {
		driver.findElement(By.id(id)).click();
	}

	public void clicarLink(String id) {
		driver.findElement(By.linkText(id)).click();
	}

	public String obterTexto(String id) {
		return driver.findElement(By.className(id)).getText();
	}

	public String obterTexto(By by) {
		return driver.findElement(by).getText();
	}
	
	public String getAlertSimples() {
		return driver.switchTo().alert().getText();
	}
	
	public void setAcceptAlert() {
		driver.switchTo().alert().accept();
	}
	
	public Object executarJS(String cmd, Object... param) {
		JavascriptExecutor js = (JavascriptExecutor)driver;
		WebElement element = driver.findElement(By.id("elementosForm:nome"));
		return js.executeScript(cmd, param);
	}
}
