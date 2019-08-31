package visao.telas;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.math.BigDecimal;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.BorderFactory;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.LayoutStyle;

import controle.UserLog;
import modelo.UsuarioDAO;

public class TelaUsuarioPerfil extends JPanel {
	private static final long serialVersionUID = -5278172520373438884L;
	public static final String NOME_TELA = "USUARIO_PERFIL";

	private JPanel alterarUsuario = new JPanel();
	private JPanel dadosUsuario = new JPanel();
	private JButton btAlterarUsuario = new JButton();
	private JButton btSalvarDados = new JButton();
	private JButton btAlterarSenha = new JButton();
	private JPasswordField inputConfNovaSenha = new JPasswordField();
	private JTextField inputEmail = new JTextField();
	private JTextField inputNomeUsuario = new JTextField();
	private JPasswordField inputNovaSenha = new JPasswordField();
	private JTextField inputTelefone = new JTextField();
	private JPasswordField inputSenhaAtual = new JPasswordField();
	private JLabel lbAdminUsuario = new JLabel();
	private JLabel lbAlterarSenha = new JLabel();
	private JLabel lbEdiConfNovaSenha = new JLabel();
	private JLabel lbEdiEmailUsuario = new JLabel();
	private JLabel lbEdiNomeUsuario = new JLabel();
	private JLabel lbEdiNovaSenha = new JLabel();
	private JLabel lbEdiSenhaAtual = new JLabel();
	private JLabel lbEdiTelefoneUsuario = new JLabel();
	private JLabel lbEmailUsuario = new JLabel();
	private JLabel lbLoginUsuario = new JLabel();
	private JLabel lbPreAdminUsuario = new JLabel();
	private JLabel lbPreEmailUsuario = new JLabel();
	private JLabel lbPreLoginUsuario = new JLabel();
	private JLabel lbPreTelefoneUsuario = new JLabel();
	private JLabel lbTelefoneUsuario = new JLabel();
	private JLabel nomeUsuario = new JLabel();

	public TelaUsuarioPerfil() {
		initComponents();
		perfilAtual();
		bloquearTodos(false);
	}

	public TelaUsuarioPerfil(String var) {
		initComponents();
		perfilAtual();
		bloquearTodos(false);
	}
	
	public void perfilAtual() {
		UserLog usuario = new UserLog();
		usuario.usuarioAtual(UserLog.userLogin);

		nomeUsuario.setText(UserLog.userName);
		lbPreLoginUsuario.setText(UserLog.userLogin);
		lbPreTelefoneUsuario.setText(regExTelefoneString(UserLog.userPhone));
		lbPreEmailUsuario.setText("" + UserLog.userEmail);

		if (UserLog.userRoot == true) {
			lbPreAdminUsuario.setText("Sim");
		} else {
			lbPreAdminUsuario.setText("Não");
		}
	}

