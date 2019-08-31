package visao.principal;

import java.awt.BorderLayout;
import java.awt.CardLayout;

import javax.swing.JPanel;

import visao.menu.MenuPrincipal;
import visao.telas.TelaAgenciaCadastrar;
import visao.telas.TelaAgenciaConsulta;
import visao.telas.TelaArquivoConsulta;
import visao.telas.TelaArquivoEnviar;
import visao.telas.TelaUsuarioCadastrar;
import visao.telas.TelaUsuarioConsulta;
import visao.telas.TelaUsuarioPerfil;

public class TelaPrincipal extends javax.swing.JFrame {
	private static final long serialVersionUID = 23;

	private JPanel gerarTelas;
	private TelaArquivoEnviar telaArqivoEnviar;
	private TelaArquivoConsulta telaArquivoConsulta;
	private TelaAgenciaCadastrar telaAgenciaCadastrar;
	private TelaAgenciaConsulta telaAgenciaConsulta;
	private TelaUsuarioPerfil telaUsuarioPerfil;
	private TelaUsuarioConsulta telaUsuarioConsulta;
	private TelaUsuarioCadastrar telaUsuarioCadastrar;

	public TelaPrincipal() {

		construirTelaPrincipal();

		construirMenu();

		construirTelaArquivoEnviar();

	}

	private void construirMenu() {
		MenuPrincipal barra = new MenuPrincipal(this);

		setJMenuBar(barra.getJMenuBar());

	}

	private void exibirTela(String indicePainel) {
		CardLayout cl = (CardLayout) (gerarTelas.getLayout());

		cl.show(gerarTelas, indicePainel);

	}

	private void construirTelaPrincipal() {
		setLayout(new BorderLayout());
		setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
		setTitle("GDA - GERENCIADOR DE ARQUIVOS");
		setResizable(false);
		setSize(900, 600);
		setLocationRelativeTo(null);

		gerarTelas = new JPanel(new CardLayout());
		add(gerarTelas, BorderLayout.CENTER);
	}

	public void construirTelaArquivoEnviar() {
		telaArqivoEnviar = new TelaArquivoEnviar();
		gerarTelas.add(telaArqivoEnviar, TelaArquivoEnviar.NOME_TELA);
		add(gerarTelas, BorderLayout.CENTER);
	}

	public void construirTelaArquivoConsulta() {
		telaArquivoConsulta = new TelaArquivoConsulta();
		gerarTelas.add(telaArquivoConsulta, TelaArquivoConsulta.NOME_TELA);
		add(gerarTelas, BorderLayout.CENTER);
	}

	public void construirTelaAgenciaCadastrar() {
		telaAgenciaCadastrar = new TelaAgenciaCadastrar();
		gerarTelas.add(telaAgenciaCadastrar, TelaAgenciaCadastrar.NOME_TELA);
		add(gerarTelas, BorderLayout.CENTER);
	}

	public void construirTelaAgenciaConsulta() {
		telaAgenciaConsulta = new TelaAgenciaConsulta();
		gerarTelas.add(telaAgenciaConsulta, TelaAgenciaConsulta.NOME_TELA);
		add(gerarTelas, BorderLayout.CENTER);
	}

	public void construirTelaUsuarioCadastrar() {
		telaUsuarioCadastrar = new TelaUsuarioCadastrar();
		gerarTelas.add(telaUsuarioCadastrar, TelaUsuarioCadastrar.NOME_TELA);
		add(gerarTelas, BorderLayout.CENTER);
	}

	public void construirTelaUsuarioConsulta() {
		telaUsuarioConsulta = new TelaUsuarioConsulta();
		gerarTelas.add(telaUsuarioConsulta, TelaUsuarioConsulta.NOME_TELA);
		add(gerarTelas, BorderLayout.CENTER);
	}

	public void construirTelaUsuarioPerfil() {
		telaUsuarioPerfil = new TelaUsuarioPerfil();
		gerarTelas.add(telaUsuarioPerfil, TelaUsuarioPerfil.NOME_TELA);
		add(gerarTelas, BorderLayout.CENTER);
	}

	public void construirTelaUsuarioPerfil(String var) {
		telaUsuarioPerfil = new TelaUsuarioPerfil(var);
		gerarTelas.add(telaUsuarioPerfil, TelaUsuarioPerfil.NOME_TELA);
		add(gerarTelas, BorderLayout.CENTER);
	}

	public static void main(String args[]) {
		TelaPrincipal frame = new TelaPrincipal();
		frame.setVisible(true);

	}

	public void exibirTelaArquivoEnviar() {
		exibirTela(TelaArquivoEnviar.NOME_TELA);
	}

	public void exibirTelaArquivoConsulta() {
		exibirTela(TelaArquivoConsulta.NOME_TELA);
	}

	public void exibirTelaAgenciaCadastrar() {
		exibirTela(TelaAgenciaCadastrar.NOME_TELA);
	}

	public void exibirTelaAgenciaConsulta() {
		exibirTela(TelaAgenciaConsulta.NOME_TELA);
	}

	public void exibirTelaUsuarioCadastrar() {
		exibirTela(TelaUsuarioCadastrar.NOME_TELA);
	}

	public void exibirTelaUsuarioConsulta() {
		exibirTela(TelaUsuarioConsulta.NOME_TELA);
	}

	public void exibirTelaUsuarioPerfil() {
		exibirTela(TelaUsuarioPerfil.NOME_TELA);
	}

	public void exibirTelaUsuarioPerfil(String var) {
		exibirTela(TelaUsuarioPerfil.NOME_TELA);
	}

	public TelaAgenciaConsulta getRefTelaAgenciaConsulta() {
		return telaAgenciaConsulta;
	}

	public void exibirTelaAlterarUsuario() {
		// TODO Auto-generated method stub
		
	}

}
