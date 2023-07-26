import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;


public class TesteCadastro {
	
	@Test
	public void testeCadastroComSucesso() {
		
		WebDriver driver = new FirefoxDriver();
		driver.manage().window().maximize();
		driver.get("file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html");
		driver.findElement(By.id("elementosForm:nome")).sendKeys("Jose Cristinao");
		driver.findElement(By.id("elementosForm:sobrenome")).sendKeys("da Silva Lucena");
		driver.findElement(By.id("elementosForm:sexo:0")).click();
		driver.findElement(By.id("elementosForm:comidaFavorita:0")).click();
		driver.findElement(By.id("elementosForm:comidaFavorita:2")).click();
		
		WebElement elementEscolaridade = driver.findElement(By.id("elementosForm:escolaridade"));
		
		Select selectEscolaridade = new Select(elementEscolaridade);
		selectEscolaridade.selectByVisibleText("Superior");
		
		WebElement elementSportes = driver.findElement(By.id("elementosForm:esportes"));
		Select selectSportes = new Select(elementSportes);
		selectSportes.selectByVisibleText("Futebol");
		driver.findElement(By.id("elementosForm:cadastrar")).click();
		
		//Validações
		
		String textoNome = driver.findElement(By.id("descNome")).getText();
		String textoSobreNome = driver.findElement(By.id("descSobrenome")).getText();
		String textoSexo = driver.findElement(By.id("descSexo")).getText();
		String textoComidaFavorita = driver.findElement(By.id("descComida")).getText();
		String textoEscolaridade = driver.findElement(By.id("descEscolaridade")).getText();
		String textoSportes = driver.findElement(By.id("descEsportes")).getText();
		
		Assert.assertTrue("Jose Cristinao", textoNome.endsWith("Jose Cristinao"));
		Assert.assertTrue("da Silva Lucena", textoSobreNome.endsWith("da Silva Lucena"));
		Assert.assertEquals("Sexo: Masculino", textoSexo);
		Assert.assertEquals("Comida: Carne Pizza", textoComidaFavorita);
		Assert.assertEquals("Escolaridade: superior", textoEscolaridade);
		Assert.assertEquals("Esportes: Futebol", textoSportes);
		
		driver.close();
		
		
		
	}

}
