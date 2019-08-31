/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import conexao.ConnectionMySql;
import controle.UserLog;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

public class UsuarioDAO {

	public boolean autentificaUsuario(String login, String senha) {

		Connection connect = ConnectionMySql.getConnection();
		PreparedStatement stat = null;
		ResultSet result = null;
		boolean autentificado = false;

		try {
			stat = connect.prepareStatement("SELECT * FROM users WHERE userLogin = ? and userPassword = ?");
			stat.setString(1, login);
			stat.setString(2, senha);
			result = stat.executeQuery();

			if (result.next()) {
				autentificado = true;
			}
		} catch (SQLException ex) {
			JOptionPane.showMessageDialog(null, "ERRO AO TENTAR PEGAR LOGIN E SENHA");
		} finally {
			ConnectionMySql.endConnection(connect, stat, result);
		}
		return autentificado;

	}

	public String nomeUsuario(int userID) {
		Connection connect = ConnectionMySql.getConnection();
		PreparedStatement stat = null;
		ResultSet result = null;
		String nome = null;

		try {
			stat = connect.prepareStatement("SELECT * FROM users WHERE userID = ?");
			stat.setInt(1, userID);
			result = stat.executeQuery();

			if (result.next()) {
				nome = result.getString("userName");
			}

		} catch (SQLException ex) {
			JOptionPane.showMessageDialog(null, "ERRO AO PEGAR NOME DO USUARIO POR ID");
		} finally {
			ConnectionMySql.endConnection(connect, stat, result);
		}

		return nome;
	}

	public void dadosUsuario(String login) {

		Connection connect = ConnectionMySql.getConnection();
		PreparedStatement stat = null;
		ResultSet result = null;

		try {
			stat = connect.prepareStatement("SELECT * FROM users WHERE userLogin = ?");
			stat.setString(1, login);
			result = stat.executeQuery();

			if (result.next()) {
				UserLog.userID = result.getInt("userID");
				UserLog.userName = result.getString("userName");
				UserLog.userEmail = result.getString("userEmail");
				UserLog.userPhone = result.getBigDecimal("userPhone");
				UserLog.userLogin = result.getString("userLogin");
				UserLog.userPassword = result.getString("userPassword");
				UserLog.userRoot = result.getBoolean("userRoot");
			}
		} catch (SQLException ex) {
			JOptionPane.showMessageDialog(null, "ERRO AO PEGAR DADOS DO USUARIO");
		} finally {
			ConnectionMySql.endConnection(connect, stat, result);
		}

	}

	public List<Usuario> exibirUsuarios() {

		Connection connect = ConnectionMySql.getConnection();
		PreparedStatement stat = null;
		ResultSet result = null;

		List<Usuario> usuarios = new ArrayList<>();

		try {
			stat = connect.prepareStatement("SELECT * FROM users");
			result = stat.executeQuery();

			while (result.next()) {

				Usuario user = new Usuario();

				user.setUserID(result.getInt("userID"));
				user.setUserName(result.getString("userName"));
				user.setUserEmail(result.getString("userEmail"));
				user.setUserPhone(result.getBigDecimal("userPhone"));
				user.setUserLogin(result.getString("userLogin"));
				user.setUserPassword(result.getString("userPassword"));
				user.setUserRoot(result.getBoolean("userRoot"));

				usuarios.add(user);

			}
		} catch (SQLException ex) {
			JOptionPane.showMessageDialog(null, "Erro ao exibir o arquivo");
		} finally {
			ConnectionMySql.endConnection(connect, stat, result);
		}
		return usuarios;

	}

	public List<Usuario> pegarUsuario(int ID) {

		Connection connect = ConnectionMySql.getConnection();
		PreparedStatement stat = null;
		ResultSet result = null;

		List<Usuario> usuarios = new ArrayList<>();

		try {
			stat = connect.prepareStatement("SELECT * FROM users WHERE userID = ?");
			stat.setInt(1, ID);
			result = stat.executeQuery();
			while (result.next()) {

				Usuario user = new Usuario();

				user.setUserID(result.getInt("userID"));
				user.setUserName(result.getString("userName"));
				user.setUserEmail(result.getString("userEmail"));
				user.setUserPhone(result.getBigDecimal("userPhone"));
				user.setUserLogin(result.getString("userLogin"));
				user.setUserPassword(result.getString("userPassword"));
				user.setUserRoot(result.getBoolean("userRoot"));

				usuarios.add(user);

			}
		} catch (SQLException ex) {
			JOptionPane.showMessageDialog(null, "Erro ao exibir o arquivo");
		} finally {
			ConnectionMySql.endConnection(connect, stat, result);
		}
		return usuarios;
	}

