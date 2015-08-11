package informacoes;

import java.util.List;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import pojos.Pais;

import com.google.common.base.Function;
import com.google.common.base.Predicate;
import com.google.common.collect.FluentIterable;

public class ObterListaDePaises {

  private List<Pais> paises;

  public ObterListaDePaises(Document doc) {
    Elements paises = doc.select("td > b > a");
    list(paises);
  }

  public List<Pais> getPaises() {
    return paises;
  }

  private void list(Elements elements) {
    paises = FluentIterable.from(elements)
        .transform(new ParaPais())
        .filter(new FiltrarPaisesExistentes())
        .toList();
  }

  private class ParaPais implements Function<Element, Pais> {

    public Pais apply(Element element) {
      String title = element.text();
      String href = element.attr("href");

      Pais pais = new Pais();
      pais.setNome(title);
      pais.setUrl(href);

      return pais;
    }

  }

  private class FiltrarPaisesExistentes implements Predicate<Pais> {

    @Override
    public boolean apply(Pais pais) {
      return !pais.getUrl().contains("/w/index.php?");
    }

  }

}