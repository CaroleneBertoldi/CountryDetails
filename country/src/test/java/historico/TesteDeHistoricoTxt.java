package historico;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.util.List;

import org.junit.Test;

import pojos.ItemDeHistorico;

public class TesteDeHistoricoTxt {

  @Test
  public void cria_se_nao_existe() {
    File arquivo = new File("historico.txt");
    arquivo.delete();

    assertFalse(arquivo.exists());

    HistoricoTxt.INSTANCIA.getItensDeHistorico();

    assertTrue(arquivo.exists());

    arquivo.delete();
  }

  @Test
  public void ler_e_escrever_em_arquivo() {
    File arquivo = new File("historico.txt");

    HistoricoTxt txt = HistoricoTxt.INSTANCIA;
    txt.addItemDeHistorico("Brasil", "01/01/2015 00:00:00");
    txt.addItemDeHistorico("Argentina", "01/01/2015 00:01:00");

    List<ItemDeHistorico> itens = txt.getItensDeHistorico();

    assertThat(itens.size(), equalTo(2));
    assertThat(itens.get(0).getNome(), equalTo("Brasil"));
    assertThat(itens.get(1).getNome(), equalTo("Argentina"));

    arquivo.delete();
  }

}