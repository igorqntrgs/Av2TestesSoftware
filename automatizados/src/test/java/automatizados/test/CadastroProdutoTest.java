
package automatizados.test;

import static org.junit.Assert.assertEquals;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import automatizados.pageObject.CadastroDeProdutoPO;

@FixMethodOrder(MethodSorters.NAME_ASCENDING) 
public class CadastroProdutoTest extends BaseTest {

private static CadastroDeProdutoPO produtoPage;
	
	@BeforeClass
	public static void prepararTestes() {
		driver.get("file:///C:/Users/36124872022.2N/Downloads/sistema/sistema/produtos.html");
		produtoPage = new CadastroDeProdutoPO(driver);
		produtoPage.abrirModal();
	}
	
	@AfterClass
	public static void aposTestes() {
		produtoPage.inputCodigo.clear();
		produtoPage.inputNome.clear();
		produtoPage.inputQuantidade.clear();
		produtoPage.inputValor.clear();
		produtoPage.inputData.clear();
		produtoPage.buttonEditar.clear();
		produtoPage.buttonCriar.clear();
		produtoPage.buttonSair.clear();
		produtoPage.sair();
	}
	
	@Test
    public void TC001_MensagemErroAoCriarProdutoSemCamposObrigatorios() {
        
        produtoPage.preencherFormulario("", "", "", "", "");
        
        String mensagemErro = produtoPage.obterMensagem();
        assertEquals("Informe todos os campos obrigatórios", mensagemErro);
    }
	
	@Test
	public void TC002_EditarProdutoExistente() {	

		produtoPage.buttonEditar();
		produtoPage.preencherFormulario("Novo Código","Novo Nome", "Nova Quantidade", "Novo Valor", "Nova Data");
		produtoPage.buttonCriar();
		
	}
	
	@Test
	public void TC003_CancelarDuranteCriacaoOuEdicaoDeProduto() {
        
        produtoPage.sair();
        produtoPage.preencherFormulario("Novo Código","Novo Nome", "Nova Quantidade", "Novo Valor", "Nova Data");
        produtoPage.sair();
    }
}
