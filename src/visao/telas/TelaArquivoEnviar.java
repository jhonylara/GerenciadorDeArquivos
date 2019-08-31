package visao.telas;

import java.awt.datatransfer.DataFlavor;
import java.awt.dnd.DnDConstants;
import java.awt.dnd.DropTarget;
import java.awt.dnd.DropTargetDropEvent;
import java.io.*;
import java.util.List;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import controle.InserirArquivo;
import controle.PopularCombo;

public class TelaArquivoEnviar extends JPanel {
	private static final long serialVersionUID = 232323;

	public static final String NOME_TELA = "ARQUIVO_ENVIAR";

	private javax.swing.JLabel jLabel1;
	private javax.swing.JScrollPane jScrollPane1;
	private javax.swing.JButton btEnviarArquivo;
	private javax.swing.JButton btEscolherArquivo;
	private javax.swing.JComboBox<String> cbAgencia;
	private javax.swing.JComboBox<String> cbRegional;
	private javax.swing.JPanel divArquivo;
	private javax.swing.JTextArea txDescricao;
	private javax.swing.JPanel divDescricao;
	private javax.swing.JLabel lbAgencia;
	private javax.swing.JLabel lbPesquisa;
	private javax.swing.JLabel lbRegional;
	private javax.swing.JSeparator spSeparador;
	private javax.swing.JTextField txLocalArquivo;
	private javax.swing.JFileChooser escolherArquivo;
	PopularCombo popular = new PopularCombo();
	private InserirArquivo inserir = new InserirArquivo();

	public TelaArquivoEnviar() {
		criarTelaArquivoEnviar();
		iniciarComboRegional();
	}

	private void iniciarComboRegional() {
		cbRegional.setModel(new javax.swing.DefaultComboBoxModel<>(popular.gerarNomeRegional()));
	}

	private void cbRegionalActionPerformed(java.awt.event.ActionEvent evt) {
		iniciarComboAgencia(String.valueOf(cbRegional.getSelectedItem()));
	}

	private void iniciarComboAgencia(String regional) {
		cbAgencia.setModel(new javax.swing.DefaultComboBoxModel<>(popular.pegarNomeRegional(regional)));
	}

	private void cbAgenciaActionPerformed(java.awt.event.ActionEvent evt) {
	}

	private boolean sendValidator() {
		boolean validator;
		if ((txLocalArquivo.getText().equals("Nenhum arquivo selecionado.")
				|| txLocalArquivo.getText().trim().equals(""))
				&& (txDescricao.getText().trim().equals("Adicione uma descrição para o arquivo.")
						|| txDescricao.getText().trim().equals(""))) {
			validator = false;
		} else {
			validator = true;
		}
		return validator;
	}

