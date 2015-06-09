package historico;

import static historico.TipoDeOrdenacao.BUBBLESORT;
import static historico.TipoDeOrdenacao.QUICKSORT;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

import java.io.File;
import java.util.Date;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import pojos.ItemDeHistorico;

public class TesteDeHistorico {

  private Historico historico;
  
  @Before
  public void start() throws Exception {
    historico = Historico.INSTANCIA;
    historico.setOrdena(false);
    historico.getListaDePaises().clear();
    
    String siria = "Síria";
    String brasil = "Brasil";
    String alemanha = "Alemanha";
    String equador = "Equador";
    String chile = "Chile";
    String africaDoSul = "África do Sul";

    historico.addPais(siria);
    historico.addPais(brasil);
    historico.addPais(alemanha);
    historico.addPais(equador);
    historico.addPais(chile);
    historico.addPais(africaDoSul);
    
    Thread.sleep(1000);
    historico.addPais(brasil);
  }
  
  @After
  public void end() {
    File arquivo = new File("historico.txt");
    arquivo.delete();
  }

  @Test
  public void historico_sem_ordenacao() {
    List<ItemDeHistorico> lista = historico.getListaDePaises();
    
    assertThat(lista.size(), equalTo(7));

    assertThat(lista.get(0).getNome(), equalTo("Brasil"));
    assertThat(lista.get(1).getNome(), equalTo("África do Sul"));
    assertThat(lista.get(2).getNome(), equalTo("Chile"));
    assertThat(lista.get(3).getNome(), equalTo("Equador"));
    assertThat(lista.get(4).getNome(), equalTo("Alemanha"));
    assertThat(lista.get(5).getNome(), equalTo("Brasil"));
    assertThat(lista.get(6).getNome(), equalTo("Síria"));
  }

  @Test
  public void historico_ordenacao_quicksort() throws Exception {
    historico.setOrdena(true);
    historico.setTipoDeOrdenacao(QUICKSORT);
    List<ItemDeHistorico> lista = historico.getListaDePaises();

    assertThat(lista.size(), equalTo(7));
    
    assertThat(lista.get(0).getNome(), equalTo("África do Sul"));
    assertThat(lista.get(1).getNome(), equalTo("Alemanha"));
    assertThat(lista.get(2).getNome(), equalTo("Brasil"));
    assertThat(lista.get(3).getNome(), equalTo("Brasil"));
    assertThat(lista.get(4).getNome(), equalTo("Chile"));
    assertThat(lista.get(5).getNome(), equalTo("Equador"));
    assertThat(lista.get(6).getNome(), equalTo("Síria"));
    
    assertThat(isMenorQue(lista.get(2).getDate(), lista.get(3).getDate()), equalTo(true));
  }

  @Test
  public void historico_ordenacao_bubblesort() throws Exception {
    historico.setOrdena(true);
    historico.setTipoDeOrdenacao(BUBBLESORT);
    List<ItemDeHistorico> lista = historico.getListaDePaises();
    
    assertThat(lista.size(), equalTo(7));

    assertThat(lista.get(0).getNome(), equalTo("África do Sul"));
    assertThat(lista.get(1).getNome(), equalTo("Alemanha"));
    assertThat(lista.get(2).getNome(), equalTo("Brasil"));
    assertThat(lista.get(3).getNome(), equalTo("Brasil"));
    assertThat(lista.get(4).getNome(), equalTo("Chile"));
    assertThat(lista.get(5).getNome(), equalTo("Equador"));
    assertThat(lista.get(6).getNome(), equalTo("Síria"));
    
    assertThat(isMenorQue(lista.get(2).getDate(), lista.get(3).getDate()), equalTo(true));
  }

  private boolean isMenorQue(Date date1, Date date2) {
    return date1.before(date2);
  }

}