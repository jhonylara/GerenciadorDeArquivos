package visao.telas;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.math.BigDecimal;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.ButtonGroup;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import modelo.Usuario;
import modelo.UsuarioDAO;

public class TelaUsuarioConsulta extends javax.swing.JPanel {
	private static final long serialVersionUID = -649739957905865945L;
	public static final String NOME_TELA = "USUARIO_CONSULTA";

	private javax.swing.JTable tbExibirUsuario;
	private javax.swing.JButton btAlterarUsuario;
    private javax.swing.JButton btApagarUsuario;
    private javax.swing.JButton btSalvarAlteracoes;
    private javax.swing.JTextField inputEmail;
    private javax.swing.JTextField inputLogin;
    private javax.swing.JTextField inputNomeUsuario;
    private javax.swing.JPasswordField inputNovaSenha;
    private javax.swing.JTextField inputTelefone;
    private javax.swing.JScrollPane scrollPanel;
    private javax.swing.JLabel lbAdministrador;
    private javax.swing.JLabel lbEmail;
    private javax.swing.JLabel lbLogin;
    private javax.swing.JLabel lbNomeUsuario;
    private javax.swing.JLabel lbNovaSenha;
    private javax.swing.JLabel lbTelefone;
    private javax.swing.JPanel panelAlterarUsuario;
    private javax.swing.JRadioButton rbAdminNao;
    private javax.swing.JRadioButton rbAdminSim;
	private boolean selectRoot;

	public TelaUsuarioConsulta() {
		initComponents();
		exibirUsuarios();
	}

	public void exibirUsuarios() {
		bloquearInputs(false);
		DefaultTableModel tabelaUsuario = (DefaultTableModel) tbExibirUsuario.getModel();
		UsuarioDAO criar = new UsuarioDAO();
		String root;

		for (Usuario user : criar.exibirUsuarios()) {
			if (user.isUserRoot() == true) {
				root = "SIM";
			} else {
				root = "NÃO";
			}
			tabelaUsuario.addRow(new Object[] {
					user.getUserID(), user.getUserName(), user.getUserLogin(), root, user.getUserPhone(),
					user.getUserEmail(),
			});
		}
	}
	
	public void preencherCampos(String usuarioSelecionado) {
		panelAlterarUsuario.setVisible(true);
		int idSelecionado = Integer.valueOf(usuarioSelecionado);
		UsuarioDAO usuarioDAO = new UsuarioDAO();

		for(Usuario usuario: usuarioDAO.pegarUsuario(idSelecionado)) {
			inputLogin.setText(usuario.getUserLogin());
			inputNomeUsuario.setText(usuario.getUserName());
			inputEmail.setText(usuario.getUserEmail());
			inputTelefone.setText(regExTelefoneString(usuario.getUserPhone()));
			inputNovaSenha.setText(usuario.getUserPassword());
			
			rbAdminSim.setSelected(false);
			rbAdminSim.setSelected(false);
			
			if(usuario.isUserRoot() == true) {
				rbAdminSim.setSelected(true);
			} else {
				rbAdminNao.setSelected(true);
			}
		}
	}
	
	private void limparTabela() {
		DefaultTableModel tabelaUsuario = (DefaultTableModel) tbExibirUsuario.getModel();
		
		tabelaUsuario.setNumRows(0);
	}
	
	private void bloquearInputs(boolean bloquear) {
		panelAlterarUsuario.setVisible(bloquear);
	}

	private void enviarUsuario() {
		if (validarCampos() == true) {			
			int idUsuario = (int) tbExibirUsuario.getValueAt(tbExibirUsuario.getSelectedRow(), 0);
			UsuarioDAO user = new UsuarioDAO();
			BigDecimal telefone = regExTelefoneBigDecimal();
			char[] novaSenha = inputNovaSenha.getPassword();

			user.alterarUsuario(idUsuario, inputNomeUsuario.getText(), inputEmail.getText(), telefone, inputLogin.getText(), novaSenha.toString(), selectRoot);
			
			limparTabela();
			exibirUsuarios();
		}
	}

