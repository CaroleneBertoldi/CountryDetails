package historico;

import static historico.TipoDeOrdenacao.QUICKSORT;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import pojos.ItemDeHistorico;
import pojos.Pais;

import com.google.inject.Singleton;

@Singleton
public class Historico {
	
	private List<ItemDeHistorico> itens = new ArrayList<ItemDeHistorico>();
	
	private TipoDeOrdenacao tipoDeOrdenacao = QUICKSORT;
	
	private boolean ordena;

	public void addPais(Pais pais) {
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