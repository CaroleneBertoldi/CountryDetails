package autocomplete;

import java.io.IOException;
import java.net.URL;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import com.google.common.base.Charsets;
import com.google.common.base.Throwables;
import com.google.common.io.Resources;

public class HtmlsFalso {

	  public static final Document LISTA_ESTADOS_SOBERANOS = html("/wikipedia/lista-de-estados-soberanos.html");

	  private HtmlsFalso() {
	  }

	  private static Document html(String resourceName) {
	    try {
	      URL url = Resources.getResource(HtmlsFalso.class, resourceName);
	      String html = Resources.toString(url, Charsets.UTF_8);
	      return Jsoup.parse(html, "http://fake");
	    } catch (IOException e) {
	      throw Throwables.propagate(e);
	    }
	  }

}