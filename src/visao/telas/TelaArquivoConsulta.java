package visao.telas;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.LayoutStyle;

import com.toedter.calendar.JDateChooser;
import com.toedter.calendar.JTextFieldDateEditor;

import controle.ConsultaArquivo;
import controle.PopularCombo;
import visao.menu.MenuPrincipal;
import visao.principal.TelaExibirArquivos;

public class TelaArquivoConsulta extends JPanel {
	private static final long serialVersionUID = 1L;
	public static final String NOME_TELA = "ARQUIVO_CONSULTA";

	private JButton btExibirArquivos = new JButton();
	private JComboBox<String> cbAgencia = new JComboBox<>();
	private JComboBox<String> cbRegional = new JComboBox<>();
	private JCheckBox chkPesquisaNome = new JCheckBox();
	private JCheckBox chkPesquisaUsuario = new JCheckBox();
	private JCheckBox chkPesquisarData = new JCheckBox();
	private JSeparator jSeparator3 = new JSeparator();
	private JSeparator jSeparator4 = new JSeparator();
	private JSeparator jSeparator5 = new JSeparator();
	private JLabel lbAgencia = new JLabel();
	private JLabel lbDigiteData = new JLabel();
	private JLabel lbDigiteNome = new JLabel();
	private JLabel lbDigiteUsuario = new JLabel();
	private JLabel lbRegional = new JLabel();
	private JTextField texPesquisaUsuario = new JTextField();
	private JTextField txNomeArquivo = new JTextField();
	private JPanel divConsultaAdicional = new JPanel();
	private JPanel divEscolhaAgencia = new JPanel();
	private JDateChooser dtCampoData = new JDateChooser();

	PopularCombo popular = new PopularCombo();

	public TelaArquivoConsulta() {
		criarTelaArquivoConsulta();
		iniciarComboRegional();
		bloquearCampos(false);

		dtCampoData.setEnabled(false);
		txNomeArquivo.setEnabled(false);
		texPesquisaUsuario.setEnabled(false);
		lbDigiteData.setEnabled(false);
		lbDigiteNome.setEnabled(false);
		lbDigiteUsuario.setEnabled(false);
		divConsultaAdicional.setEnabled(false);
	}

	private void limparCampos() {
		chkPesquisaNome.setSelected(false);
		chkPesquisaUsuario.setSelected(false);
		chkPesquisarData.setSelected(false);
		dtCampoData.setDate(null);
		txNomeArquivo.setText(null);
		texPesquisaUsuario.setText(null);
	}

	private void validarCombo() {
		if (cbAgencia.getSelectedItem().equals("Selecione...")) {
			bloquearCampos(false);
			limparCampos();
		} else {
			bloquearCampos(true);
			limparCampos();
		}
	}

	private void bloquearCampos(boolean abrir) {
		divConsultaAdicional.setEnabled(abrir);
		chkPesquisaNome.setEnabled(abrir);
		chkPesquisaUsuario.setEnabled(abrir);
		chkPesquisarData.setEnabled(abrir);

		jSeparator3.setEnabled(abrir);
		jSeparator4.setEnabled(abrir);
		jSeparator5.setEnabled(abrir);

		caixaPesquisaUsuario();
		caixaPesquisaNome();
		caixaPesquisarData();

		lbDigiteUsuario.setEnabled(false);
		lbDigiteNome.setEnabled(false);
		lbDigiteData.setEnabled(false);
		dtCampoData.setEnabled(false);
		txNomeArquivo.setEnabled(false);
	}

	private void caixaPesquisaUsuario() {
		if (chkPesquisaUsuario.isSelected()) {
			texPesquisaUsuario.setEnabled(true);
			lbDigiteUsuario.setEnabled(true);
			texPesquisaUsuario.setText(null);
		} else {
			texPesquisaUsuario.setEnabled(false);
			lbDigiteUsuario.setEnabled(false);
			texPesquisaUsuario.setText(null);
		}
		;
	}

	private void caixaPesquisaNome() {
		if (chkPesquisaNome.isSelected()) {
			txNomeArquivo.setEnabled(true);
			lbDigiteNome.setEnabled(true);
			txNomeArquivo.setText(null);

		} else {
			txNomeArquivo.setEnabled(false);
			lbDigiteNome.setEnabled(false);
			txNomeArquivo.setText(null);
		}
		;
	}

	private void caixaPesquisarData() {
		if (chkPesquisarData.isSelected()) {
			dtCampoData.setEnabled(true);
			lbDigiteData.setEnabled(true);
			dtCampoData.setDate(null);
		} else {
			dtCampoData.setEnabled(false);
			lbDigiteData.setEnabled(false);
			dtCampoData.setDate(null);
		}
		;
	}

	private void cbRegionalActionPerformed(java.awt.event.ActionEvent evt) {
		bloquearCampos(false);
		limparCampos();
		iniciarComboAgencia(String.valueOf(cbRegional.getSelectedItem()));
	}