	private void excluirUsuario() {
		int returnRemove = JOptionPane.showConfirmDialog(null, "Deseja excluir o usuario '" + tbExibirUsuario.getValueAt(tbExibirUsuario.getSelectedRow(), 2) + "'", "Excluir usuario", JOptionPane.WARNING_MESSAGE);
		if(returnRemove == 0) {
			UsuarioDAO usuarioDAO = new UsuarioDAO();
			usuarioDAO.excluirUsuario((int) tbExibirUsuario.getValueAt(tbExibirUsuario.getSelectedRow(), 0));
			
			limparTabela();
			exibirUsuarios();
		}
	}

	@SuppressWarnings("deprecation")
	private boolean validarCampos() {
		boolean valido = true;
		if (inputNovaSenha.getText().equals("") && inputEmail.getText().equals("")
				&& inputLogin.getText().equals("") && inputNomeUsuario.getText().equals("")
				&& inputTelefone.getText().equals("")) {
			valido = false;
			JOptionPane.showMessageDialog(null, "Todos os campos em branco", "Campo vazio",
					JOptionPane.WARNING_MESSAGE);
			return false;
		}

		String erro = ":";

		if (inputNomeUsuario.getText().equals("")) {
			valido = false;
			erro = erro + " Usuario,";
		}
		if (inputEmail.getText().equals("")) {
			valido = false;
			erro = erro + " Email,";
		}
		if (inputTelefone.getText().equals("")) {
			valido = false;
			erro = erro + " Telefone,";
		}
		if (valido == false) {
			JOptionPane.showMessageDialog(null, "Os campos estão em branco" + erro);
		}

		if (regExEmail() == false) {
			valido = false;
			JOptionPane.showMessageDialog(null, "Digite o email neste formato: ' email@exemplo.com '");
			return false;
		}
		
		if(inputTelefone.getText().substring(5, 6).equals("9")) {			
			if (inputTelefone.getText().length() < 17) {
				valido = false;
				JOptionPane.showMessageDialog(null, "O telefone esta incompleto");
				return false;
			}
		}
		
		if(!inputTelefone.getText().substring(5, 6).equals("9")) {			
			if (inputTelefone.getText().length() < 16) {
				valido = false;
				JOptionPane.showMessageDialog(null, "O telefone esta incompleto");
				return false;
			}
		}

		if(!rbAdminSim.isSelected() && !rbAdminNao.isSelected()) {
			valido = false;
			JOptionPane.showMessageDialog(null, "Selecione um tipo de usuario");
			return false;
		}
		
		
		if(!inputLogin.getText().equals(tbExibirUsuario.getValueAt(tbExibirUsuario.getSelectedRow(), 2).toString())) {			
			UsuarioDAO usuarioDB = new UsuarioDAO();
			for(Usuario user :  usuarioDB.exibirUsuarios() ) {
				if(inputLogin.getText().equals(user.getUserLogin())) {
					valido = false;
					JOptionPane.showMessageDialog(null, "Este usuario já existe");
					return false;
				}
			}
		}

		return valido;
	}
	
	private String regExTelefoneString(BigDecimal telefone) {
		String numeroString = null;
		if(telefone.toString().substring(3, 4).equals("9")) {
			BigDecimal numeroDecimal = telefone;
			String numeroDDD = numeroDecimal.toString().substring(0, 2);
			String numeroFirst = numeroDecimal.toString().substring(2, 7);
			String numeroLast = numeroDecimal.toString().substring(7, 11);
	
			numeroString = "(" + numeroDDD + ") " + numeroFirst + " - " + numeroLast;
		} else {
			BigDecimal numeroDecimal = telefone;
			String numeroDDD = numeroDecimal.toString().substring(0, 2);
			String numeroFirst = numeroDecimal.toString().substring(2, 6);
			String numeroLast = numeroDecimal.toString().substring(6, 10);
	
			numeroString = "(" + numeroDDD + ") " + numeroFirst + " - " + numeroLast;
		}
		return numeroString;
	}
	
	public BigDecimal regExTelefoneBigDecimal() {
		BigDecimal numeroReturn = null;
		if(inputTelefone.getText().substring(5, 6).equals("9")) {			
			String inputText = inputTelefone.getText();
			String numeroString = inputText.substring(1, 3) + inputText.substring(5, 10) + inputText.substring(13, 17);
			numeroReturn = new BigDecimal(numeroString);
		} else {
			String inputText = inputTelefone.getText();
			String numeroString = inputText.substring(1, 3) + inputText.substring(5, 9) + inputText.substring(12, 16);
			numeroReturn = new BigDecimal(numeroString);
		}
		return numeroReturn;
	}

