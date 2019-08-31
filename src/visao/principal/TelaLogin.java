
package visao.principal;

import java.awt.event.KeyEvent;

import javax.swing.JOptionPane;

import controle.UserLog;
import modelo.UsuarioDAO;

public class TelaLogin extends javax.swing.JFrame {
	private static final long serialVersionUID = 8654151734857448246L;

	public TelaLogin() {
		initComponents();
	}

	@SuppressWarnings("deprecation")
	private void checkLogin() {
		UsuarioDAO usuario = new UsuarioDAO();

		if ((usuario.autentificaUsuario(campoLogin.getText(), campoSenha.getText())) == true) {
			UserLog user = new UserLog();

			user.usuarioAtual(campoLogin.getText());
			TelaPrincipal principal = new TelaPrincipal();
			principal.setVisible(true);
			dispose();

			if(UserLog.userName != null) {				
				String loginUpper = UserLog.userName.substring(0, 1).toUpperCase();
				String loginLower = UserLog.userName.substring(1, UserLog.userName.length());
				
				String campoComp = loginUpper + loginLower;
				JOptionPane.showMessageDialog(null, "Bem vindo " + campoComp + "!");
			}
		} else {
			JOptionPane.showMessageDialog(null, "Usuario invalido!");
		}
	}

	private void initComponents() {

		campoSenha = new javax.swing.JPasswordField();
		campoLogin = new javax.swing.JTextField();
		jLabel1 = new javax.swing.JLabel();
		jLabel2 = new javax.swing.JLabel();
		jLabel3 = new javax.swing.JLabel();
		botaoEntrar = new javax.swing.JButton();

		setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
		setTitle("GDA - GERENCIADOR DE ARQUIVOS");
		setResizable(false);

		jLabel1.setText("Usuario:");

		jLabel2.setText("Senha:");

		jLabel3.setText("GERENCIADOR DE ARQUIVOS");
		jLabel3.setToolTipText("");

		botaoEntrar.setText("Entrar");
		botaoEntrar.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				botaoEntrarActionPerformed(evt);
			}
		});

		botaoEntrar.addKeyListener(new java.awt.event.KeyAdapter() {
			public void keyPressed(java.awt.event.KeyEvent evt) {
				if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
					checkLogin();
				}
			}
		});

		campoSenha.addKeyListener(new java.awt.event.KeyAdapter() {
			public void keyPressed(java.awt.event.KeyEvent evt) {
				if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
					checkLogin();
				}
			}
		});

		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
		getContentPane().setLayout(layout);
		layout.setHorizontalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(layout
				.createSequentialGroup().addContainerGap(63, Short.MAX_VALUE)
				.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(javax.swing.GroupLayout.Alignment.TRAILING,
								layout.createSequentialGroup().addComponent(jLabel3).addGap(90, 90, 90))
						.addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
								.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
										.addComponent(jLabel1).addComponent(jLabel2))
								.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
								.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
										.addComponent(botaoEntrar)
										.addGroup(layout
												.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
												.addComponent(campoSenha).addComponent(campoLogin,
														javax.swing.GroupLayout.DEFAULT_SIZE, 111, Short.MAX_VALUE)))
								.addGap(105, 105, 105)))));
		layout.setVerticalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(
				javax.swing.GroupLayout.Alignment.TRAILING,
				layout.createSequentialGroup().addGap(42, 42, 42).addComponent(jLabel3).addGap(29, 29, 29)
						.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
								.addComponent(campoLogin, javax.swing.GroupLayout.PREFERRED_SIZE,
										javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
								.addComponent(jLabel1))
						.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 25, Short.MAX_VALUE)
						.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
								.addComponent(campoSenha, javax.swing.GroupLayout.PREFERRED_SIZE,
										javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
								.addComponent(jLabel2))
						.addGap(29, 29, 29).addComponent(botaoEntrar).addGap(37, 37, 37)));

		pack();
		setLocationRelativeTo(null);
	}

	private void botaoEntrarActionPerformed(java.awt.event.ActionEvent evt) {
		checkLogin();
	}

	public static void main(String args[]) {

		java.awt.EventQueue.invokeLater(() -> {
			new TelaLogin().setVisible(true);
		});
	}

	private javax.swing.JButton botaoEntrar;
	private javax.swing.JTextField campoLogin;
	private javax.swing.JPasswordField campoSenha;
	private javax.swing.JLabel jLabel1;
	private javax.swing.JLabel jLabel2;
	private javax.swing.JLabel jLabel3;

}
