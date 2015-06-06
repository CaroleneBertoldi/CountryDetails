package historico;

import static historico.TipoDeOrdenacao.QUICKSORT;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import pojos.ItemDeHistorico;

import com.google.common.collect.ComparisonChain;
import com.google.common.collect.Lists;

public enum Historico {
  
  INSTANCIA;
  
  private List<ItemDeHistorico> itens;
	
	private TipoDeOrdenacao tipoDeOrdenacao = QUICKSORT;
	
	private boolean ordena;
	
	private Historico() {
	  itens = HistoricoTxt.INSTANCIA.getItensDeHistorico();
	}
	
	public void addPais(String pais) {
	  ItemDeHistorico itemDeHistorico = new ItemDeHistorico(pais);
	  itens.add(itemDeHistorico);
	  
		HistoricoTxt.INSTANCIA.addItemDeHistorico(itemDeHistorico.getNome(), itemDeHistorico.getData());
	}
	
	public TipoDeOrdenacao getTipoDeOrdenacao() {
	  return tipoDeOrdenacao;
	}
	
	public void setOrdena(boolean ordena) {
		this.ordena = ordena;
	}
	
	public void setTipoDeOrdenacao(TipoDeOrdenacao tipoDeOrdenacao) {
		this.tipoDeOrdenacao = tipoDeOrdenacao;
	}
	
  

	public List<ItemDeHistorico> getListaDePaises() {
	  if (ordena) {
	    List<ItemDeHistorico> itensOrdenados = new ArrayList<ItemDeHistorico>(itens);
	    tipoDeOrdenacao.sort(itensOrdenados, ItemDeHistoricoComparator.INSTANCE);
	    return itensOrdenados;
	  }
	  
	  return Lists.reverse(itens);
	}
	
	private enum ItemDeHistoricoComparator implements Comparator<ItemDeHistorico> {
	  INSTANCE;
	  @Override
	  public int compare(ItemDeHistorico itemDeHistorico1, ItemDeHistorico itemDeHistorico2) {
	    return ComparisonChain.start()
	        .compare(itemDeHistorico1.getNome(), itemDeHistorico2.getNome())
	        .compare(itemDeHistorico1.getDate(), itemDeHistorico2.getDate())
	        .result();	      
	  }
	}
	
}