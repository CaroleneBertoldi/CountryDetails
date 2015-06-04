package pojos;

import org.joda.time.DateTime;


public class ItemDeHistorico {

	private String nome;
	private String data;

	public ItemDeHistorico(String pais) {
		nome = pais;
		data = new DateTime().toString("YYYY-MM-dd HH:mm:ss");
	}

	public void setData(String data) {
		this.data = data;
	}
	
	public String getNome() {
		return nome;
	}
	
	public String getData(){
		return data;
	}

}