	public boolean regExEmail() {
		Pattern validEmail = Pattern.compile("^[A-Z0-9._-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);

		Matcher matcher = validEmail.matcher(inputEmail.getText());
		return matcher.find();
	}

	@SuppressWarnings("serial")
	private void initComponents() {
	    scrollPanel = new javax.swing.JScrollPane();
	    btApagarUsuario = new javax.swing.JButton();
	    btAlterarUsuario = new javax.swing.JButton();
	    panelAlterarUsuario = new javax.swing.JPanel();
	    lbNomeUsuario = new javax.swing.JLabel();
	    lbEmail = new javax.swing.JLabel();
	    lbTelefone = new javax.swing.JLabel();
	    lbNovaSenha = new javax.swing.JLabel();
	    lbAdministrador = new javax.swing.JLabel();
	    lbLogin = new javax.swing.JLabel();
	    inputLogin = new javax.swing.JTextField();
	    inputNomeUsuario = new javax.swing.JTextField();
	    inputEmail = new javax.swing.JTextField();
	    inputTelefone = new javax.swing.JTextField();
	    inputNovaSenha = new javax.swing.JPasswordField();
	    btSalvarAlteracoes = new javax.swing.JButton();
	    rbAdminSim = new javax.swing.JRadioButton();
	    rbAdminNao = new javax.swing.JRadioButton();
	    tbExibirUsuario = new javax.swing.JTable();

		tbExibirUsuario.setModel(new javax.swing.table.DefaultTableModel(new Object[][] {},
				new String[] { "ID", "NOME", "LOGIN", "ADMIN", "TELEFONE", "EMAIL" }) {
			boolean[] canEdit = new boolean[] { false, false, false, false, false, false };

			public boolean isCellEditable(int rowIndex, int columnIndex) {
				return canEdit[columnIndex];
			}
		});

		scrollPanel.setViewportView(tbExibirUsuario);

	    btApagarUsuario.setText("Apagar usuario");
	    btApagarUsuario.addActionListener(new java.awt.event.ActionListener() {
	        public void actionPerformed(java.awt.event.ActionEvent evt) {
	        	excluirUsuario();
	        }
	    });

	    btAlterarUsuario.setText("Alterar usuario");
	    btAlterarUsuario.addActionListener(new java.awt.event.ActionListener() {
	        public void actionPerformed(java.awt.event.ActionEvent evt) {
	        	try {
	        		if(panelAlterarUsuario.isVisible()) {
	        			panelAlterarUsuario.setVisible(false);
	        		} else {
	        			panelAlterarUsuario.setVisible(true);
	        			preencherCampos(tbExibirUsuario.getValueAt(tbExibirUsuario.getSelectedRow(), 0).toString());	        			
	        		}
				} catch (Exception e) {
					JOptionPane.showMessageDialog(null, "Selecione um usuario para editar.");
				}
	        }
	    });

	    panelAlterarUsuario.setBorder(javax.swing.BorderFactory.createTitledBorder("Alterar usuario"));

	    lbNomeUsuario.setFont(new java.awt.Font("Cantarell", 0, 18)); // NOI18N
	    lbNomeUsuario.setText("Nome Usuario: ");

	    lbEmail.setFont(new java.awt.Font("Cantarell", 0, 18)); // NOI18N
	    lbEmail.setText("Email");

	    lbTelefone.setFont(new java.awt.Font("Cantarell", 0, 18)); // NOI18N
	    lbTelefone.setText("Telefone:");

	    lbNovaSenha.setFont(new java.awt.Font("Cantarell", 0, 18)); // NOI18N
	    lbNovaSenha.setText("Nova senha:");

	    lbAdministrador.setFont(new java.awt.Font("Cantarell", 0, 18)); // NOI18N
	    lbAdministrador.setText("Administrador:");

	    lbLogin.setFont(new java.awt.Font("Cantarell", 0, 18)); // NOI18N
	    lbLogin.setText("Login:");

	    inputLogin.setText("");

	    inputNomeUsuario.setText("");

	    inputEmail.setText("");

	    inputTelefone.setText("");
	    inputTelefone.addKeyListener(new KeyListener() {
			@Override
			public void keyTyped(KeyEvent e) {}
			@Override
			public void keyReleased(KeyEvent e) {
				try {
					int numTamanho = inputTelefone.getText().length();
					if((e.getKeyCode() >= 20 && e.getKeyCode() <= 47) || (e.getKeyCode() >= 58 && e.getKeyCode() <= 126)) {
						String numCom = inputTelefone.getText().substring(0, numTamanho - 1);
						inputTelefone.setText(numCom);
						
						JOptionPane.showMessageDialog(null, "Digite apenas números.", "Digito diferente.", JOptionPane.WARNING_MESSAGE);
					}
					
					String numTel = inputTelefone.getText().substring(5, 6);
					if(!numTel.equals("9")) {						
						if(numTamanho > 16) {
							String numCom = inputTelefone.getText().substring(0, numTamanho - 1);
							inputTelefone.setText(numCom);
							
							JOptionPane.showMessageDialog(null, "Telefone não pode conter mais que 8 números digitos.", "Tamanho maximo de telefone", JOptionPane.WARNING_MESSAGE);
						}
					} else {
						if(numTamanho > 17) {
							String numCom = inputTelefone.getText().substring(0, numTamanho - 1);
							inputTelefone.setText(numCom);
							
							JOptionPane.showMessageDialog(null, "Celular não pode conter mais que 9 números digitos.", "Tamanho maximo de telefone", JOptionPane.WARNING_MESSAGE);
						}
					}
				} catch (Exception exp) {
				}
			}
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode() >= 48 && e.getKeyCode() <= 57) {
					int numTamanho = inputTelefone.getText().length();
					if(numTamanho == 0) {
						String numCom = inputTelefone.getText().substring(0, numTamanho);
						inputTelefone.setText("(" + numCom);
					} else if (numTamanho == 3) {
						String numCom = inputTelefone.getText().substring(1, numTamanho);
						inputTelefone.setText("(" + numCom + ") ");
					} else if (numTamanho > 8) {
						String numCom = inputTelefone.getText().substring(5, 6);
						if(!numCom.equals("9")) {
							if(numTamanho == 9) {
								String numDDD = inputTelefone.getText().substring(1, 3);
								String numTel = inputTelefone.getText().substring(5, numTamanho);
								inputTelefone.setText("(" + numDDD + ") " + numTel + " - ");
							} else if (numTamanho == 12) {
								String numDDD = inputTelefone.getText().substring(1, 3);
								String numFirst = inputTelefone.getText().substring(5, 10);
								String numLast = inputTelefone.getText().substring(12, numTamanho);
								inputTelefone.setText("(" + numDDD + ") " + numFirst + " - " + numLast);
							}
						} else {
							if(numTamanho == 10) {
								String numDDD = inputTelefone.getText().substring(1, 3);
								String numTel = inputTelefone.getText().substring(5, numTamanho);
								inputTelefone.setText("(" + numDDD + ") " + numTel + " - ");
							} else if (numTamanho == 12) {
								String numDDD = inputTelefone.getText().substring(1, 3);
								String numFirst = inputTelefone.getText().substring(5, 10);
								String numLast = inputTelefone.getText().substring(13, numTamanho);
								inputTelefone.setText("(" + numDDD + ") " + numFirst + " - " + numLast);
							}
						}
					}
				}
			}
		});

