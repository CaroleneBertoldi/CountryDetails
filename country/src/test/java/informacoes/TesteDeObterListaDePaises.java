package informacoes;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;
import informacoes.ObterListaDePaises;

import java.util.List;

import org.jsoup.nodes.Document;
import org.junit.Before;
import org.junit.Test;

import pojos.Pais;
import util.HtmlsFalso;


public class TesteDeObterListaDePaises {
	
	private ObterListaDePaises parser;
	
	@Before
	public void start()  {
	  Document doc = HtmlsFalso.LISTA_ESTADOS_SOBERANOS;
	  parser = new ObterListaDePaises(doc);
	}
	
	@Test
	public void obter_lista_de_paises() {
		List<Pais> paises = parser.getPaises();
		
		assertThat(paises.size(), equalTo(339));
		
		assertThat(paises.get(0).getNome(), equalTo("Afeganistão"));
		assertThat(paises.get(1).getNome(), equalTo("África do Sul"));
		assertThat(paises.get(71).getNome(), equalTo("China"));
		assertThat(paises.get(338).getNome(), equalTo("Transdniestre"));
		
		assertThat(paises.get(0).getUrl(), equalTo("/wiki/Afeganist%C3%A3o"));
		assertThat(paises.get(1).getUrl(), equalTo("/wiki/%C3%81frica_do_Sul"));
		assertThat(paises.get(71).getUrl(), equalTo("/wiki/Rep%C3%BAblica_Popular_da_China"));
		assertThat(paises.get(338).getUrl(), equalTo("/wiki/Transdniestre"));
	}
	
}