package pojos;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

import org.joda.time.DateTime;
import org.junit.Test;

import pojos.ItemDeHistorico;
import pojos.Pais;

public class TesteDeItemDeHistorico {
	
	@Test
	public void teste_de_item_de_um_historico(){
		Pais pais = new Pais();
		pais.setNome("Brasil");
		pais.setUrl("wikipedia/pagina-de-um-pais.html");
		
		ItemDeHistorico item = new ItemDeHistorico(pais);

		assertThat(item.getNome(), equalTo("Brasil"));
		assertThat(item.getUrl(), equalTo("wikipedia/pagina-de-um-pais.html"));
		assertThat(item.getData(), equalTo(new DateTime().toString("YYYY-MM-dd HH:mm:ss")));
	}

}