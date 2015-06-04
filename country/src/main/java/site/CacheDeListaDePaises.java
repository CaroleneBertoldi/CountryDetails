package site;

import static com.google.common.collect.Lists.newArrayList;
import informacoes.ObterListaDePaises;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.Collator;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.TreeMap;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import pojos.Pais;

public class CacheDeListaDePaises {

	private static CacheDeListaDePaises instancia;
	
	private Map<String, String> paisUrlMap = new TreeMap<String, String>(
				new Comparator<String>() {
					@Override
					public int compare(String o1, String o2) {
						return Collator.getInstance(new Locale("pt", "BR")).compare(o1, o2);
					}
				}
			);

	private CacheDeListaDePaises() throws MalformedURLException, IOException{
		Document doc = Jsoup.parse(new URL("http://pt.wikipedia.org/wiki/Lista_de_Estados_soberanos"), 0);
		ObterListaDePaises obterListaDePaises = new ObterListaDePaises(doc);
		List<Pais> paises = obterListaDePaises.getPaises();
		
		for (Pais pais : paises) {
			paisUrlMap.put(pais.getNome(), pais.getUrl());
		}
	}
	
	public static CacheDeListaDePaises instancia() throws MalformedURLException, IOException{
		return instancia != null ? instancia : new CacheDeListaDePaises();
	}
	
	public String getUrl(String pais) {
		return paisUrlMap.get(pais);
	}

	public List<String> getPaises() {
		return newArrayList(paisUrlMap.keySet());
	}

}