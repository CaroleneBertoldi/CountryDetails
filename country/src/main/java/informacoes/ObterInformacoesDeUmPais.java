package informacoes;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import pojos.InformacoesDeUmPais;

public class ObterInformacoesDeUmPais {

  private Document doc;

  public ObterInformacoesDeUmPais(Document doc) {
    this.doc = doc;
  }

  public InformacoesDeUmPais carregarInformacoes(String pais) {
    InformacoesDeUmPais informacoes = new InformacoesDeUmPais();

    informacoes.setPais(pais);
    extraindoBandeira(informacoes);
    extraindoLocalizacao(informacoes);
    extraindoCapital(informacoes);
    extraindoLinguaOficial(informacoes);
    extraindoArea(informacoes);
    extraindoPopulacao(informacoes);
    extraindoPIB(informacoes);
    extraindoIDH(informacoes);
    extraindoMoeda(informacoes);
    extraindoCodigoISO(informacoes);

    return informacoes;
  }

  private void extraindoBandeira(InformacoesDeUmPais informacoes) {
    String src = extrairImagem(informacoes, "src", "125px-Flag_of");
    informacoes.setBandeira(src);
  }

  private void extraindoLocalizacao(InformacoesDeUmPais informacoes) {
    String src = extrairImagem(informacoes, "alt", "Localização");
    informacoes.setLocalizacaoNoMundo(src);
  }

  private void extraindoCapital(InformacoesDeUmPais informacoes) {
    String capital = extraindoDadosDaMesmaLinha("Capital");
    capital = limpandoInformacao(capital, '[', ']');
    capital = limpandoInformacao(capital, '°');
    capital = limpandoInformacao(capital, 'º');
    capital = limpaNumeros(capital);
    capital = limpaSobrescritos(capital);
    informacoes.setCapital(capital);
  }

  private void extraindoLinguaOficial(InformacoesDeUmPais informacoes) {
    String lingua = extraindoDadosDaMesmaLinha("Língua oficial");
    lingua = limpandoInformacao(lingua, '[', ']');
    lingua = limpandoInformacao(lingua, '*');
    lingua = limpaNumeros(lingua);
    lingua = limpaSobrescritos(lingua);
    informacoes.setLinguaOficial(lingua);
  }

  private void extraindoMoeda(InformacoesDeUmPais informacoes) {
    String moeda = extraindoDadosDaMesmaLinha("Moeda");
    moeda = limpandoInformacao(moeda, '[', ']');
    moeda = limpaNumeros(moeda);
    moeda = limpaSobrescritos(moeda);
    informacoes.setMoeda(moeda);
  }

  private void extraindoArea(InformacoesDeUmPais informacoes) {
    String area = extraindoDadosDeColunaDaProximaLinha("Lista de países e territórios por área");
    area = limpandoInformacao(area, '[', ']');
    area = limpandoInformacao(area, '(', ')');
    informacoes.setArea(area);
  }

  private void extraindoPopulacao(InformacoesDeUmPais informacoes) {
    String populacao = extraindoDadosDeColunaDaProximaLinha("População");
    populacao = limpandoInformacao(populacao, '[', ']');
    populacao = limpandoInformacao(populacao, '(', ')');
    populacao = limpaSobrescritos(populacao);
    informacoes.setPopulacao(populacao);
  }

  private void extraindoPIB(InformacoesDeUmPais informacoes) {
    String pib = extraindoDadosDeColunaDaProximaLinha("Produto interno bruto");
    pib = limpandoInformacao(pib, '[', ']');
    pib = limpandoInformacao(pib, '(', ')');
    pib = limpandoInformacao(pib, '*');
    pib = limpaSobrescritos(pib);
    informacoes.setPIB(pib);
  }

  private void extraindoIDH(InformacoesDeUmPais informacoes) {
    String idh = extraindoDadosDaMesmaLinha("Índice de Desenvolvimento Humano");
    idh = limpandoInformacao(idh, '[', ']');
    idh = limpandoInformacao(idh, '(', ')');
    idh = limpandoInformacao(idh, ' ');
    idh = limpaSobrescritos(idh);
    informacoes.setIDH(idh);
  }

  private void extraindoCodigoISO(InformacoesDeUmPais informacoes) {
    String codigo = extraindoDadosDaMesmaLinha("ISO 3166-1");
    informacoes.setCodigoISO(codigo);
  }

  private String extrairImagem(InformacoesDeUmPais informacoes, String atributo, String valor) {
    Elements imgs = doc.getElementsByTag("img");

    String src = "";
    for (Element element : imgs) {
      String attr = element.attr(atributo);
      if (attr.contains(valor)) {
        src = element.absUrl("src");
        break;
      }
    }

    return src;
  }

  private String extraindoDadosDaMesmaLinha(String atributo) {
    Elements elements = doc.select("tr");

    String valor = "";
    for (Element element : elements) {
      Elements ancora = element.select("a");
      String attr = ancora.attr("title");
      if (attr.equals(atributo)) {
        Elements td = element.select("td");
        if (td.size() > 1) {
          td.get(1).getElementsByTag("sup").remove();
          valor = td.get(1).text();
        }
      }
    }

    return valor;
  }

  private String extraindoDadosDeColunaDaProximaLinha(String atributo) {
    Elements elements = doc.select("tr");

    String valor = "";
    for (int i = 0; i < elements.size(); i++) {
      Elements ancora = elements.get(i).select("a");
      String attr = ancora.attr("title");
      if (attr.equals(atributo)) {
        Elements td = elements.get(i + 1).select("td");
        valor = td.get(1).text();
      }
    }

    return valor;
  }

  private String limpandoInformacao(String informacao, char abre, char fecha) {
    informacao = toCode(informacao);
    informacao = informacao.replaceAll("\\" + abre + ".*\\" + fecha, "");
    informacao = informacao.replaceAll("[ ]+", " ").trim();
    return informacao;
  }

  private String limpandoInformacao(String informacao, char inicio) {
    informacao = toCode(informacao);
    informacao = informacao.replaceAll("\\" + inicio + ".*", "");
    informacao = informacao.replaceAll("[ ]+", " ").trim();
    return informacao;
  }

  private String limpaNumeros(String informacao) {
    informacao = toCode(informacao);
    informacao = informacao.replaceAll("[0-9]+", "");
    informacao = informacao.replaceAll("[ ]+", " ").trim();
    return informacao;
  }

  private String limpaSobrescritos(String informacao) {
    informacao = toCode(informacao);
    informacao = informacao.replaceAll("[¹²³⁴⁵⁶⁷⁸⁹]+", "");
    informacao = informacao.replaceAll("[ ]+", " ").trim();
    return informacao;
  }

  private String toCode(String prova) {
    return prova.replace((char) 160, ' ').trim();
  }

}