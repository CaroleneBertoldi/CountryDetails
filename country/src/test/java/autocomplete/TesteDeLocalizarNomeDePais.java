package autocomplete;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

import java.util.List;

import org.jsoup.nodes.Document;
import org.junit.Before;
import org.junit.Test;

import com.google.inject.Inject;


public class TesteDeLocalizarNomeDePais {
	
	@Inject
	private ParserPaises parser;
	
	@Before
	public void start()  {
		Document doc = HtmlsFalso.LISTA_ESTADOS_SOBERANOS;
	    parser  =  new ParserPaises(doc);
	 }
	
	@Test
	public void localizar_de_paises() {
		List<Pais> paises = parser.getPaises();
		
		String d0_1 = paises.get(0).getNome();
		String d1_1 = paises.get(1).getNome();
		String d343_1 = paises.get(343).getNome();
		
		String d0_2 = paises.get(0).getUrl();
		String d1_2 = paises.get(1).getUrl();
		String d343_2 = paises.get(343).getUrl();
		
		assertThat(paises.size(), equalTo(344));
		
		assertThat(d0_1, equalTo("Afeganistão"));
		assertThat(d1_1, equalTo("África do Sul"));
		assertThat(d343_1, equalTo("Transdniestre"));
		
		assertThat(d0_2, equalTo("/wiki/Afeganist%C3%A3o"));
		assertThat(d1_2, equalTo("/wiki/%C3%81frica_do_Sul"));
		assertThat(d343_2, equalTo("/wiki/Transdniestre"));
	}
	
}