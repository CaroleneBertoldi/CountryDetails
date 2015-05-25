package autocomplete;

import static com.google.common.collect.Lists.newArrayList;

import java.util.List;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

class ParserPaises {

	private final Document doc;
	
	public ParserPaises(Document doc) {
	  this.doc = doc;
	}
	
	public List<Pais> getPaises() {
		Elements paises = doc.select("td > b > a");
		return list(paises);
	}

	private List<Pais> list(Elements elements){
		List<Pais> list = newArrayList();
		 
		for (Element element : elements) {
			String title = element.attr("title");
			String href = element.attr("href");
			
			Pais pais = new Pais();
			pais.setNome(title);
			pais.setUrl(href);
			
			list.add(pais);
		}
			
		return list;
	}

}