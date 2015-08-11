package historico;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import pojos.ItemDeHistorico;

import com.google.common.base.Throwables;

enum HistoricoTxt {

  INSTANCIA;

  private Logger LOGGER = Logger.getLogger(HistoricoTxt.class.getName());

  private File arquivo = new File("historico.txt");

  public void addItemDeHistorico(String pais, String data) {
    LOGGER.info("HistoricoTxt adicionando país...");

    try {
      FileWriter fw = new FileWriter(arquivo, true);
      BufferedWriter bw = new BufferedWriter(fw);

      bw.write(data + ", " + pais);
      bw.newLine();

      bw.close();
      fw.close();

    } catch (IOException e) {
      throw Throwables.propagate(e);
    }

    LOGGER.info("HistoricoTxt adicionando país completo!");
  }

  protected List<ItemDeHistorico> getItensDeHistorico() {
    LOGGER.info("HistoricoTxt lendo arquivo...");

    List<ItemDeHistorico> itens = new ArrayList<ItemDeHistorico>();

    try {
      if (arquivo.exists()) {
        FileReader fr = new FileReader(arquivo);
        BufferedReader br = new BufferedReader(fr);

        while (br.ready()) {
          String linha = br.readLine();
          String[] split = linha.split(", ");

          ItemDeHistorico itemDeHistorico = new ItemDeHistorico(split[1], split[0]);

          itens.add(itemDeHistorico);
        }

        br.close();
        fr.close();
      } else {
        arquivo.createNewFile();
      }

    } catch (IOException e) {
      throw Throwables.propagate(e);
    }

    LOGGER.info("HistoricoTxt lendo arquivo completo!");

    return itens;
  }

}