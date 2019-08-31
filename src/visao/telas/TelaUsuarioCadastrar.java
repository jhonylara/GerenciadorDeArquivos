package visao.telas;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.math.BigDecimal;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.LayoutStyle;

import modelo.Usuario;
import modelo.UsuarioDAO;

public class TelaUsuarioCadastrar extends JPanel {
	private static final long serialVersionUID = 2073965227608087530L;
	public static final String NOME_TELA = "USUARIO_CADASTRO";

	private JButton btConfirmaAlteracao = new JButton();
	private JPanel divCadastrarUsuario = new JPanel();
	private JLabel lbUsuarioAdmin = new JLabel();
	private JLabel lbNomeUsuario = new JLabel();
	private JLabel lbConfSenha = new JLabel();
	private JLabel lbEmail = new JLabel();
	private JLabel lbTelefone = new JLabel();
	private JLabel lbLogin = new JLabel();
	private JLabel lbSenha = new JLabel();
	private JRadioButton rbAdmNao = new JRadioButton();
	private JRadioButton rbAdmSim = new JRadioButton();
	private JTextField inputEmail = new JTextField();
	private JTextField inputLogin = new JTextField();
	private JTextField inputNomeUsuario = new JTextField();
	private JPasswordField inputConfSenha = new JPasswordField();
	private JPasswordField inputSenha = new JPasswordField();
	private JTextField inputTelefone = new JTextField();
	private boolean selectRoot;

	public TelaUsuarioCadastrar() {
		initComponents();
	}

	public BigDecimal regExTelefoneBigDecimal() {
		String inputText = inputTelefone.getText();
		String numeroString = inputText.substring(1, 3) + inputText.substring(5, 10) + inputText.substring(13, 17);
		BigDecimal numeroReturn = new BigDecimal(numeroString);

		return numeroReturn;
	}

	public boolean regExEmail() {
		Pattern validEmail = Pattern.compile("^[A-Z0-9._-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);

		Matcher matcher = validEmail.matcher(inputEmail.getText());
		return matcher.find();
	}

	@SuppressWarnings("deprecation")
	private boolean validarCampos() {
		boolean valido = true;
		if (inputSenha.getText().equals("") && inputConfSenha.getText().equals("") && inputEmail.getText().equals("")
				&& inputLogin.getText().equals("") && inputNomeUsuario.getText().equals("")
				&& inputTelefone.getText().equals("")) {
			valido = false;
			JOptionPane.showMessageDialog(null, "Todos os campos em branco", "Campo vazio",
					JOptionPane.WARNING_MESSAGE);
			return false;
		}

		char[] novaSenha = inputSenha.getPassword();
		char[] confNovaSenha = inputConfSenha.getPassword();
		if (novaSenha.length != confNovaSenha.length) {
			JOptionPane.showMessageDialog(null, "Confirmação de senha não corresponde a nova senha", "Campo incorreto",
					JOptionPane.WARNING_MESSAGE);
			valido = false;
			return false;
		} else {
			for (int i = 0; i < novaSenha.length; i++) {
				if (novaSenha[i] != confNovaSenha[i]) {
					JOptionPane.showMessageDialog(null, "Confirmação de senha não corresponde a nova senha",
							"Campo incorreto", JOptionPane.WARNING_MESSAGE);
					valido = false;
					return false;
				}
			}
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
		if (inputTelefone.getText().length() < 17) {
			valido = false;
			JOptionPane.showMessageDialog(null, "O telefone esta incompleto");
			return false;
		}

		if(!rbAdmSim.isSelected() && rbAdmNao.isSelected()) {
			valido = false;
			JOptionPane.showMessageDialog(null, "Selecione um tipo de usuario");
			return false;
		}
		
		
		UsuarioDAO usuarioDB = new UsuarioDAO();
		for(Usuario user :  usuarioDB.exibirUsuarios() ) {
			if(inputLogin.getText().equals(user.getUserLogin())) {
				valido = false;
				JOptionPane.showMessageDialog(null, "Este usuario já existe");
				return false;
			}
		}

		return valido;
	}

	@SuppressWarnings("deprecation")
	private void enviarUsuario() {
		if (validarCampos() == true) {
			UsuarioDAO user = new UsuarioDAO();
			BigDecimal telefone = regExTelefoneBigDecimal();
			String novaSenha = inputConfSenha.getText();

			user.cadastrarUsuario(inputNomeUsuario.getText(), inputEmail.getText(), telefone, inputLogin.getText(), novaSenha, selectRoot);
		}
	}

	private void initComponents() {
		divCadastrarUsuario.setBorder(BorderFactory.createTitledBorder("Adicionar novo usuario"));

		lbEmail.setText("EMAIL:");

		lbTelefone.setText("TELEFONE:");

		lbLogin.setText("LOGIN:");

		lbSenha.setText("SENHA:");

		lbNomeUsuario.setText("NOME USUARIO:");

		lbConfSenha.setText("REPITA SENHA:");

		lbUsuarioAdmin.setText("USUARIO ADMIN?:");		
		rbAdmSim.setText("SIM");
		rbAdmNao.setText("NÃO");
		
		ButtonGroup editableGroup = new ButtonGroup();
		editableGroup.add(rbAdmSim);
		editableGroup.add(rbAdmNao);

		rbAdmSim.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(rbAdmSim.isSelected()) {
					selectRoot = true;
				} else {
					selectRoot = false;
				}
			}
		});
		
