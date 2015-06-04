package historico;

import static historico.TipoDeOrdenacao.QUICKSORT;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import pojos.ItemDeHistorico;

public class Historico {
	
	private List<ItemDeHistorico> itens;
	
	private TipoDeOrdenacao tipoDeOrdenacao = QUICKSORT;
	
	private boolean ordena;

	public Historico() {
		HistoricoTxt txt = HistoricoTxt.instancia();
		itens = txt.getItensDeHistorico() != null 
				? txt.getItensDeHistorico()
				: new ArrayList<ItemDeHistorico>();;
	}

	public void addPais(String pais) {
		ItemDeHistorico novoItem = new ItemDeHistorico(pais);
		itens.add(novoItem);
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
	    tipoDeOrdenacao.sort(itensOrdenados, new ItemDeHistoricoComparator());
	    return itensOrdenados;
	  }
	  
	  return itens;
	}
	
	private static class ItemDeHistoricoComparator implements Comparator<ItemDeHistorico> {

	    @Override
	    public int compare(ItemDeHistorico itemDeHistorico1, ItemDeHistorico itemDeHistorico2) {
	      return itemDeHistorico1.getNome().compareTo(itemDeHistorico2.getNome());
	    }
	  
	}
	
}