	    inputNovaSenha.setText("");
	    btSalvarAlteracoes.setText("Salvar alterações");
	    btSalvarAlteracoes.addActionListener(new java.awt.event.ActionListener() {
	        public void actionPerformed(java.awt.event.ActionEvent evt) {
	            enviarUsuario();
	        }
	    });

	    rbAdminSim.setText("SIM");
		rbAdminNao.setText("NÃO");
		
		ButtonGroup editableGroup = new ButtonGroup();
		editableGroup.add(rbAdminSim);
		editableGroup.add(rbAdminNao);

		rbAdminSim.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(rbAdminSim.isSelected()) {
					selectRoot = true;
				} else {
					selectRoot = false;
				}
			}
		});
		
		rbAdminNao.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(rbAdminNao.isSelected()) {
					selectRoot = true;
				} else {
					selectRoot = false;
				}
			}
		});


	    javax.swing.GroupLayout panelAlterarUsuarioLayout = new javax.swing.GroupLayout(panelAlterarUsuario);
	    panelAlterarUsuario.setLayout(panelAlterarUsuarioLayout);
	    panelAlterarUsuarioLayout.setHorizontalGroup(
	        panelAlterarUsuarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
	        .addGroup(panelAlterarUsuarioLayout.createSequentialGroup()
	            .addContainerGap()
	            .addGroup(panelAlterarUsuarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
	                .addComponent(lbLogin)
	                .addComponent(lbNomeUsuario)
	                .addComponent(lbEmail)
	                .addComponent(lbTelefone)
	                .addComponent(lbNovaSenha)
	                .addComponent(lbAdministrador))
	            .addGap(29, 29, 29)
	            .addGroup(panelAlterarUsuarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
	                .addGroup(panelAlterarUsuarioLayout.createSequentialGroup()
	                    .addGroup(panelAlterarUsuarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
	                        .addComponent(inputLogin)
	                        .addComponent(inputNomeUsuario)
	                        .addComponent(inputEmail)
	                        .addComponent(inputTelefone)
	                        .addComponent(inputNovaSenha, javax.swing.GroupLayout.DEFAULT_SIZE, 470, Short.MAX_VALUE))
	                    .addGap(18, 18, 18)
	                    .addComponent(btSalvarAlteracoes, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
	                .addGroup(panelAlterarUsuarioLayout.createSequentialGroup()
	                    .addGap(88, 88, 88)
	                    .addComponent(rbAdminSim)
	                    .addGap(62, 62, 62)
	                    .addComponent(rbAdminNao)
	                    .addGap(0, 0, Short.MAX_VALUE)))
	            .addContainerGap())
	    );
	    panelAlterarUsuarioLayout.setVerticalGroup(
	        panelAlterarUsuarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
	        .addGroup(panelAlterarUsuarioLayout.createSequentialGroup()
	            .addContainerGap()
	            .addGroup(panelAlterarUsuarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
	                .addComponent(lbLogin)
	                .addComponent(inputLogin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
	            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
	            .addGroup(panelAlterarUsuarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
	                .addComponent(lbNomeUsuario)
	                .addComponent(inputNomeUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
	            .addGroup(panelAlterarUsuarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
	                .addGroup(panelAlterarUsuarioLayout.createSequentialGroup()
	                    .addGap(8, 8, 8)
	                    .addGroup(panelAlterarUsuarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
	                        .addComponent(lbEmail)
	                        .addComponent(inputEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
	                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
	                    .addGroup(panelAlterarUsuarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
	                        .addComponent(lbTelefone)
	                        .addComponent(inputTelefone, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
	                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
	                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelAlterarUsuarioLayout.createSequentialGroup()
	                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
	                    .addComponent(btSalvarAlteracoes, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
	                    .addGap(5, 5, 5)))
	            .addGroup(panelAlterarUsuarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
	                .addComponent(lbNovaSenha)
	                .addComponent(inputNovaSenha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
	            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
	            .addGroup(panelAlterarUsuarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
	                .addComponent(lbAdministrador)
	                .addComponent(rbAdminSim)
	                .addComponent(rbAdminNao))
	            .addContainerGap(17, Short.MAX_VALUE))
	    );

	    javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
	    this.setLayout(layout);
	    layout.setHorizontalGroup(
	        layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
	        .addGroup(layout.createSequentialGroup()
	            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
	                .addGroup(layout.createSequentialGroup()
	                    .addComponent(scrollPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 707, javax.swing.GroupLayout.PREFERRED_SIZE)
	                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
	                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
	                        .addComponent(btAlterarUsuario, javax.swing.GroupLayout.DEFAULT_SIZE, 169, Short.MAX_VALUE)
	                        .addComponent(btApagarUsuario, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
	                .addComponent(panelAlterarUsuario, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
	            .addContainerGap())
	    );
	    layout.setVerticalGroup(
	        layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
	        .addGroup(layout.createSequentialGroup()
	            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
	                .addComponent(scrollPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 306, javax.swing.GroupLayout.PREFERRED_SIZE)
	                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
	                    .addGap(26, 26, 26)
	                    .addComponent(btApagarUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
	                    .addGap(87, 87, 87)
	                    .addComponent(btAlterarUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)))
	            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
								.addComponent(panelAlterarUsuario, javax.swing.GroupLayout.PREFERRED_SIZE,
										javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)));
	}
}
