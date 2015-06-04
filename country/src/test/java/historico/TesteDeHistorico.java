package historico;

import static historico.TipoDeOrdenacao.BUBBLESORT;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import pojos.ItemDeHistorico;

public class TesteDeHistorico {

  private Historico historico;

  @Before
  public void start() {
    historico = new Historico();

    String siria = "Síria";
    String brasil = "Brasil";
    String alemanha = "Alemanha";
    String equador = "Equador";
    String chile = "Chile";

    historico.addPais(siria);
    historico.addPais(brasil);
    historico.addPais(alemanha);
    historico.addPais(equador);
    historico.addPais(chile);
  }

  @Test
  public void historico_sem_ordenacao() {
    List<ItemDeHistorico> lista = historico.getListaDePaises();

    assertThat(lista.get(0).getNome(), equalTo("Síria"));
    assertThat(lista.get(1).getNome(), equalTo("Brasil"));
    assertThat(lista.get(2).getNome(), equalTo("Alemanha"));
    assertThat(lista.get(3).getNome(), equalTo("Equador"));
    assertThat(lista.get(4).getNome(), equalTo("Chile"));
  }

  @Test
  public void historico_ordenacao_quicksort() {
    historico.setOrdena(true);
    List<ItemDeHistorico> lista = historico.getListaDePaises();

    assertThat(lista.get(0).getNome(), equalTo("Alemanha"));
    assertThat(lista.get(1).getNome(), equalTo("Brasil"));
    assertThat(lista.get(2).getNome(), equalTo("Chile"));
    assertThat(lista.get(3).getNome(), equalTo("Equador"));
    assertThat(lista.get(4).getNome(), equalTo("Síria"));
  }

  @Test
  public void historico_ordenacao_bubblesort() {
    historico.setOrdena(true);
    historico.setTipoDeOrdenacao(BUBBLESORT);
    List<ItemDeHistorico> lista = historico.getListaDePaises();

    assertThat(lista.get(0).getNome(), equalTo("Alemanha"));
    assertThat(lista.get(1).getNome(), equalTo("Brasil"));
    assertThat(lista.get(2).getNome(), equalTo("Chile"));
    assertThat(lista.get(3).getNome(), equalTo("Equador"));
    assertThat(lista.get(4).getNome(), equalTo("Síria"));
  }

}