	@SuppressWarnings("serial")
	private void actionMoveChooserFile() {
		divArquivo.setDropTarget(new DropTarget() {
			public synchronized void drop(DropTargetDropEvent evt) {
				try {
					evt.acceptDrop(DnDConstants.ACTION_COPY);
					@SuppressWarnings("unchecked")
					List<File> droppedFiles = (List<File>) evt.getTransferable()
							.getTransferData(DataFlavor.javaFileListFlavor);
					if (droppedFiles.size() > 1) {
						JOptionPane.showMessageDialog(null,
								"Varios arquivos escolhidos. \nSera adicionado apenas o primeiro");
					}
					escolherArquivo.setSelectedFile(droppedFiles.get(0));
					txLocalArquivo.setText(droppedFiles.get(0).toString());

				} catch (Exception ex) {
					ex.printStackTrace();
				}
			}
		});

		txLocalArquivo.setDropTarget(new DropTarget() {
			public synchronized void drop(DropTargetDropEvent ev) {
				try {
					ev.acceptDrop(DnDConstants.ACTION_COPY);
					@SuppressWarnings("unchecked")
					List<File> droppedFiles = (List<File>) ev.getTransferable()
							.getTransferData(DataFlavor.javaFileListFlavor);
					if (droppedFiles.size() > 1) {
						JOptionPane.showMessageDialog(null,
								"Varios arquivos escolhidos. \nSera adicionado apenas o primeiro");
					}
					escolherArquivo.setSelectedFile(droppedFiles.get(0));
					txLocalArquivo.setText(droppedFiles.get(0).toString());
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			}
		});
	}

	private void txDescricaoMouseClicked(java.awt.event.MouseEvent evt) {
		if (txDescricao.getText().equals("Adicione uma descrição para o arquivo.")) {
			txDescricao.setText(null);

		}

	}

	private void criarTelaArquivoEnviar() {
		jScrollPane1 = new javax.swing.JScrollPane();
		jLabel1 = new javax.swing.JLabel();
		divArquivo = new javax.swing.JPanel();
		divDescricao = new javax.swing.JPanel();
		txLocalArquivo = new javax.swing.JTextField();
		cbRegional = new javax.swing.JComboBox<>();
		cbAgencia = new javax.swing.JComboBox<>();
		lbPesquisa = new javax.swing.JLabel();
		txDescricao = new javax.swing.JTextArea();
		btEscolherArquivo = new javax.swing.JButton();
		btEnviarArquivo = new javax.swing.JButton();
		lbRegional = new javax.swing.JLabel();
		lbAgencia = new javax.swing.JLabel();
		spSeparador = new javax.swing.JSeparator();
		escolherArquivo = new javax.swing.JFileChooser();
		PopularCombo popular = new PopularCombo();

		actionMoveChooserFile();

		cbAgencia.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Escolha uma Regional acima!" }));

		divArquivo.setBorder(javax.swing.BorderFactory.createTitledBorder("Selecione o Arquivo:"));

		jLabel1.setText("Informe a descrição do arquivo:");

		javax.swing.GroupLayout divDescricaoLayout = new javax.swing.GroupLayout(divDescricao);
		divDescricao.setLayout(divDescricaoLayout);
		divDescricaoLayout.setHorizontalGroup(divDescricaoLayout
				.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(divDescricaoLayout.createSequentialGroup().addGap(19, 19, 19)
						.addGroup(divDescricaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
								.addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 820,
										javax.swing.GroupLayout.PREFERRED_SIZE)
								.addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 200,
										javax.swing.GroupLayout.PREFERRED_SIZE))
						.addContainerGap(29, Short.MAX_VALUE)));
		divDescricaoLayout.setVerticalGroup(divDescricaoLayout
				.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(javax.swing.GroupLayout.Alignment.TRAILING, divDescricaoLayout.createSequentialGroup()
						.addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE).addComponent(jLabel1)
						.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
						.addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 129,
								javax.swing.GroupLayout.PREFERRED_SIZE)
						.addContainerGap()));

		divDescricao.setBorder(javax.swing.BorderFactory.createTitledBorder("Descrição"));

		txDescricao.setColumns(20);
		txDescricao.setRows(5);
		txDescricao.setText("Adicione uma descrição para o arquivo.");
		txDescricao.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				txDescricaoMouseClicked(evt);
			}
		});

		jScrollPane1.setViewportView(txDescricao);

		btEscolherArquivo.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				int result = escolherArquivo.showOpenDialog(null);
				if (result == JFileChooser.APPROVE_OPTION) {
					txLocalArquivo.setText(escolherArquivo.getSelectedFile().getAbsolutePath());
				}

			}
		});

		btEnviarArquivo.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				try {
					if (sendValidator() == false) {
						JOptionPane.showMessageDialog(null,
								"Campos invalidos. \nPreencha todos os campos e tente novamente.", "Error",
								JOptionPane.WARNING_MESSAGE);
					} else {
						inserir.insertFile(escolherArquivo.getSelectedFile(), cbAgencia.getSelectedItem().toString(),
								txDescricao.getText());

						txLocalArquivo.setText("Nenhum arquivo selecionado.");
						txDescricao.setText("Adicione uma descrição para o arquivo.");
						cbAgencia.setSelectedIndex(0);
						cbRegional.setSelectedIndex(0);
					}
				} catch (Exception error) {
					JOptionPane.showMessageDialog(null, "Erro ao enviar arquivo.", "Error", JOptionPane.ERROR_MESSAGE);
				}
			}

		});

		cbRegional.setModel(new javax.swing.DefaultComboBoxModel<>(popular.gerarNomeRegional()));
		cbRegional.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				cbRegionalActionPerformed(evt);
			}
		});
		cbAgencia.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				cbAgenciaActionPerformed(evt);
			}
		});
		txLocalArquivo.setEnabled(false);
		txLocalArquivo.setText("Nenhum arquivo selecionado.");

		lbPesquisa.setText("Arraste o arquivo ou pesquise");

		btEscolherArquivo.setText("Escolher arquivo");

		btEnviarArquivo.setText("Enviar Arquivo");

		lbRegional.setText("Escolha o Escritorio Regional:");

		lbAgencia.setText("Escolha a Agencia:");

		javax.swing.GroupLayout divArquivoLayout = new javax.swing.GroupLayout(divArquivo);
		divArquivo.setLayout(divArquivoLayout);
		divArquivoLayout.setHorizontalGroup(divArquivoLayout
				.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(divArquivoLayout.createSequentialGroup().addGap(20, 20, 20)
						.addGroup(divArquivoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
								.addGroup(divArquivoLayout
										.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
										.addComponent(cbAgencia, 0, 502, Short.MAX_VALUE)
										.addComponent(cbRegional, javax.swing.GroupLayout.Alignment.LEADING, 0,
												javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
										.addComponent(txLocalArquivo, javax.swing.GroupLayout.Alignment.LEADING))
								.addComponent(lbRegional).addComponent(lbAgencia).addComponent(lbPesquisa))
						.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED,
								javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addGroup(divArquivoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
								.addComponent(btEnviarArquivo, javax.swing.GroupLayout.Alignment.TRAILING,
										javax.swing.GroupLayout.PREFERRED_SIZE, 254,
										javax.swing.GroupLayout.PREFERRED_SIZE)
								.addComponent(btEscolherArquivo, javax.swing.GroupLayout.Alignment.TRAILING,
										javax.swing.GroupLayout.PREFERRED_SIZE, 254,
										javax.swing.GroupLayout.PREFERRED_SIZE))
						.addGap(26, 26, 26))
				.addGroup(javax.swing.GroupLayout.Alignment.TRAILING,
						divArquivoLayout.createSequentialGroup()
								.addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(spSeparador, javax.swing.GroupLayout.PREFERRED_SIZE, 722,
										javax.swing.GroupLayout.PREFERRED_SIZE)
								.addGap(60, 60, 60)));
		divArquivoLayout.setVerticalGroup(divArquivoLayout
				.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(divArquivoLayout.createSequentialGroup().addGap(30, 30, 30)
						.addGroup(divArquivoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
								.addGroup(divArquivoLayout.createSequentialGroup().addComponent(lbPesquisa)
										.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
										.addComponent(txLocalArquivo, javax.swing.GroupLayout.PREFERRED_SIZE, 31,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addGap(0, 12, Short.MAX_VALUE))
								.addComponent(btEscolherArquivo, javax.swing.GroupLayout.DEFAULT_SIZE,
										javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
						.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
						.addComponent(spSeparador, javax.swing.GroupLayout.PREFERRED_SIZE, 10,
								javax.swing.GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED).addComponent(lbRegional)
						.addGroup(divArquivoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
								.addGroup(divArquivoLayout.createSequentialGroup()
										.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
										.addComponent(cbRegional, javax.swing.GroupLayout.PREFERRED_SIZE, 29,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addGap(18, 18, 18).addComponent(lbAgencia)
										.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
										.addComponent(cbAgencia, javax.swing.GroupLayout.PREFERRED_SIZE, 33,
												javax.swing.GroupLayout.PREFERRED_SIZE))
								.addGroup(divArquivoLayout.createSequentialGroup().addGap(19, 19, 19).addComponent(
										btEnviarArquivo, javax.swing.GroupLayout.PREFERRED_SIZE, 68,
										javax.swing.GroupLayout.PREFERRED_SIZE)))
						.addGap(21, 21, 21)));

		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
		this.setLayout(layout);
		layout.setHorizontalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(layout
				.createSequentialGroup().addContainerGap()
				.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
						.addComponent(divArquivo, javax.swing.GroupLayout.Alignment.TRAILING,
								javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE,
								Short.MAX_VALUE)
						.addComponent(divDescricao, javax.swing.GroupLayout.DEFAULT_SIZE,
								javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
				.addContainerGap()));
		layout.setVerticalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(
				javax.swing.GroupLayout.Alignment.TRAILING,
				layout.createSequentialGroup().addContainerGap(27, Short.MAX_VALUE)
						.addComponent(divArquivo, javax.swing.GroupLayout.PREFERRED_SIZE,
								javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
						.addComponent(divDescricao, javax.swing.GroupLayout.PREFERRED_SIZE,
								javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
						.addGap(88, 88, 88)));

		divDescricao.getAccessibleContext().setAccessibleName("");
	}

}
