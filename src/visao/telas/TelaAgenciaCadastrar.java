package visao.telas;

import javax.swing.JOptionPane;

import controle.PopularCombo;
import modelo.Agencia;
import modelo.AgenciaDAO;
import modelo.Regional;
import modelo.RegionalDAO;

public class TelaAgenciaCadastrar extends javax.swing.JPanel {

	private static final long serialVersionUID = 833959719499655324L;
	public static final String NOME_TELA = "AGENCIA_CADASTRO";

	private javax.swing.JComboBox<String> cbEscolhaRegional;
	private javax.swing.JButton btRegistrarRegional;
	private javax.swing.JButton btRegistrarAgencia;
	private javax.swing.JPanel jpAdicionarAgencia;
	private javax.swing.JPanel jpAdicionarRegional;
	private javax.swing.JLabel lbEscolhaRegional;
	private javax.swing.JLabel lbNomeAgencia;
	private javax.swing.JLabel lbNomeRegional;
	private javax.swing.JTextField txNomeAgencia;
	private javax.swing.JTextField txNomeRegional;

	public TelaAgenciaCadastrar() {
		criarTelaAgenciaCadastrar();
	}

	public void adicionarAgencia() {

		Agencia agencia = new Agencia();
		AgenciaDAO dao = new AgenciaDAO();

		String upperCase = txNomeAgencia.getText().trim().substring(0, 1).toUpperCase();
		String lowerCase = txNomeAgencia.getText().trim().substring(1, txNomeAgencia.getText().length()).toLowerCase();
		agencia.setAgenciaName(upperCase + lowerCase);

		if (validarNome(dao, agencia) == false) {
			agencia.setRegional_regionalID(dao.exibirRegionalID((cbEscolhaRegional.getSelectedItem().toString())));
			dao.adicionarAgencia(agencia);
		}
	}

	public void adicionarRegional() {

		Regional regional = new Regional();
		RegionalDAO dao = new RegionalDAO();

		String upperCase = txNomeRegional.getText().trim().substring(0, 1).toUpperCase();
		String lowerCase = txNomeRegional.getText().trim().substring(1, txNomeRegional.getText().length())
				.toLowerCase();
		regional.setRegionalName(upperCase + lowerCase);

		if (validarNome(dao, regional) == false) {
			dao.adicionarRegional(regional);
		}
	}

	public boolean validarNome(RegionalDAO regDao, Regional regNome) {
		boolean validar = false;

		for (int i = 0; i < regDao.comboRegional().size(); i++) {
			if (regDao.comboRegional().get(i).toUpperCase().equals(regNome.getRegionalName().toUpperCase()) == true) {
				JOptionPane.showMessageDialog(null, "Ja existe uma regional com este nome.", "Error",
						JOptionPane.WARNING_MESSAGE);
				validar = true;
			}
		}

		return validar;
	}

	public boolean validarNome(AgenciaDAO ageDao, Agencia ageNome) {
		boolean validar = false;

		for (int i = 0; i < ageDao
				.exibirNomeAgenciaRegional(ageDao.exibirRegionalID(cbEscolhaRegional.getSelectedItem().toString()))
				.size(); i++) {
			if (ageDao
					.exibirNomeAgenciaRegional(ageDao.exibirRegionalID(cbEscolhaRegional.getSelectedItem().toString()))
					.get(i).toUpperCase().equals(ageNome.getAgenciaName().toUpperCase()) == true) {
				JOptionPane.showMessageDialog(null, "Ja existe uma regional vinculada com o este nome de agencia.", "Error",
						JOptionPane.WARNING_MESSAGE);
				validar = true;
			}
		}

		return validar;
	}