		rbAdmNao.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(rbAdmNao.isSelected()) {
					selectRoot = true;
				} else {
					selectRoot = false;
				}
			}
		});
		
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
		
		btConfirmaAlteracao.setText("Confirmar");
		btConfirmaAlteracao.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				enviarUsuario();
			}
		});


		GroupLayout divCadastrarUsuarioLayout = new GroupLayout(divCadastrarUsuario);
		divCadastrarUsuario.setLayout(divCadastrarUsuarioLayout);
		divCadastrarUsuarioLayout.setHorizontalGroup(divCadastrarUsuarioLayout
				.createParallelGroup(GroupLayout.Alignment.LEADING)
				.addGroup(divCadastrarUsuarioLayout.createSequentialGroup().addGap(68, 68, 68)
						.addGroup(divCadastrarUsuarioLayout
								.createParallelGroup(GroupLayout.Alignment.TRAILING).addComponent(lbConfSenha)
								.addComponent(lbSenha).addComponent(lbEmail).addComponent(lbNomeUsuario)
								.addComponent(lbTelefone).addComponent(lbLogin).addComponent(lbUsuarioAdmin))
						.addGap(28, 28, 28)
						.addGroup(divCadastrarUsuarioLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
								.addGroup(divCadastrarUsuarioLayout.createSequentialGroup()
										.addGroup(divCadastrarUsuarioLayout
												.createParallelGroup(GroupLayout.Alignment.LEADING)
												.addGroup(divCadastrarUsuarioLayout.createSequentialGroup()
														.addGroup(divCadastrarUsuarioLayout
																.createParallelGroup(GroupLayout.Alignment.LEADING)
																.addComponent(inputSenha, GroupLayout.DEFAULT_SIZE, 116,
																		Short.MAX_VALUE)
																.addComponent(inputLogin).addComponent(inputConfSenha))
														.addGap(364, 364, 364))
												.addGroup(divCadastrarUsuarioLayout.createSequentialGroup()
														.addComponent(rbAdmSim).addGap(18, 18, 18)
														.addComponent(rbAdmNao)
														.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED,
																GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
										.addComponent(btConfirmaAlteracao, GroupLayout.PREFERRED_SIZE, 161,
												GroupLayout.PREFERRED_SIZE)
										.addGap(40, 40, 40))
								.addGroup(divCadastrarUsuarioLayout.createSequentialGroup()
										.addGroup(divCadastrarUsuarioLayout
												.createParallelGroup(GroupLayout.Alignment.LEADING)
												.addGroup(divCadastrarUsuarioLayout
														.createParallelGroup(GroupLayout.Alignment.TRAILING, false)
														.addComponent(inputNomeUsuario, GroupLayout.Alignment.LEADING,
																GroupLayout.DEFAULT_SIZE, 250, Short.MAX_VALUE)
														.addComponent(inputEmail, GroupLayout.Alignment.LEADING))
												.addComponent(inputTelefone, GroupLayout.PREFERRED_SIZE, 150,
														GroupLayout.PREFERRED_SIZE))
										.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))));
		divCadastrarUsuarioLayout.setVerticalGroup(divCadastrarUsuarioLayout
				.createParallelGroup(GroupLayout.Alignment.LEADING)
				.addGroup(divCadastrarUsuarioLayout.createSequentialGroup().addGap(38, 38, 38)
						.addGroup(divCadastrarUsuarioLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
								.addComponent(inputNomeUsuario, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE)
								.addComponent(lbNomeUsuario))
						.addGap(18, 18, 18)
						.addGroup(divCadastrarUsuarioLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
								.addComponent(lbEmail).addComponent(inputEmail, GroupLayout.PREFERRED_SIZE,
										GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addGap(18, 18, 18)
						.addGroup(divCadastrarUsuarioLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
								.addComponent(lbTelefone).addComponent(inputTelefone, GroupLayout.PREFERRED_SIZE,
										GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addGap(18, 18, 18)
						.addGroup(divCadastrarUsuarioLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
								.addComponent(lbLogin).addComponent(inputLogin, GroupLayout.PREFERRED_SIZE,
										GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addGroup(divCadastrarUsuarioLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
								.addGroup(divCadastrarUsuarioLayout.createSequentialGroup().addGap(18, 18, 18)
										.addGroup(divCadastrarUsuarioLayout
												.createParallelGroup(GroupLayout.Alignment.BASELINE)
												.addComponent(lbSenha)
												.addComponent(inputSenha, GroupLayout.PREFERRED_SIZE,
														GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
										.addGap(18, 18, 18)
										.addGroup(divCadastrarUsuarioLayout
												.createParallelGroup(GroupLayout.Alignment.BASELINE)
												.addComponent(lbConfSenha)
												.addComponent(inputConfSenha, GroupLayout.PREFERRED_SIZE,
														GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
										.addGap(18, 18, 18)
										.addGroup(divCadastrarUsuarioLayout
												.createParallelGroup(GroupLayout.Alignment.BASELINE)
												.addComponent(lbUsuarioAdmin).addComponent(rbAdmSim)
												.addComponent(rbAdmNao))
										.addContainerGap(31, Short.MAX_VALUE))
								.addGroup(GroupLayout.Alignment.TRAILING,
										divCadastrarUsuarioLayout.createSequentialGroup()
												.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED,
														GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
												.addComponent(btConfirmaAlteracao, GroupLayout.PREFERRED_SIZE, 61,
														GroupLayout.PREFERRED_SIZE)
												.addGap(31, 31, 31)))));

		GroupLayout layout = new GroupLayout(this);
		this.setLayout(layout);
		layout.setHorizontalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
				.addGroup(layout
						.createSequentialGroup().addContainerGap().addComponent(divCadastrarUsuario,
								GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addContainerGap()));
		layout.setVerticalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
				.addGroup(layout
						.createSequentialGroup().addContainerGap().addComponent(divCadastrarUsuario,
								GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addContainerGap(246, Short.MAX_VALUE)));
	}
}
