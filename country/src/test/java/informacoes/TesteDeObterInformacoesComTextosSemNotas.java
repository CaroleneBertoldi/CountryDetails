package informacoes;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

import org.jsoup.nodes.Document;
import org.junit.Before;
import org.junit.Test;

import pojos.InformacoesDeUmPais;
import util.HtmlsFalso;

public class TesteDeObterInformacoesComTextosSemNotas {

  private ObterInformacoesDeUmPais parser;

  @Before
  public void start() {
    Document doc = HtmlsFalso.PAGINA_DE_UM_PAIS_TEXTOS_SEM_NOTAS;
    parser = new ObterInformacoesDeUmPais(doc);
  }

  @Test
  public void obter_pais_texto_sem_nota() {
    String pais = "Portugal";
    InformacoesDeUmPais informacoes = parser.carregarInformacoes(pais);

    assertThat(informacoes.getCapital(), equalTo("Lisboa"));
    assertThat(informacoes.getLinguaOficial(), equalTo("Português"));
    assertThat(informacoes.getMoeda(), equalTo("Euro (EUR)"));
  }

}