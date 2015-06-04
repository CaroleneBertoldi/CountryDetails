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

	public InformacoesDeUmPais carregarInformacoes(String pais, String dominio) {
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
		String src = extrairImagem(informacoes, "Bandeira");
		informacoes.setBandeira(src);
	}
	
	private void extraindoLocalizacao(InformacoesDeUmPais informacoes) {
		String src = extrairImagem(informacoes, "Localização");
		informacoes.setLocalizacaoNoMundo(src);
	}
	
	private void extraindoCapital(InformacoesDeUmPais informacoes) {
		String capital = extraindoDadosDaMesmaLinha("Capital");
		capital = capital.replaceAll("[0-9]*", "");
		capital = capital.replaceAll(" °'\"S °'\"O", "");
		informacoes.setCapital(capital);
	}
	
	private void extraindoLinguaOficial(InformacoesDeUmPais informacoes) {
		String lingua = extraindoDadosDaMesmaLinha("Língua oficial");
		int limite = lingua.indexOf("[");
		lingua = lingua.substring(0, limite);
		informacoes.setLinguaOficial(lingua);
	}
	
	private void extraindoMoeda(InformacoesDeUmPais informacoes) {
		String moeda = extraindoDadosDaMesmaLinha("Moeda");
		informacoes.setMoeda(moeda);
	}
	
	private void extraindoArea(InformacoesDeUmPais informacoes) {
		String area = extraindoDadosDeColunaDaProximaLinha("Lista de países e territórios por área");
		int limite = area.indexOf("[");
		area = area.substring(0, limite);
		informacoes.setArea(toCode(area));
	}
	
	private void extraindoPopulacao(InformacoesDeUmPais informacoes) {
		String populacao = extraindoDadosDeColunaDaProximaLinha("População");
		int limite = populacao.indexOf("[");
		populacao = populacao.substring(0, limite);
		informacoes.setPopulacao(toCode(populacao));
	}
	
	private void extraindoPIB(InformacoesDeUmPais informacoes) {
		String pib = extraindoDadosDeColunaDaProximaLinha("Produto interno bruto");
		int limite = pib.indexOf("*");
		pib = pib.substring(0, limite);
		informacoes.setPIB(toCode(pib));
	}
	
	private void extraindoIDH(InformacoesDeUmPais informacoes) {
		String idh = extraindoDadosDaMesmaLinha("Índice de Desenvolvimento Humano");
		int limite = idh.indexOf("(") - 1;
		idh = idh.substring(0, limite);
		informacoes.setIDH(idh);
	}
	
	private void extraindoCodigoISO(InformacoesDeUmPais informacoes) {
		String codigo = extraindoDadosDaMesmaLinha("ISO 3166-1");
		informacoes.setCodigoISO(codigo);
	}
	
	private String extrairImagem(InformacoesDeUmPais informacoes, String atributo) {
		Elements imgs = doc.getElementsByTag("img");
		
		String src = "";
		for (Element element : imgs) {
			String attr = element.attr("alt");
			if (attr.contains(atributo)) {
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
			Elements amcora = element.select("a");
			String attr = amcora.attr("title");
			if (attr.equals(atributo)) {
				Elements td = element.select("td");
				valor = td.get(1).text();
			}
		}
		
		return valor;
	}
	
	private String extraindoDadosDeColunaDaProximaLinha(String atributo) {
		Elements elements = doc.select("tr");
		
		String valor = "";
		for (int i = 0; i < elements.size(); i++) {
			Elements amcora = elements.get(i).select("a");
			String attr = amcora.attr("title");
			if (attr.equals(atributo)) {
				Elements td = elements.get(i + 1).select("td");
				valor = td.get(1).text();
			}
		}
		
		return valor;
	}

	private String toCode(String prova) {
	  return prova.replace((char) 160,' ');
	}

}