package site;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.List;

import org.junit.Test;

public class TesteDeCacheDeListaDePaises {
	
	@Test
	public void obter_lista_de_paises() throws MalformedURLException, IOException {
		CacheDeListaDePaises parser = CacheDeListaDePaises.instancia();
		List<String> paises = parser.getPaises();
		
		assertThat(paises.size(), equalTo(333));
		
		assertThat(paises.get(0), equalTo("Abcásia"));
		assertThat(paises.get(1), equalTo("Abcázia"));
		assertThat(paises.get(332), equalTo("Zimbaué"));
	}

}