package br.ce.wcaquino.test;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;

import br.ce.wcaquino.core.BaseTest;
import br.ce.wcaquino.core.DriverFactory;
import br.ce.wcaquino.page.CampoTreinamentoPage;

@RunWith(Parameterized.class)
public class TesteRegrasCadastraisParameterized extends BaseTest {

	private CampoTreinamentoPage page;

	@Parameter
	public String nome;
	@Parameter(value = 1)
	public String sobreNome;
	@Parameter(value = 2)
	public String sexo;
	@Parameter(value = 3)
	public List<String> comidas;
	@Parameter(value = 4)
	public String[] sportes;
	@Parameter(value = 5)
	public String msg;

	@Before
	public void inicializa() {
		DriverFactory.getDriver()
				.get("file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html");
		page = new CampoTreinamentoPage();
	}

	@Parameters
	public static Collection<Object[]> getCollection() {
		return Arrays.asList(new Object[][] { { "", "", "", Arrays.asList(), new String[] {}, "Nome eh obrigatorio" },
				{ "Cristiano", "", "", Arrays.asList(), new String[] {}, "Sobrenome eh obrigatorio" },
				{ "Cristaino", "Silva", "", Arrays.asList(), new String[] {}, "Sexo eh obrigatorio" },
				{ "Cristiano", "Silva", "Masculino", Arrays.asList("Carne", "Vegetariano"), new String[] {},
						"Tem certeza que voce eh vegetariano" },
				{ "Cristiano", "Silva", "Masculino", Arrays.asList("Carne"),
						new String[] { "Futebol", "O que eh esporte" }, "Voce faz esporte ou nao?" }

		});
	}

	@Test
	public void deveValidarRegras() {

		page.setNome(nome);
		page.setsobrenome(sobreNome);
		if (sexo.equals("Masculino")) {
			page.setSexoMasculino();
		}
		if (sexo.equals("Feminio")) {
			page.setSexoFeminino();
		}

		if (comidas.contains("Carne")) {
			page.setComidaFavoritaCarne();
		}
		if (comidas.contains("Pizza")) {
			page.setComidaFavoritaPizza();
		}

		page.setComboSport("Corrida");
		page.setBotaoCadastrar();
		Assert.assertEquals(msg, page.getAlertSimples());
		page.setAcceptAlert();

	}

}
