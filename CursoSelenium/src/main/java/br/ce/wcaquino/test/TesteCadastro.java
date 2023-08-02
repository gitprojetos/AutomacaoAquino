package br.ce.wcaquino.test;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import br.ce.wcaquino.core.BaseTest;
import br.ce.wcaquino.core.DriverFactory;
import br.ce.wcaquino.page.CampoTreinamentoPage;

public class TesteCadastro extends BaseTest{

	private CampoTreinamentoPage page;

	@Before
	public void inicializa() {
		DriverFactory.getDriver()
				.get("file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html");
		page = new CampoTreinamentoPage();

	}

	@Test
	public void testeCadastroComSucesso() {
		page.setNome("Jose Cristiano");
		page.setsobrenome("da Silva Lucena");
		page.setSexoMasculino();
		page.setComidaFavoritaCarne();
		page.setComidaFavoritaPizza();
		page.setComboEscolaridade("Superior");
		page.setComboSport("Futebol");
		page.setBotaoCadastrar();

		// Validações

		Assert.assertTrue("Jose Cristinao", page.getNomeCadastro().endsWith("Jose Cristiano"));
		Assert.assertTrue("da Silva Lucena", page.getSobreNomeCadastro().endsWith("da Silva Lucena"));
		Assert.assertEquals("Sexo: Masculino", page.getSexoMasculinoCadastrado());
		Assert.assertEquals("Comida: Carne Pizza", page.getComidaCadastrada());
		Assert.assertEquals("Escolaridade: superior", page.getEscolaridadeCadastrada());
		Assert.assertEquals("Esportes: Futebol", page.getEsportCadastrado());

	}


}
