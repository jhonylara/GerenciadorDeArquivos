package controle;

import java.util.ArrayList;

import modelo.AgenciaDAO;
import modelo.RegionalDAO;

public class PopularCombo {
	
	RegionalDAO regional = new RegionalDAO();
	AgenciaDAO agencia = new AgenciaDAO();
	
	public String[] prepararVetor(ArrayList<String> item) {
		String[] popula = new String[(item.size()+1)];
		popula[0] = "Selecione...";
		for (int i = 1; i < (item.size()+1); i++) {
			popula[i] = item.get(i-1).toString();
		}
		return popula;
	}

	
	public String[] gerarNomeRegional() {
		return prepararVetor(regional.comboRegional());
	}
	
	public String[] gerarNomeAgencia(String id){
		return prepararVetor(agencia.passarParametrosAgencia(id));
	}
	
	public String[] pegarNomeRegional(String nomeRegional) {
		
		return gerarNomeAgencia (String.valueOf(agencia.exibirRegionalID(nomeRegional)));
	}

	
}
