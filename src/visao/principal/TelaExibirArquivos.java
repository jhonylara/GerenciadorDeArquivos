package visao.principal;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Blob;
import java.sql.SQLException;
import java.text.ParseException;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.table.DefaultTableModel;

import modelo.AgenciaDAO;
import modelo.Arquivo;
import modelo.ArquivoDAO;
import modelo.UsuarioDAO;

public class TelaExibirArquivos extends JFrame {

	private static final long serialVersionUID = 22323;

	private int linha;
	private String id;

	ArquivoDAO arquivoDados = new ArquivoDAO();
	JButton botao = new JButton();

	private JFileChooser escolherArquivo = new JFileChooser();
	private JButton btBaixarArquivo = new JButton();
	private JScrollPane jScrollPane1 = new JScrollPane();
	private JTable tabelaArquivosExibir = new JTable();

	public TelaExibirArquivos() throws ParseException {
		initComponents();
		exibirArquivos();
	}

	public void exibirArquivos() throws ParseException {

		DefaultTableModel tabelaArquivo = (DefaultTableModel) tabelaArquivosExibir.getModel();

		ArquivoDAO criar = new ArquivoDAO();
		UsuarioDAO user = new UsuarioDAO();
		AgenciaDAO ag = new AgenciaDAO();

		for (Arquivo arq : criar.exibirArquivos()) {
			tabelaArquivo.addRow(new Object[] { arq.getArchiveName(), arq.getArchiveType(),
					ag.consultaAgenciaRegional(arq.getAgencia_agenciaID()),
					ag.exibirNomeAgencia(arq.getAgencia_agenciaID()), arq.getArchiveDescription(), arq.getArchiveDate(),
					user.nomeUsuario(arq.getUser_userID()), arq.getArchiveID() });
		}
	}

	private void btBaixarArquivoActionPerformed(java.awt.event.ActionEvent evt) {
		baixarArquivo(arquivoDados.arquivoBlob(Integer.valueOf(this.id)));
	}

	private void baixarArquivo(Blob arquivo) {
		int result = escolherArquivo.showSaveDialog(null);
		if (result == JFileChooser.APPROVE_OPTION) {
			String diretorio = escolherArquivo.getCurrentDirectory() + "/"
					+ escolherArquivo.getSelectedFile().getName();

			Blob conteudoArquivo = arquivo;
			FileOutputStream fluxoSaida = null;
			int tamanho = 0;

			try {
				fluxoSaida = new FileOutputStream(diretorio);
			} catch (FileNotFoundException e1) {
				e1.printStackTrace();
			}

			try {
				tamanho = (int) conteudoArquivo.length();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}

			try {
				fluxoSaida.write(conteudoArquivo.getBytes(1, tamanho));
			} catch (IOException | SQLException e1) {
				e1.printStackTrace();
			}

			try {
				fluxoSaida.close();
			} catch (IOException e1) {
				e1.printStackTrace();
			}

		}
		;
	}

	private void tabelaArquivosExibirMouseClicked(java.awt.event.MouseEvent evt) {
		this.linha = tabelaArquivosExibir.getSelectedRow();
		this.id = tabelaArquivosExibir.getValueAt(linha, 7).toString();
	}

	private void initComponents() {
		tabelaArquivosExibir.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				tabelaArquivosExibirMouseClicked(evt);
			}
		});

		btBaixarArquivo.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				btBaixarArquivoActionPerformed(evt);
			}
		});
		tabelaArquivosExibir.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tabelaArquivosExibir.setModel(new DefaultTableModel(new Object[][] {

		}, new String[] { "NOME ARQUIVO", "TIPO ARQUIVO", "REGIONAL", "AGENCIA", "DESCRICAO", "DATA", "USUARIO",
				"ID" }));
		jScrollPane1.setViewportView(tabelaArquivosExibir);

		btBaixarArquivo.setText("Baixar Arquivos Selecionados");

		GroupLayout layout = new GroupLayout(getContentPane());
		getContentPane().setLayout(layout);
		layout.setHorizontalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
				.addComponent(jScrollPane1, GroupLayout.DEFAULT_SIZE, 900, Short.MAX_VALUE)
				.addGroup(GroupLayout.Alignment.TRAILING,
						layout.createSequentialGroup().addContainerGap().addComponent(btBaixarArquivo,
								GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addContainerGap()));
		layout.setVerticalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
				.addGroup(layout.createSequentialGroup()
						.addComponent(jScrollPane1, GroupLayout.PREFERRED_SIZE, 525, GroupLayout.PREFERRED_SIZE)
						.addGap(18, 18, 18).addComponent(btBaixarArquivo, GroupLayout.DEFAULT_SIZE, 46, Short.MAX_VALUE)
						.addContainerGap()));

		pack();
		setLocationRelativeTo(null);
	}

	public static void main(String args[]) {
		try {
			for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
				if ("Nimbus".equals(info.getName())) {
					UIManager.setLookAndFeel(info.getClassName());
					break;
				}
			}
		} catch (ClassNotFoundException ex) {
			java.util.logging.Logger.getLogger(TelaExibirArquivos.class.getName()).log(java.util.logging.Level.SEVERE,
					null, ex);
		} catch (InstantiationException ex) {
			java.util.logging.Logger.getLogger(TelaExibirArquivos.class.getName()).log(java.util.logging.Level.SEVERE,
					null, ex);
		} catch (IllegalAccessException ex) {
			java.util.logging.Logger.getLogger(TelaExibirArquivos.class.getName()).log(java.util.logging.Level.SEVERE,
					null, ex);
		} catch (UnsupportedLookAndFeelException ex) {
			java.util.logging.Logger.getLogger(TelaExibirArquivos.class.getName()).log(java.util.logging.Level.SEVERE,
					null, ex);
		}

		java.awt.EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					new TelaExibirArquivos().setVisible(true);
				} catch (ParseException e) {
					e.printStackTrace();
				}
			}
		});
	}
}
