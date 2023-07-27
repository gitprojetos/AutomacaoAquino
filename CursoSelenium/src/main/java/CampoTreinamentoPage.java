import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CampoTreinamentoPage {
	
	private DSL dsl;
	
	public CampoTreinamentoPage(WebDriver driver){
		dsl = new DSL(driver);
	}
	
	public void setNome(String nome) {
		dsl.escreve("elementosForm:nome", nome);
	}
	
	public void setsobrenome(String nome) {
		dsl.escreve("elementosForm:sobrenome", nome);
	}
	
	public void setSexoMasculino() {
		dsl.clicarRadio("elementosForm:sexo:0");
	}
	public void setSexoFeminino() {
		dsl.clicarRadio("elementosForm:sexo:1");
	}
	
	public void setComidaFavoritaCarne() {
		dsl.clicarCheckBox("elementosForm:comidaFavorita:0");
	}
	
	public void setComidaFavoritaPizza() {
		dsl.clicarCheckBox("elementosForm:comidaFavorita:2");
	}
	public void setComidaVegetariano() {
		dsl.clicarCheckBox("elementosForm:comidaFavorita:3");
	}
	
	public void setComboEscolaridade(String nivelEscolaridade) {
		dsl.selecionarComboByVisibleText("elementosForm:escolaridade", nivelEscolaridade);
	}
	
	public void setComboSport(String... tipoSporte) {
		
		for (String valor : tipoSporte) {
			dsl.selecionarComboByVisibleText("elementosForm:esportes", valor);
		}
		
	}
	
	public void setBotaoCadastrar() {
		dsl.clicarBotão("elementosForm:cadastrar");
	}
	
	public String getNomeCadastro() {
		return dsl.obterTexto(By.id("descNome"));
	}
	
	public String getSobreNomeCadastro() {
		return dsl.obterTexto(By.id("descSobrenome"));
	}
	
	public String getSexoMasculinoCadastrado() {
		return dsl.obterTexto(By.id("descSexo"));
	}
	
	public String getComidaCadastrada() {
		return dsl.obterTexto(By.id("descComida"));
	}
	
	public String getEscolaridadeCadastrada() {
		return dsl.obterTexto(By.id("descEscolaridade"));
	}
	
	public String getEsportCadastrado() {
		return dsl.obterTexto(By.id("descEsportes"));
	}
	
	public String getAlertSimples() {
		return dsl.getAlertSimples();
	}
	
	public void setAcceptAlert() {
		dsl.setAcceptAlert();
	}
	
	
	
	
}