	private void criarTelaAgenciaCadastrar() {
		jpAdicionarRegional = new javax.swing.JPanel();
		txNomeRegional = new javax.swing.JTextField();
		btRegistrarRegional = new javax.swing.JButton();
		lbNomeRegional = new javax.swing.JLabel();
		jpAdicionarAgencia = new javax.swing.JPanel();
		lbNomeAgencia = new javax.swing.JLabel();
		txNomeAgencia = new javax.swing.JTextField();
		lbEscolhaRegional = new javax.swing.JLabel();
		cbEscolhaRegional = new javax.swing.JComboBox<>();
		btRegistrarAgencia = new javax.swing.JButton();
		PopularCombo popular = new PopularCombo();

		jpAdicionarRegional.setBorder(javax.swing.BorderFactory.createTitledBorder("ADICIONAR REGINAL"));

		btRegistrarRegional.setText("Registrar Regional");
		btRegistrarRegional.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {

				adicionarRegional();
				cbEscolhaRegional.setModel(new javax.swing.DefaultComboBoxModel<>(popular.gerarNomeRegional()));
			}

		});

		lbNomeRegional.setText("Digite o nome da Regional:");

		javax.swing.GroupLayout jpAdicionarRegionalLayout = new javax.swing.GroupLayout(jpAdicionarRegional);
		jpAdicionarRegional.setLayout(jpAdicionarRegionalLayout);
		jpAdicionarRegionalLayout.setHorizontalGroup(
				jpAdicionarRegionalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(jpAdicionarRegionalLayout.createSequentialGroup().addGap(21, 21, 21)
								.addGroup(jpAdicionarRegionalLayout
										.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
										.addComponent(txNomeRegional, javax.swing.GroupLayout.PREFERRED_SIZE, 445,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addComponent(lbNomeRegional))
								.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 108,
										Short.MAX_VALUE)
								.addComponent(btRegistrarRegional, javax.swing.GroupLayout.PREFERRED_SIZE, 243,
										javax.swing.GroupLayout.PREFERRED_SIZE)
								.addGap(51, 51, 51)));
		jpAdicionarRegionalLayout.setVerticalGroup(jpAdicionarRegionalLayout
				.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(jpAdicionarRegionalLayout.createSequentialGroup().addGap(38, 38, 38)
						.addGroup(jpAdicionarRegionalLayout
								.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
								.addComponent(btRegistrarRegional, javax.swing.GroupLayout.PREFERRED_SIZE, 55,
										javax.swing.GroupLayout.PREFERRED_SIZE)
								.addGroup(jpAdicionarRegionalLayout.createSequentialGroup().addComponent(lbNomeRegional)
										.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
										.addComponent(txNomeRegional, javax.swing.GroupLayout.PREFERRED_SIZE, 34,
												javax.swing.GroupLayout.PREFERRED_SIZE)))
						.addContainerGap(40, Short.MAX_VALUE)));

		jpAdicionarAgencia.setBorder(javax.swing.BorderFactory.createTitledBorder("ADICIONAR AGENCIA"));

		lbNomeAgencia.setText("Digite o nome da Agencia:");

		lbEscolhaRegional.setText("Escolha a regional da Agencia");

		cbEscolhaRegional.setModel(new javax.swing.DefaultComboBoxModel<>(popular.gerarNomeRegional()));

		btRegistrarAgencia.setText("Registrar Agencia");
		btRegistrarAgencia.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				adicionarAgencia();
			}
		});
		javax.swing.GroupLayout jpAdicionarAgenciaLayout = new javax.swing.GroupLayout(jpAdicionarAgencia);
		jpAdicionarAgencia.setLayout(jpAdicionarAgenciaLayout);
		jpAdicionarAgenciaLayout.setHorizontalGroup(
				jpAdicionarAgenciaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(jpAdicionarAgenciaLayout.createSequentialGroup().addGap(24, 24, 24)
								.addGroup(jpAdicionarAgenciaLayout
										.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
										.addComponent(btRegistrarAgencia, javax.swing.GroupLayout.PREFERRED_SIZE, 248,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addGroup(jpAdicionarAgenciaLayout
												.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
												.addComponent(cbEscolhaRegional, 0, 447, Short.MAX_VALUE)
												.addComponent(lbEscolhaRegional).addComponent(txNomeAgencia)
												.addComponent(lbNomeAgencia)))
								.addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));
		jpAdicionarAgenciaLayout.setVerticalGroup(
				jpAdicionarAgenciaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(jpAdicionarAgenciaLayout.createSequentialGroup().addGap(24, 24, 24)
								.addComponent(lbNomeAgencia)
								.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
								.addComponent(txNomeAgencia, javax.swing.GroupLayout.PREFERRED_SIZE, 33,
										javax.swing.GroupLayout.PREFERRED_SIZE)
								.addGap(43, 43, 43).addComponent(lbEscolhaRegional)
								.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
								.addComponent(cbEscolhaRegional, javax.swing.GroupLayout.PREFERRED_SIZE, 35,
										javax.swing.GroupLayout.PREFERRED_SIZE)
								.addGap(40, 40, 40)
								.addComponent(btRegistrarAgencia, javax.swing.GroupLayout.PREFERRED_SIZE, 62,
										javax.swing.GroupLayout.PREFERRED_SIZE)
								.addContainerGap(24, Short.MAX_VALUE)));

		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
		this.setLayout(layout);
		layout.setHorizontalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(layout
				.createSequentialGroup().addContainerGap()
				.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
						.addComponent(jpAdicionarRegional, javax.swing.GroupLayout.Alignment.TRAILING,
								javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE,
								Short.MAX_VALUE)
						.addComponent(jpAdicionarAgencia, javax.swing.GroupLayout.DEFAULT_SIZE,
								javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
				.addContainerGap()));
		layout.setVerticalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(layout.createSequentialGroup().addContainerGap()
						.addComponent(jpAdicionarRegional, javax.swing.GroupLayout.PREFERRED_SIZE,
								javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
						.addComponent(jpAdicionarAgencia, javax.swing.GroupLayout.PREFERRED_SIZE,
								javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
						.addContainerGap(89, Short.MAX_VALUE)));
	}

}
