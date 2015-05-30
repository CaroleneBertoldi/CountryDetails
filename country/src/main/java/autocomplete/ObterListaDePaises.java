package autocomplete;

import java.util.List;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.google.common.base.Function;
import com.google.common.collect.Lists;

class ObterListaDePaises {

	private List<Pais> paises;
	
	public ObterListaDePaises(Document doc) {
	  Elements paises = doc.select("td > b > a");
	  list(paises);
	}
	
	public List<Pais> getPaises() {
		return paises;
	}

	private void list(Elements elements){
		paises = Lists.transform(elements, new ParaPais());
	}
	
	private class ParaPais implements Function<Element, Pais> {

		public Pais apply(Element element) {
			String title = element.attr("title");
			String href = element.attr("href");
			
			Pais pais = new Pais();
			pais.setNome(title);
			pais.setUrl(href);
			
			return pais;
		}
		
	}

}