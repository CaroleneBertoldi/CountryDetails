package pojos;



public class InformacoesDeUmPais {

	private String nome;
	private String bandeira;
	private String localizacao;
	private String capital;
	private String lingua;
	private String area;
	private String populacao;
	private String pib;
	private String idh;
	private String moeda;
	private String iso;

	public void setPais(String pais) {
		nome = pais;
	}
	
	public void setBandeira(String bandeira) {
		this.bandeira = bandeira;
	}
	
	public void setLocalizacaoNoMundo(String localizacao){
		this.localizacao = localizacao;
	}
	
	public void setCapital(String capital){
		this.capital = capital;
	}
	
	public void setLinguaOficial(String lingua){
		this.lingua = lingua;
	}
	
	public void setArea(String area){
		this.area = area;
	}
	
	public void setPopulacao(String populacao){
		this.populacao = populacao;
	}
	
	public void setPIB(String pib){
		this.pib = pib;
	}
	
	public void setIDH(String idh){
		this.idh = idh;
	}
	
	public void setMoeda(String moeda){
		this.moeda = moeda;
	}
	
	public void setCodigoISO(String iso){
		this.iso = iso;
	}
	
	public String getNome() {
		return nome;
	}

	public String getBandeira() {
		return bandeira;
	}

	public String getLocalizacaoNoMundo() {
		return localizacao;
	}

	public String getCapital() {
		return capital;
	}

	public String getLinguaOficial() {
		return lingua;
	}

	public String getArea() {
		return area;
	}

	public String getPopulacao() {
		return populacao;
	}

	public String getPIB() {
		return pib;
	}

	public String getIDH() {
		return idh;
	}

	public String getMoeda() {
		return moeda;
	}

	public String getCodigoISO() {
		return iso;
	}

}