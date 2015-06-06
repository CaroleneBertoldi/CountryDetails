package site;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

import java.util.List;

import org.junit.Test;

public class TesteDeCacheDeListaDePaises {
	
	@Test
	public void obter_lista_de_paises() {
		CacheDeListaDePaises cache = CacheDeListaDePaises.INSTANCIA;
		List<String> paises = cache.getPaises();
		
		assertThat(paises.size(), equalTo(333));
		
		assertThat(paises.get(0), equalTo("Abcásia"));
		assertThat(paises.get(1), equalTo("Abcázia"));
		assertThat(paises.get(332), equalTo("Zimbaué"));
	}

}