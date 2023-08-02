package br.ce.wcaquino.core;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class DSL {

	public void escreve(String idCampo, String texto) {
		DriverFactory.getDriver().findElement(By.id(idCampo)).clear();
		DriverFactory.getDriver().findElement(By.id(idCampo)).sendKeys(texto);
	}

	public String obterValorCampo(String idCampo, String propriedadeElemento) {
		return DriverFactory.getDriver().findElement(By.id(idCampo)).getAttribute(propriedadeElemento);
	}

	public void clicarRadio(String idCampo) {
		DriverFactory.getDriver().findElement(By.id(idCampo)).click();
	}

	public void clicarCheckBox(String idCampo) {
		DriverFactory.getDriver().findElement(By.id(idCampo)).click();
	}

	public boolean checarRadioMarcado(String idCampo) {
		return DriverFactory.getDriver().findElement(By.id(idCampo)).isSelected();
	}

	public boolean checarCheckBoxMarcado(String idCampo) {
		return DriverFactory.getDriver().findElement(By.id(idCampo)).isSelected();
	}

	public void selecionarComboByVisibleText(String idCampo, String valor) {
		WebElement element = DriverFactory.getDriver().findElement(By.id(idCampo));
		Select select = new Select(element);
		select.selectByVisibleText(valor);
	}

	public String obterValorNoComboSelecionado(String idCampo) {
		WebElement element = DriverFactory.getDriver().findElement(By.id(idCampo));
		Select select = new Select(element);
		return select.getFirstSelectedOption().getText();
	}

	public void clicarBotão(String id) {
		DriverFactory.getDriver().findElement(By.id(id)).click();
	}

	public void clicarLink(String id) {
		DriverFactory.getDriver().findElement(By.linkText(id)).click();
	}

	public String obterTexto(String id) {
		return DriverFactory.getDriver().findElement(By.className(id)).getText();
	}

	public String obterTexto(By by) {
		return DriverFactory.getDriver().findElement(by).getText();
	}

	public String getAlertSimples() {
		return DriverFactory.getDriver().switchTo().alert().getText();
	}

	public void setAcceptAlert() {
		DriverFactory.getDriver().switchTo().alert().accept();
	}

	public Object executarJS(String cmd, Object... param) {
		JavascriptExecutor js = (JavascriptExecutor) DriverFactory.getDriver();
		WebElement element = DriverFactory.getDriver().findElement(By.id("elementosForm:nome"));
		return js.executeScript(cmd, param);
	}

	/**** INTERGAGINDO COM TABLEA DINÂMICAs ****/

	public void clicarBotaoTabela(String colunaBusca, String valor, String colunaBotao) {
		/*
		 * Primeiro: Deve encontrar a coluna
		 */
		WebElement tabela = DriverFactory.getDriver().findElement(By.xpath("//table[@id='elementosForm:tableUsuarios']"));
		int idColuna = obterIndiceColuna(colunaBusca);

		/* Segundo: Varrer essa coluna para encontrar a linha do registro */
		int idLinha = obterIndiceLinha(valor, tabela, idColuna);

		/*
		 * Terceiro: Feito isso vai descobri em que coluna está o botão que que clicar
		 */

		int idColunaBotao = obterIndiceColuna(colunaBotao);
		
		/* Quarto: Clicar no botão da celula encontrada */
		WebElement celula = tabela.findElement(By.xpath("//tr[+idLinha]/td[+idColunaBotao]"));
		celula.findElement(By.xpath(".//input")).click();

	}

	private int obterIndiceLinha(String valor, WebElement tabela, int idColuna) {
		List<WebElement> linhas = tabela.findElements(By.xpath(".//tr/td[+idColuna]"));
		int idLinha = -1;
		for (int i = 0; i < linhas.size(); i++) {
			if (linhas.get(i).getText().equals(valor)) {
				idLinha = i + 1;
				break;
			}

		}
		return idLinha;
	}

	private int obterIndiceColuna(String colunaBusca) {
		WebElement tabela = DriverFactory.getDriver().findElement(By.xpath("//table[@id='elementosForm:tableUsuarios']"));
		List<WebElement> colunas = tabela.findElements(By.xpath(".//th"));
		int idColuna = -1;
		for (int i = 0; i < colunas.size(); i++) {
			if (colunas.get(i).getText().equals(colunaBusca)) {
				idColuna = i + 1;
				break;
			}
		}
		return idColuna;
	}
}