	private void bloquearTodos(boolean var) {
		alterarUsuario.setEnabled(var);

		inputNomeUsuario.setEnabled(var);
		inputEmail.setEnabled(var);
		inputTelefone.setEnabled(var);
		inputSenhaAtual.setEnabled(var);
		inputNovaSenha.setEnabled(var);
		inputConfNovaSenha.setEnabled(var);

		lbEdiNomeUsuario.setEnabled(var);
		lbEdiEmailUsuario.setEnabled(var);
		lbEdiTelefoneUsuario.setEnabled(var);
		lbEdiSenhaAtual.setEnabled(var);
		lbEdiNovaSenha.setEnabled(var);
		lbEdiConfNovaSenha.setEnabled(var);

		btSalvarDados.setEnabled(var);
		btAlterarSenha.setEnabled(var);

		if (var == true) {
			completarCampos(var);
		} else {
			completarCampos(var);
		}
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

	private boolean validarDados() {
		boolean valido = true;
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
		
		if(regExEmail() == false) {
			valido = false;
			JOptionPane.showMessageDialog(null, "Digite o email neste formato: ' email@exemplo.com '");
		}
		if(inputTelefone.getText().length() < 17) {
			valido = false;
			JOptionPane.showMessageDialog(null, "O telefone esta incompleto");
		}
		return valido;
	}
	
	@SuppressWarnings("deprecation")
	private boolean validarSenha() {
		boolean valido = true;
		if (inputNovaSenha.getText().equals("") && inputConfNovaSenha.getText().equals("") && inputSenhaAtual.getText().equals("")) {
			valido = false;
			JOptionPane.showMessageDialog(null, "Todos os campos em branco", "Campo vazio", JOptionPane.WARNING_MESSAGE);
		}
		
		char[] senhaAtual = inputSenhaAtual.getPassword();
		if(senhaAtual.length != UserLog.userPassword.length()) {
			JOptionPane.showMessageDialog(null, "Senha atual não correspode a senha atual", "Campo incorreto", JOptionPane.WARNING_MESSAGE);
			valido = false;
			return false;
		} else {
			for(int i = 0; i < senhaAtual.length; i++) {
				if (senhaAtual[i] != UserLog.userPassword.charAt(i)) {
					JOptionPane.showMessageDialog(null, "Senha atual não correspode a senha atual", "Campo incorreto", JOptionPane.WARNING_MESSAGE);
					valido = false;
					return false;
				}
			}
		}
		
		char[] novaSenha = inputNovaSenha.getPassword();
		char[] confNovaSenha = inputConfNovaSenha.getPassword();
		if(novaSenha.length != confNovaSenha.length) {
			JOptionPane.showMessageDialog(null, "Confirmação de senha não corresponde a nova senha", "Campo incorreto", JOptionPane.WARNING_MESSAGE);
			valido = false;
			return false;
		} else {
			for(int i = 0; i < novaSenha.length; i++) {
				if (novaSenha[i] != confNovaSenha[i]) {
					JOptionPane.showMessageDialog(null, "Confirmação de senha não corresponde a nova senha", "Campo incorreto", JOptionPane.WARNING_MESSAGE);
					valido = false;
					return false;
				}
			}
		}
		
		return valido;
	}

	private void editarUsuario() {
		if(validarDados() == true) {			
			UsuarioDAO user = new UsuarioDAO();
			BigDecimal telefone = regExTelefoneBigDecimal();
			
			user.alterarUsuario(inputNomeUsuario.getText(), inputEmail.getText(), telefone, UserLog.userID);
			
			perfilAtual();
		}
	}

	@SuppressWarnings("deprecation")
	private void editarSenha() {
		if(validarSenha() == true) {
			UsuarioDAO user = new UsuarioDAO();
			String novaSenha = inputConfNovaSenha.getText();
			user.alterarSenha(novaSenha, UserLog.userID);
			
			perfilAtual();
		}
	}

	private void completarCampos(boolean var) {
		if (var == true) {
			inputNomeUsuario.setText(nomeUsuario.getText());
			inputEmail.setText(lbPreEmailUsuario.getText());
			inputTelefone.setText(lbPreTelefoneUsuario.getText());
		} else {
			inputNomeUsuario.setText("");
			inputEmail.setText("");
			inputTelefone.setText("");
		}
	}

	private void initComponents() {

		dadosUsuario.setBorder(BorderFactory.createTitledBorder("Dados do usuario"));

		nomeUsuario.setFont(new java.awt.Font("Cantarell", 0, 48)); // NOI18N

		lbEmailUsuario.setFont(new java.awt.Font("Cantarell", 1, 18)); // NOI18N
		lbEmailUsuario.setText("Email: ");

		lbTelefoneUsuario.setFont(new java.awt.Font("Cantarell", 1, 18)); // NOI18N
		lbTelefoneUsuario.setText("Telefone: ");

		lbLoginUsuario.setFont(new java.awt.Font("Cantarell", 1, 18)); // NOI18N
		lbLoginUsuario.setText("Login: ");

		lbAdminUsuario.setFont(new java.awt.Font("Cantarell", 1, 18)); // NOI18N
		lbAdminUsuario.setText("Admininistrador: ");

		lbPreEmailUsuario.setFont(new java.awt.Font("Cantarell", 0, 18)); // NOI18N

		lbPreTelefoneUsuario.setFont(new java.awt.Font("Cantarell", 0, 18)); // NOI18N

		lbPreLoginUsuario.setFont(new java.awt.Font("Cantarell", 0, 18)); // NOI18N

		lbPreAdminUsuario.setFont(new java.awt.Font("Cantarell", 0, 18)); // NOI18N

		btAlterarUsuario.setText("Alterar dados");
		btAlterarUsuario.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				if (alterarUsuario.isEnabled()) {
					bloquearTodos(false);
				} else {
					bloquearTodos(true);
				}
			}
		});

		GroupLayout dadosUsuarioLayout = new GroupLayout(dadosUsuario);
		dadosUsuario.setLayout(dadosUsuarioLayout);
		dadosUsuarioLayout.setHorizontalGroup(dadosUsuarioLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
				.addGroup(dadosUsuarioLayout.createSequentialGroup().addContainerGap()
						.addGroup(dadosUsuarioLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
								.addComponent(nomeUsuario)
								.addGroup(dadosUsuarioLayout.createSequentialGroup()
										.addGroup(dadosUsuarioLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
												.addComponent(lbEmailUsuario).addComponent(lbTelefoneUsuario)
												.addComponent(lbLoginUsuario).addComponent(lbAdminUsuario))
										.addGap(29, 29, 29)
										.addGroup(dadosUsuarioLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
												.addComponent(lbPreEmailUsuario).addComponent(lbPreTelefoneUsuario)
												.addComponent(lbPreAdminUsuario).addComponent(lbPreLoginUsuario))))
						.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE,
								Short.MAX_VALUE)
						.addComponent(btAlterarUsuario, GroupLayout.PREFERRED_SIZE, 236, GroupLayout.PREFERRED_SIZE)
						.addContainerGap()));
		dadosUsuarioLayout.setVerticalGroup(dadosUsuarioLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
				.addGroup(dadosUsuarioLayout.createSequentialGroup().addGap(6, 6, 6).addComponent(nomeUsuario)
						.addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
						.addGroup(dadosUsuarioLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
								.addComponent(lbEmailUsuario).addComponent(lbPreEmailUsuario))
						.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
						.addGroup(dadosUsuarioLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
								.addGroup(dadosUsuarioLayout.createSequentialGroup()
										.addGroup(dadosUsuarioLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
												.addComponent(lbTelefoneUsuario).addComponent(lbPreTelefoneUsuario))
										.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
										.addGroup(dadosUsuarioLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
												.addComponent(lbLoginUsuario).addComponent(lbPreLoginUsuario))
										.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
										.addGroup(dadosUsuarioLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
												.addComponent(lbAdminUsuario).addComponent(lbPreAdminUsuario))
										.addGap(0, 0, Short.MAX_VALUE))
								.addComponent(btAlterarUsuario, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE,
										Short.MAX_VALUE))
						.addContainerGap()));

		alterarUsuario.setBorder(BorderFactory.createTitledBorder("Editar dados do usuario"));
		alterarUsuario.setPreferredSize(new java.awt.Dimension(890, 342));

		lbEdiNomeUsuario.setFont(new java.awt.Font("Cantarell", 1, 18)); // NOI18N
		lbEdiNomeUsuario.setText("Nome do usuario:");

		lbEdiEmailUsuario.setFont(new java.awt.Font("Cantarell", 1, 18)); // NOI18N
		lbEdiEmailUsuario.setText("Email:");

		lbEdiTelefoneUsuario.setFont(new java.awt.Font("Cantarell", 1, 18)); // NOI18N
		lbEdiTelefoneUsuario.setText("Telefone:");
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

		btSalvarDados.setText("Salvar alterações");
		btSalvarDados.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				editarUsuario();
			}
		});

		lbEdiSenhaAtual.setFont(new java.awt.Font("Cantarell", 1, 18)); // NOI18N
		lbEdiSenhaAtual.setText("Senha atual:");

		lbEdiNovaSenha.setFont(new java.awt.Font("Cantarell", 1, 18)); // NOI18N
		lbEdiNovaSenha.setText("Nova senha:");

		lbEdiConfNovaSenha.setFont(new java.awt.Font("Cantarell", 1, 18)); // NOI18N
		lbEdiConfNovaSenha.setText("Confirmar nova senha:");

		btAlterarSenha.setText("Alterar senha");
		btAlterarSenha.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				editarSenha();
			}
		});

		lbAlterarSenha.setFont(new java.awt.Font("Cantarell", 1, 18)); // NOI18N
		lbAlterarSenha.setText("Alterar senha");

		GroupLayout alterarUsuarioLayout = new GroupLayout(alterarUsuario);
		alterarUsuario.setLayout(alterarUsuarioLayout);
		alterarUsuarioLayout.setHorizontalGroup(alterarUsuarioLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
				.addGroup(alterarUsuarioLayout.createSequentialGroup().addContainerGap().addGroup(alterarUsuarioLayout
						.createParallelGroup(GroupLayout.Alignment.LEADING)
						.addGroup(alterarUsuarioLayout.createSequentialGroup()
								.addGroup(alterarUsuarioLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
										.addComponent(lbEdiConfNovaSenha, GroupLayout.Alignment.TRAILING)
										.addGroup(alterarUsuarioLayout.createSequentialGroup()
												.addGroup(alterarUsuarioLayout
														.createParallelGroup(GroupLayout.Alignment.TRAILING)
														.addComponent(lbEdiSenhaAtual).addComponent(lbEdiNovaSenha))
												.addGap(0, 0, Short.MAX_VALUE)))
								.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
								.addGroup(alterarUsuarioLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
										.addComponent(inputConfNovaSenha, GroupLayout.PREFERRED_SIZE, 391,
												GroupLayout.PREFERRED_SIZE)
										.addComponent(inputSenhaAtual, GroupLayout.PREFERRED_SIZE, 391,
												GroupLayout.PREFERRED_SIZE)
										.addComponent(inputNovaSenha, GroupLayout.PREFERRED_SIZE, 391,
												GroupLayout.PREFERRED_SIZE)
										.addGroup(alterarUsuarioLayout
												.createParallelGroup(GroupLayout.Alignment.LEADING)
												.addComponent(inputNomeUsuario, GroupLayout.Alignment.TRAILING,
														GroupLayout.PREFERRED_SIZE, 391, GroupLayout.PREFERRED_SIZE)
												.addComponent(inputTelefone, GroupLayout.Alignment.TRAILING,
														GroupLayout.PREFERRED_SIZE, 391, GroupLayout.PREFERRED_SIZE)
												.addComponent(inputEmail, GroupLayout.Alignment.TRAILING,
														GroupLayout.PREFERRED_SIZE, 391, GroupLayout.PREFERRED_SIZE)))
								.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 59, Short.MAX_VALUE)
								.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(btAlterarSenha,
										GroupLayout.PREFERRED_SIZE, 158, GroupLayout.PREFERRED_SIZE))
						.addGroup(alterarUsuarioLayout.createSequentialGroup()
								.addGroup(alterarUsuarioLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
										.addComponent(lbEdiNomeUsuario).addComponent(lbEdiTelefoneUsuario)
										.addComponent(lbEdiEmailUsuario))
								.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE,
										Short.MAX_VALUE)
								.addComponent(btSalvarDados, GroupLayout.PREFERRED_SIZE, 158,
										GroupLayout.PREFERRED_SIZE)))
						.addGap(45, 45, 45))
				.addGroup(alterarUsuarioLayout.createSequentialGroup().addGap(350, 350, 350)
						.addComponent(lbAlterarSenha).addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));
		alterarUsuarioLayout.setVerticalGroup(alterarUsuarioLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
				.addGroup(alterarUsuarioLayout.createSequentialGroup().addContainerGap().addGroup(alterarUsuarioLayout
						.createParallelGroup(GroupLayout.Alignment.LEADING)
						.addGroup(alterarUsuarioLayout.createSequentialGroup().addGroup(alterarUsuarioLayout
								.createParallelGroup(GroupLayout.Alignment.LEADING)
								.addGroup(alterarUsuarioLayout.createSequentialGroup().addComponent(lbEdiNomeUsuario)
										.addGap(10, 10, 10).addComponent(lbEdiEmailUsuario))
								.addComponent(btSalvarDados, GroupLayout.PREFERRED_SIZE, 50,
										GroupLayout.PREFERRED_SIZE))
								.addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
								.addComponent(lbEdiTelefoneUsuario))
						.addGroup(alterarUsuarioLayout.createSequentialGroup()
								.addComponent(inputNomeUsuario, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE)
								.addGap(13, 13, 13)
								.addComponent(inputEmail, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED).addComponent(inputTelefone,
										GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE)))
						.addGroup(
								alterarUsuarioLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
										.addGroup(alterarUsuarioLayout.createSequentialGroup().addGap(12, 12, 12)
												.addComponent(lbAlterarSenha)
												.addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
												.addGroup(alterarUsuarioLayout
														.createParallelGroup(GroupLayout.Alignment.TRAILING)
														.addComponent(lbEdiSenhaAtual)
														.addComponent(inputSenhaAtual, GroupLayout.PREFERRED_SIZE,
																GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
												.addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
												.addGroup(alterarUsuarioLayout
														.createParallelGroup(GroupLayout.Alignment.BASELINE)
														.addComponent(lbEdiNovaSenha)
														.addComponent(inputNovaSenha, GroupLayout.PREFERRED_SIZE,
																GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
												.addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
												.addGroup(alterarUsuarioLayout
														.createParallelGroup(GroupLayout.Alignment.BASELINE)
														.addComponent(inputConfNovaSenha, GroupLayout.PREFERRED_SIZE,
																GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
														.addComponent(lbEdiConfNovaSenha)))
										.addGroup(alterarUsuarioLayout.createSequentialGroup().addGap(46, 46, 46)
												.addGroup(alterarUsuarioLayout
														.createParallelGroup(GroupLayout.Alignment.LEADING)
														.addComponent(btAlterarSenha, GroupLayout.PREFERRED_SIZE, 50,
																GroupLayout.PREFERRED_SIZE))))
						.addContainerGap(19, Short.MAX_VALUE)));

		GroupLayout layout = new GroupLayout(this);
		this.setLayout(layout);
		layout.setHorizontalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(layout
				.createSequentialGroup().addContainerGap()
				.addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
						.addComponent(alterarUsuario, GroupLayout.DEFAULT_SIZE, 882, Short.MAX_VALUE).addComponent(
								dadosUsuario, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
				.addContainerGap()));
		layout.setVerticalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(layout
				.createSequentialGroup().addContainerGap()
				.addComponent(dadosUsuario, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
						GroupLayout.PREFERRED_SIZE)
				.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
				.addComponent(alterarUsuario, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
				.addContainerGap()));
	}
}
