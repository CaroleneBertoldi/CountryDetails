package site;

import static com.google.common.collect.Lists.newArrayList;
import informacoes.ObterListaDePaises;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.logging.Logger;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import pojos.NomeDePaisComparator;
import pojos.Pais;

import com.google.common.base.Throwables;

public enum CacheDeListaDePaises {

  INSTANCIA;

  private static final String URL_WIKIPEDIA = "http://pt.wikipedia.org";
  private static final String URL_LISTA_DE_PAISES = URL_WIKIPEDIA + "/wiki/Lista_de_Estados_soberanos";

  private Logger LOGGER = Logger.getLogger(CacheDeListaDePaises.class.getName());

  private Map<String, String> paisUrlMap = new TreeMap<String, String>(NomeDePaisComparator.INSTANCE);

  private CacheDeListaDePaises() {
    LOGGER.info("CacheDeListaDePaises carregando países...");

    try {
      Document doc = Jsoup.parse(new URL(URL_LISTA_DE_PAISES), 0);
      ObterListaDePaises obterListaDePaises = new ObterListaDePaises(doc);
      List<Pais> paises = obterListaDePaises.getPaises();

      for (Pais pais : paises) {
        paisUrlMap.put(pais.getNome(), pais.getUrl());
      }

    } catch (IOException e) {
      throw Throwables.propagate(e);
    }

    LOGGER.info("CacheDeListaDePaises carregando países completo!");
  }

  public String getUrl(String pais) {
    return URL_WIKIPEDIA + paisUrlMap.get(pais);
  }

  public List<String> getPaises() {
    return newArrayList(paisUrlMap.keySet());
  }

}