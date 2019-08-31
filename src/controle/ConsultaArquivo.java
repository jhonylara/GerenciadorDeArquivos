package controle;

import modelo.AgenciaDAO;

public class ConsultaArquivo {

	private static Integer agencia;
	private static String data = new String();
	private static String nome = new String();
	private static String usuario = new String();

	public ConsultaArquivo() {

	}

	public String getAgencia() {
		return agencia.toString();
	}

	public void setAgencia(String agencia) {
		AgenciaDAO agenciaSelec = new AgenciaDAO();
		Integer valorAgencia = agenciaSelec.exibirValorAgencia(agencia);
		ConsultaArquivo.agencia = valorAgencia;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		ConsultaArquivo.data = data;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		ConsultaArquivo.nome = nome;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		ConsultaArquivo.usuario = usuario;
	}
}
