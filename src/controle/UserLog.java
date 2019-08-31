package controle;

import java.math.BigDecimal;

import modelo.UsuarioDAO;


public class UserLog {
	public static int userID;
	public static String userName;
	public static String userEmail;
	public static BigDecimal userPhone;
	public static String userLogin;
	public static String userPassword;
	public static boolean userRoot;
	
	UsuarioDAO user = new UsuarioDAO();
	
	public void usuarioAtual(String login) {
		user.dadosUsuario(login);
	}
}
