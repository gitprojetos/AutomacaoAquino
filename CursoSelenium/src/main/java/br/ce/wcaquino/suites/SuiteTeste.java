package br.ce.wcaquino.suites;
import org.junit.AfterClass;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import br.ce.wcaquino.core.DriverFactory;
import br.ce.wcaquino.test.*;

@RunWith(Suite.class)
@SuiteClasses({
	//TesteRegraDeNegocio.class,
	TesteRegrasCadastraisParameterized.class,
	TesteCadastro.class,
	//TesteCampoTreinamento.class
})
public class SuiteTeste {

	@AfterClass
	public static void finalizaTudo() {
		DriverFactory.killDriver();
	}
	
}
