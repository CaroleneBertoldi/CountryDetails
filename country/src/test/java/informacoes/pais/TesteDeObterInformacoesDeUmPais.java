package informacoes.pais;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

import org.jsoup.nodes.Document;
import org.junit.Before;
import org.junit.Test;

import util.HtmlsFalso;
import autocomplete.Pais;

import com.google.inject.Inject;

public class TesteDeObterInformacoesDeUmPais {

	@Inject
	private ObterInformacoesDeUmPais parser;
	
	@Before
	public void start()  {
		Document doc = HtmlsFalso.PAGINA_DE_UM_PAIS;
	    parser = new ObterInformacoesDeUmPais(doc);
	 }
	
	@Test
	public void obter_informacoes_de_um_pais() {
		Pais pais = new Pais();
		pais.setNome("Brasil");
		pais.setUrl("wikipedia/pagina-de-um-pais.html");
		
		InformacoesDeUmPais informacoes = parser.carregarInformacoes(pais, "http://fake");
		
		assertThat(informacoes.getNome(), equalTo("Brasil"));
		assertThat(informacoes.getUrl(), equalTo("wikipedia/pagina-de-um-pais.html"));
		
		//informações obrigatórias
		assertThat(informacoes.getBandeira(), equalTo("http://upload.wikimedia.org/wikipedia/commons/thumb/0/05/Flag_of_Brazil.svg/125px-Flag_of_Brazil.svg.png"));
		assertThat(informacoes.getLocalizacaoNoMundo(), equalTo("http://upload.wikimedia.org/wikipedia/commons/thumb/b/bc/BRA_orthographic.svg/260px-BRA_orthographic.svg.png"));
		assertThat(informacoes.getCapital(), equalTo("Brasília"));
		assertThat(informacoes.getLinguaOficial(), equalTo("Português"));
		assertThat(informacoes.getArea(), equalTo("8 515 767,049"));
		
		//informações adicionais
		assertThat(informacoes.getPopulacao(), equalTo("202 768 562"));
		assertThat(informacoes.getPIB(), equalTo("US$ 2,244 trilhões"));
		assertThat(informacoes.getIDH(), equalTo("0,744"));
		assertThat(informacoes.getMoeda(), equalTo("Real (BRL)"));
		assertThat(informacoes.getCodigoISO(), equalTo("BRA"));
	}

}