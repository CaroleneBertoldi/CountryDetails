package site;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

import java.util.List;

import org.junit.Ignore;
import org.junit.Test;

// Teste acessa wikipédia em tempo real - executado última vez em 09/06/15
// Desativado por estar passivel de quebrar caso dados da wikipédia sejam modificados

@Ignore
public class TesteDeCacheDeListaDePaises {

  @Test
  public void obter_lista_de_paises() {
    CacheDeListaDePaises cache = CacheDeListaDePaises.INSTANCIA;
    List<String> paises = cache.getPaises();

    assertThat(paises.size(), equalTo(339));

    assertThat(paises.get(0), equalTo("Abcásia"));
    assertThat(paises.get(1), equalTo("Abcázia"));
    assertThat(paises.get(338), equalTo("Zimbaué"));
  }

}