package visao.telas;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import modelo.Agencia;
import modelo.AgenciaDAO;
import modelo.RegionalDAO;

public class TelaAgenciaConsulta extends javax.swing.JPanel {

	private static final long serialVersionUID = -3397006666525790645L;
	public static final String NOME_TELA = "AGENCIA_CONSULTA";

	private javax.swing.JButton btApagarSelecionado;
	private javax.swing.JButton btEditarSelecionado;
	private javax.swing.JScrollPane jScrollPane1;
	private javax.swing.JTable tbExibirAgencias;

	public TelaAgenciaConsulta() {
		criarTelaAgenciaConsulta();
		exibirAgencias();
	}

	public void exibirAgencias() {

		tbExibirAgencias.setModel(new javax.swing.table.DefaultTableModel(new Object[][] {

		}, new String[] { "REGIONAL", "AGENCIA" }));

		DefaultTableModel tabelaAgencia = (DefaultTableModel) tbExibirAgencias.getModel();

		AgenciaDAO agencia = new AgenciaDAO();
		RegionalDAO regional = new RegionalDAO();

		for (Agencia ag : agencia.exibirAgencia()) {
			tabelaAgencia
					.addRow(new Object[] { regional.nomeRegional(ag.getRegional_regionalID()), ag.getAgenciaName(), });
		}

	}
	
	public void removerAgencia() {
		AgenciaDAO ageDao = new AgenciaDAO();

		int regional = ageDao
				.exibirRegionalID(tbExibirAgencias.getValueAt(tbExibirAgencias.getSelectedRow(), 0).toString());
		ageDao.removerAgencia(regional,
				tbExibirAgencias.getValueAt(tbExibirAgencias.getSelectedRow(), 1).toString());

		exibirAgencias();		
	}
	
	public void atualizarAgencia() {
		AgenciaDAO ageDao = new AgenciaDAO();

		int regional = ageDao
				.exibirRegionalID(tbExibirAgencias.getValueAt(tbExibirAgencias.getSelectedRow(), 0).toString());
		try {			
			String selectionValue = new String();
			selectionValue = JOptionPane.showInputDialog(null, "Adicione o nome da sua nova agencia");
			if(!selectionValue.equals("")) {
				String upperCase = selectionValue.trim().substring(0, 1).toUpperCase();
				String lowerCase = selectionValue.trim().substring(1, selectionValue.length()).toLowerCase();
				
				selectionValue = upperCase + lowerCase;
				
				ageDao.atualizarAgencia(selectionValue, regional,
						tbExibirAgencias.getValueAt(tbExibirAgencias.getSelectedRow(), 1).toString());
				
				exibirAgencias();
			} else {
				JOptionPane.showMessageDialog(null, "Adicione um nome para a sua Agencia");
				atualizarAgencia();
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Atualização cancelada.");
		}
	}

	private void criarTelaAgenciaConsulta() {
		jScrollPane1 = new javax.swing.JScrollPane();
		tbExibirAgencias = new javax.swing.JTable();
		btApagarSelecionado = new javax.swing.JButton();
		btEditarSelecionado = new javax.swing.JButton();

		jScrollPane1.setViewportView(tbExibirAgencias);

		btApagarSelecionado.setText("Apagar Selecionado");

		btEditarSelecionado.setText("Editar Selecionado");
		btEditarSelecionado.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				try {					
					atualizarAgencia();
				} catch (Exception e) {
					JOptionPane.showMessageDialog(null, "Selecione uma agencia para editar.");
				}
			}
		});

		btApagarSelecionado.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				try {					
					removerAgencia();
				} catch (Exception e) {
					JOptionPane.showMessageDialog(null, "Selecione a agencia que deseja remover.");
				}
			}
		});

		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
		this.setLayout(layout);
		layout.setHorizontalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(layout
				.createSequentialGroup()
				.addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 718, Short.MAX_VALUE)
				.addGap(18, 18, 18)
				.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
						.addComponent(btEditarSelecionado, javax.swing.GroupLayout.DEFAULT_SIZE,
								javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(btApagarSelecionado, javax.swing.GroupLayout.DEFAULT_SIZE, 154, Short.MAX_VALUE))
				.addContainerGap()));
		layout.setVerticalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup().addGroup(layout
						.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
						.addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING,
								javax.swing.GroupLayout.DEFAULT_SIZE, 600, Short.MAX_VALUE)
						.addGroup(javax.swing.GroupLayout.Alignment.LEADING,
								layout.createSequentialGroup().addGap(63, 63, 63)
										.addComponent(btApagarSelecionado, javax.swing.GroupLayout.PREFERRED_SIZE, 130,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addGap(43, 43, 43)
										.addComponent(btEditarSelecionado, javax.swing.GroupLayout.PREFERRED_SIZE, 121,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED,
												javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
						.addContainerGap()));
	}
}
