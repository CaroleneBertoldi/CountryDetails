package pojos;

import org.joda.time.DateTime;


public class ItemDeHistorico {

	private String nome;
	private String url;
	private DateTime data;

	public ItemDeHistorico(Pais pais) {
		nome = pais.getNome();
		url = pais.getUrl();
		
		data = new DateTime();
	}

	public String getNome() {
		return nome;
	}
	
	public String getUrl() {
		return url;
	}
	
	public String getData(){
		return data.toString("YYYY-MM-dd HH:mm:ss");
	}

}