	private void iniciarComboRegional() {
		cbRegional.setModel(new DefaultComboBoxModel<>(popular.gerarNomeRegional()));
	}

	private void cbAgenciaActionPerformed(java.awt.event.ActionEvent evt) {
		validarCombo();
	}

	private void iniciarComboAgencia(String regional) {
		cbAgencia.setModel(new DefaultComboBoxModel<>(popular.pegarNomeRegional(regional)));
	}

	private void criarTelaArquivoConsulta() {
		((JTextFieldDateEditor) dtCampoData.getDateEditor()).setEditable(false);

		btExibirArquivos.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				TelaExibirArquivos exibir;
				try {
					ConsultaArquivo consultaArquivo = new ConsultaArquivo();
					
					consultaArquivo.setAgencia(cbAgencia.getSelectedItem().toString());
					consultaArquivo.setNome(txNomeArquivo.getText().trim().toLowerCase());
					consultaArquivo.setUsuario(texPesquisaUsuario.getText().trim().toLowerCase());
					if(dtCampoData.getDate() != null) {
						DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
						Date formatDate = dtCampoData.getDate();

						consultaArquivo.setData(dateFormat.format(formatDate));
					} else {
						consultaArquivo.setData("");
					}

					exibir = new TelaExibirArquivos();

					exibir.setVisible(true);
					MenuPrincipal.arquivoConsulta();
				} catch (ParseException e) {
					e.printStackTrace();
				}
			}
		});

		chkPesquisaUsuario.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				caixaPesquisaUsuario();
			}
		});

		chkPesquisarData.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				caixaPesquisarData();
			}
		});

		chkPesquisaNome.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				caixaPesquisaNome();
			}
		});

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

		divEscolhaAgencia.setBorder(BorderFactory.createTitledBorder("Escolha a Agencia e regional"));
		divConsultaAdicional.setBorder(BorderFactory.createTitledBorder("Consultar Adicionais"));

		cbAgencia.setModel(new DefaultComboBoxModel<>(new String[] { "Escolha uma Regional acima!" }));

		chkPesquisarData.setText("Pesquisar por Data");
		chkPesquisaNome.setText("Pesquisar por nome");
		chkPesquisaUsuario.setText("Pesquisar por Usuario");

		lbRegional.setText("Escolha o Escritorio Regional:");
		lbAgencia.setText("Escolha a Agencia:");
		lbDigiteData.setText("Digite a data:");
		lbDigiteNome.setText("Digite o nome para consulta");
		lbDigiteUsuario.setText("Digite o nome do usuario");

		btExibirArquivos.setText("Exibir Arquivos");
		setFramePage();
	}

	private void setFramePage() {
		GroupLayout divEscolhaAgenciaLayout = new GroupLayout(divEscolhaAgencia);
		divEscolhaAgencia.setLayout(divEscolhaAgenciaLayout);
		divEscolhaAgenciaLayout.setHorizontalGroup(divEscolhaAgenciaLayout
				.createParallelGroup(GroupLayout.Alignment.LEADING)
				.addGroup(divEscolhaAgenciaLayout.createSequentialGroup().addGap(58, 58, 58)
						.addGroup(divEscolhaAgenciaLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
								.addComponent(lbRegional).addComponent(lbAgencia)
								.addComponent(cbAgencia, GroupLayout.PREFERRED_SIZE, 604, GroupLayout.PREFERRED_SIZE)
								.addComponent(cbRegional, GroupLayout.PREFERRED_SIZE, 604, GroupLayout.PREFERRED_SIZE))
						.addGap(0, 0, Short.MAX_VALUE)));
		divEscolhaAgenciaLayout
				.setVerticalGroup(divEscolhaAgenciaLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
						.addGroup(divEscolhaAgenciaLayout.createSequentialGroup().addGap(21, 21, 21)
								.addComponent(lbRegional).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
								.addComponent(cbRegional, GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE)
								.addGap(18, 18, 18).addComponent(lbAgencia)
								.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
								.addComponent(cbAgencia, GroupLayout.PREFERRED_SIZE, 33, GroupLayout.PREFERRED_SIZE)
								.addContainerGap(13, Short.MAX_VALUE)));

		GroupLayout divConsultaAdicionalLayout = new GroupLayout(divConsultaAdicional);
		divConsultaAdicional.setLayout(divConsultaAdicionalLayout);
		divConsultaAdicionalLayout.setHorizontalGroup(divConsultaAdicionalLayout
				.createParallelGroup(GroupLayout.Alignment.LEADING)
				.addGroup(GroupLayout.Alignment.TRAILING, divConsultaAdicionalLayout.createSequentialGroup()
						.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addGroup(divConsultaAdicionalLayout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
								.addComponent(jSeparator3, GroupLayout.DEFAULT_SIZE, 830, Short.MAX_VALUE)
								.addComponent(jSeparator4)
								.addGroup(divConsultaAdicionalLayout.createSequentialGroup().addGap(44, 44, 44)
										.addGroup(divConsultaAdicionalLayout
												.createParallelGroup(GroupLayout.Alignment.LEADING)
												.addGroup(divConsultaAdicionalLayout.createSequentialGroup()
														.addComponent(chkPesquisaNome).addGap(65, 65, 65)
														.addGroup(divConsultaAdicionalLayout
																.createParallelGroup(GroupLayout.Alignment.LEADING)
																.addComponent(lbDigiteNome)
																.addComponent(txNomeArquivo, GroupLayout.PREFERRED_SIZE,
																		550, GroupLayout.PREFERRED_SIZE)))
												.addGroup(divConsultaAdicionalLayout.createSequentialGroup()
														.addGap(1, 1, 1).addComponent(chkPesquisarData)
														.addGap(71, 71, 71)
														.addGroup(divConsultaAdicionalLayout
																.createParallelGroup(GroupLayout.Alignment.LEADING)
																.addComponent(lbDigiteData).addComponent(dtCampoData,
																		GroupLayout.PREFERRED_SIZE, 165,
																		GroupLayout.PREFERRED_SIZE)))))
								.addGroup(divConsultaAdicionalLayout.createSequentialGroup().addGap(47, 47, 47)
										.addComponent(chkPesquisaUsuario).addGap(53, 53, 53)
										.addGroup(divConsultaAdicionalLayout
												.createParallelGroup(GroupLayout.Alignment.LEADING)
												.addComponent(lbDigiteUsuario).addComponent(texPesquisaUsuario,
														GroupLayout.PREFERRED_SIZE, 550, GroupLayout.PREFERRED_SIZE))))
						.addGap(28, 28, 28))
				.addGroup(divConsultaAdicionalLayout.createSequentialGroup().addGroup(divConsultaAdicionalLayout
						.createParallelGroup(GroupLayout.Alignment.LEADING)
						.addGroup(divConsultaAdicionalLayout.createSequentialGroup().addGap(266, 266, 266).addComponent(
								btExibirArquivos, GroupLayout.PREFERRED_SIZE, 298, GroupLayout.PREFERRED_SIZE))
						.addGroup(divConsultaAdicionalLayout.createSequentialGroup().addContainerGap().addComponent(
								jSeparator5, GroupLayout.PREFERRED_SIZE, 830, GroupLayout.PREFERRED_SIZE)))
						.addContainerGap(28, Short.MAX_VALUE)));
		divConsultaAdicionalLayout.setVerticalGroup(divConsultaAdicionalLayout
				.createParallelGroup(GroupLayout.Alignment.LEADING)
				.addGroup(divConsultaAdicionalLayout.createSequentialGroup().addGap(6, 6, 6).addComponent(lbDigiteData)
						.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
						.addGroup(divConsultaAdicionalLayout.createParallelGroup(GroupLayout.Alignment.TRAILING)
								.addComponent(chkPesquisarData)
								.addComponent(dtCampoData, GroupLayout.PREFERRED_SIZE, 31, GroupLayout.PREFERRED_SIZE))
						.addGap(18, 18, 18)
						.addComponent(jSeparator3, GroupLayout.PREFERRED_SIZE, 10, GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(lbDigiteNome)
						.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
						.addGroup(
								divConsultaAdicionalLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
										.addComponent(txNomeArquivo, GroupLayout.PREFERRED_SIZE, 28,
												GroupLayout.PREFERRED_SIZE)
										.addComponent(chkPesquisaNome))
						.addGap(18, 18, 18)
						.addComponent(jSeparator4, GroupLayout.PREFERRED_SIZE, 10, GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(lbDigiteUsuario)
						.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
						.addGroup(divConsultaAdicionalLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
								.addComponent(texPesquisaUsuario, GroupLayout.PREFERRED_SIZE, 28,
										GroupLayout.PREFERRED_SIZE)
								.addComponent(chkPesquisaUsuario))
						.addGap(18, 18, 18)
						.addComponent(jSeparator5, GroupLayout.PREFERRED_SIZE, 10, GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE,
								Short.MAX_VALUE)
						.addComponent(btExibirArquivos, GroupLayout.PREFERRED_SIZE, 45, GroupLayout.PREFERRED_SIZE)
						.addGap(4, 4, 4)));

		GroupLayout layout = new GroupLayout(this);
		this.setLayout(layout);
		layout.setHorizontalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
				.addGroup(layout.createSequentialGroup().addContainerGap()
						.addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
								.addComponent(divConsultaAdicional, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE,
										Short.MAX_VALUE)
								.addComponent(divEscolhaAgencia, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE,
										Short.MAX_VALUE))
						.addContainerGap()));
		layout.setVerticalGroup(
				layout.createParallelGroup(GroupLayout.Alignment.LEADING)
						.addGroup(layout.createSequentialGroup().addContainerGap()
								.addComponent(divEscolhaAgencia, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
								.addComponent(divConsultaAdicional, GroupLayout.PREFERRED_SIZE,
										GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addContainerGap(79, Short.MAX_VALUE)));

	}

}
