import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({
	TesteRegraDeNegocio.class,
	TesteRegrasCadastraisParameterized.class,
	TesteCadastro.class,
	TesteCampoTreinamento.class
})
public class SuiteTeste {

}