	public void alterarUsuario(String userName, String userEmail, BigDecimal userPhone, int userID) {

		Connection connect = ConnectionMySql.getConnection();
		PreparedStatement stat = null;

		try {
			stat = connect
					.prepareStatement("UPDATE users SET userName = ?, userEmail = ?, userPhone = ? WHERE userID = ?");

			stat.setString(1, userName);
			stat.setString(2, userEmail);
			stat.setBigDecimal(3, userPhone);
			stat.setInt(4, userID);
			stat.executeUpdate();

			JOptionPane.showMessageDialog(null, "Usuario alterado com sucesso");
		} catch (SQLException ex) {
			JOptionPane.showMessageDialog(null, "ERRO AO ALTERAR USUARIO" + ex);
		} finally {
			ConnectionMySql.endConnection(connect, stat);
		}
	}

	public void alterarSenha(String userPassword, int userID) {
		Connection connect = ConnectionMySql.getConnection();
		PreparedStatement stat = null;

		try {
			stat = connect.prepareStatement("UPDATE users SET userPassword = ? WHERE userID = ?");
			stat.setString(1, userPassword);
			stat.setInt(2, userID);

			stat.executeUpdate();
			JOptionPane.showMessageDialog(null, "Senha alterada com sucesso");
		} catch (SQLException ex) {
			JOptionPane.showMessageDialog(null, "ERRO AO ALTERAR SENHA" + ex);
		} finally {
			ConnectionMySql.endConnection(connect, stat);
		}
	}

	public void cadastrarUsuario(String userName, String userEmail, BigDecimal userPhone, String userLogin,
			String userPassword, boolean userRoot) {
		Connection connect = ConnectionMySql.getConnection();
		PreparedStatement stat = null;

		try {
			stat = connect.prepareStatement(
					"insert into users(userName, userEmail, userPhone, userLogin, userPassword, userRoot)"
							+ " values (?, ?, ?, ?, ?, ?)");
			stat.setString(1, userName);
			stat.setString(2, userEmail);
			stat.setBigDecimal(3, userPhone);
			stat.setString(4, userLogin);
			stat.setString(5, userPassword);
			stat.setBoolean(6, userRoot);

			stat.executeUpdate();
			JOptionPane.showMessageDialog(null, "Usuario cadastrado com sucesso");
		} catch (SQLException ex) {
			JOptionPane.showMessageDialog(null, "ERRO AO CADASTRAR USUARIO" + ex);
		} finally {
			ConnectionMySql.endConnection(connect, stat);
		}
	}

	public void alterarUsuario(int idUsuario, String userName, String userEmail, BigDecimal userPhone, String userLogin,
			String userPassword, boolean userRoot) {
		Connection connect = ConnectionMySql.getConnection();
		PreparedStatement stat = null;

		try {
			stat = connect.prepareStatement(
					"UPDATE users SET userName = ?, userEmail = ?, userPhone = ?, userLogin = ?, userPassword = ?, userRoot = ? WHERE userID = ?");
			stat.setString(1, userName);
			stat.setString(2, userEmail);
			stat.setBigDecimal(3, userPhone);
			stat.setString(4, userLogin);
			stat.setString(5, userPassword);
			stat.setBoolean(6, userRoot);
			stat.setInt(7, idUsuario);

			stat.executeUpdate();
			JOptionPane.showMessageDialog(null, "Usuario alterado com sucesso");
		} catch (SQLException ex) {
			JOptionPane.showMessageDialog(null, "ERRO AO ALTERAR USUARIO" + ex);
		} finally {
			ConnectionMySql.endConnection(connect, stat);
		}
	}

	public void excluirUsuario(int userID) {
		Connection connect = ConnectionMySql.getConnection();
		PreparedStatement stat = null;
		ResultSet result = null;

		try {
			stat = connect.prepareStatement("DELETE FROM users WHERE userID = ?");
			stat.setInt(1, userID);

			stat.executeUpdate();
			JOptionPane.showMessageDialog(null, "Usuario excluido com sucesso", "Excluir usuario",
					JOptionPane.UNDEFINED_CONDITION);
		} catch (SQLException ex) {
			JOptionPane.showMessageDialog(null, "Erro ao excluir usuario selecionado");
		} finally {
			ConnectionMySql.endConnection(connect, stat, result);
		}
	}
}
