package historico;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import pojos.ItemDeHistorico;

import com.google.common.base.Throwables;

public class HistoricoTxt {
	
	private static HistoricoTxt instancia;

	private File arquivo = new File("historico.txt");
	private List<ItemDeHistorico> itens = new ArrayList<ItemDeHistorico>();
	
	private HistoricoTxt() {
		lerAquivo();
	}

	public static HistoricoTxt instancia() {
		return instancia != null ? instancia : new HistoricoTxt();
	}
	
	public void addItemDeHistorico(String pais) {
		ItemDeHistorico itemDeHistorico = new ItemDeHistorico(pais);
		
		try {
			FileWriter fw = new FileWriter(arquivo, true);
			BufferedWriter bw = new BufferedWriter(fw);
			
			bw.write(itemDeHistorico.getNome() + ", " + itemDeHistorico.getData());
			bw.newLine();
			
			bw.close();
			fw.close();
			
			lerAquivo();
		} catch (IOException e) {
			throw Throwables.propagate(e);
		}
	}
	
	public List<ItemDeHistorico> getItensDeHistorico() {
		return itens;
	}
	
	private void lerAquivo() {
		try {
			if (arquivo.exists()) {
				FileReader fr = new FileReader(arquivo);
				BufferedReader br = new BufferedReader(fr);
				
				itens = new ArrayList<ItemDeHistorico>();
				while(br.ready()){
					String linha = br.readLine();
					String[] split = linha.split(", ");
					
					ItemDeHistorico itemDeHistorico = new ItemDeHistorico(split[0]);
					itemDeHistorico.setData(split[1]);
					
					itens.add(itemDeHistorico);
				}
				
				br.close();
				fr.close();
			}else {
				arquivo.createNewFile();
			}
		} catch (IOException e) {
			throw Throwables.propagate(e);
		}
	}

}