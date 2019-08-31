package modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import conexao.ConnectionMySql;

public class AgenciaDAO {

	public void adicionarAgencia(Agencia arq) {

		Connection connect = ConnectionMySql.getConnection();
		PreparedStatement stat = null;

		try {
			stat = connect.prepareStatement("INSERT INTO agencia (agenciaName, regional_regionalID) VALUES (?,?)");
			stat.setString(1, arq.getAgenciaName());
			stat.setInt(2, arq.getRegional_regionalID());

			stat.executeUpdate();

			JOptionPane.showMessageDialog(null, "AGENCIA SALVA COM SUCESSO");

		} catch (SQLException ex) {
			JOptionPane.showMessageDialog(null, "FALHA AO SALVAR AGENCIA NO BD");
		} finally {
			ConnectionMySql.endConnection(connect, stat);
		}

	}
	
	public void removerAgencia(int regional, String agencia) {
		Connection connect = ConnectionMySql.getConnection();
		PreparedStatement stat = null;
		ResultSet result = null;

		try {
			stat = connect.prepareStatement("DELETE FROM agencia WHERE agenciaName = ? and regional_regionalID = ?");
			stat.setString(1, agencia);
			stat.setInt(2, regional);

			stat.executeUpdate();
		} catch (SQLException ex) {
			JOptionPane.showMessageDialog(null, "Erro ao excluir a Agencia selecionada");
		} finally {
			JOptionPane.showMessageDialog(null, "Agencia deletada com sucesso", "Excluir agencia",
					JOptionPane.UNDEFINED_CONDITION);
			ConnectionMySql.endConnection(connect, stat, result);
		}
	}
	
	public void atualizarAgencia(String agenciaNova, int regional, String agencia) {
		Connection connect = ConnectionMySql.getConnection();
		PreparedStatement stat = null;
		ResultSet result = null;

		try {
			stat = connect.prepareStatement("UPDATE agencia SET agenciaName = ? WHERE agenciaName = ? and regional_regionalID = ?");
			stat.setString(1, agenciaNova);
			stat.setString(2, agencia);
			stat.setInt(3, regional);

			stat.executeUpdate();
		} catch (SQLException ex) {
			JOptionPane.showMessageDialog(null, "Erro ao atualizar a Agencia selecionada");
		} finally {
			JOptionPane.showMessageDialog(null, "Agencia atualizada com sucesso", "Atualizar Agencia",
					JOptionPane.UNDEFINED_CONDITION);
			ConnectionMySql.endConnection(connect, stat, result);
		}
	}

	public List<Agencia> exibirAgencia() {

		Connection connect = ConnectionMySql.getConnection();
		PreparedStatement stat = null;
		ResultSet result = null;

		List<Agencia> listagencias = new ArrayList<>();

		try {
			stat = connect.prepareStatement("SELECT * FROM agencia");
			result = stat.executeQuery();

			while (result.next()) {

				Agencia agencia = new Agencia();

				agencia.setAgenciaName(result.getString("agenciaName"));
				agencia.setRegional_regionalID(result.getInt("regional_regionalID"));

				listagencias.add(agencia);

			}
		} catch (SQLException ex) {
			JOptionPane.showMessageDialog(null, "Erro ao exibir o arquivo");
		} finally {
			ConnectionMySql.endConnection(connect, stat, result);
		}
		return listagencias;

	}

	public int exibirRegionalID(String regional) {

		Connection connect = ConnectionMySql.getConnection();
		PreparedStatement stat = null;
		ResultSet result = null;
		int numRegional = 0;

		try {
			stat = connect.prepareStatement("SELECT * FROM regional WHERE regionalName = ?");
			stat.setString(1, regional);
			result = stat.executeQuery();

			if (result.next()) {
				numRegional = result.getInt("regionalID");
			}
		} catch (SQLException ex) {
			JOptionPane.showMessageDialog(null, "ERRO AO EXIBIR ID REGIONAL");
		} finally {
			ConnectionMySql.endConnection(connect, stat, result);
		}

		return numRegional;

	}

	public ArrayList<String> exibirNomeAgenciaRegional(int regionalID) {
		Connection connect = ConnectionMySql.getConnection();
		PreparedStatement stat = null;
		ResultSet result = null;
		ArrayList<String> agenciaName = new ArrayList<String>();

		try {
			stat = connect.prepareStatement("SELECT agenciaName FROM agencia WHERE regional_regionalID = ?");
			stat.setInt(1, regionalID);
			result = stat.executeQuery();

			while (result.next()) {
				agenciaName.add(result.getString("agenciaName"));
			}
		} catch (SQLException ex) {
			JOptionPane.showMessageDialog(null, "ERRO AO EXIBIR ID REGIONAL");
		} finally {
			ConnectionMySql.endConnection(connect, stat, result);
		}

		return agenciaName;
	}

	public int exibirValorAgencia(String agencia) {

		Connection connect = ConnectionMySql.getConnection();
		PreparedStatement stat = null;
		ResultSet result = null;
		int numAgencia = 0;

		try {
			stat = connect.prepareStatement("SELECT * FROM agencia WHERE agenciaName = ?");
			stat.setString(1, agencia);
			result = stat.executeQuery();

			while (result.next()) {
				numAgencia = result.getInt("agenciaID");

			}
		} catch (SQLException ex) {
			JOptionPane.showMessageDialog(null, "ERRO AO EXIBIR ID AGENCIA");
		} finally {
			ConnectionMySql.endConnection(connect, stat, result);
		}

		return numAgencia;
	}

	public String exibirNomeAgencia(int id) {

		Connection connect = ConnectionMySql.getConnection();
		PreparedStatement stat = null;
		ResultSet result = null;
		String nomeAgencia = null;

		try {
			stat = connect.prepareStatement("SELECT * FROM agencia WHERE agenciaID = ?");
			stat.setInt(1, id);
			result = stat.executeQuery();

			if (result.next()) {
				nomeAgencia = result.getString("agenciaName");

			}
		} catch (SQLException ex) {
			JOptionPane.showMessageDialog(null, "ERRO AO EXIBIR ID AGENCIA");
		} finally {
			ConnectionMySql.endConnection(connect, stat, result);
		}

		return nomeAgencia;

	}

	public String consultaAgenciaRegional(int agenciaID) {
		Connection connect = ConnectionMySql.getConnection();
		PreparedStatement stat = null;
		ResultSet result = null;
		String nome = null;

		try {
			stat = connect.prepareStatement(
					"SELECT A.agenciaID, A.regional_regionalID , R.regionalName FROM agencia AS A JOIN regional AS R ON A.regional_regionalID = R.regionalID WHERE agenciaID = ?");
			stat.setInt(1, agenciaID);
			result = stat.executeQuery();

			if (result.next()) {
				nome = result.getString("regionalName");
			}

		} catch (SQLException ex) {
			JOptionPane.showMessageDialog(null, "ERRO AO PEGAR NOME DO REGIONAL POR ID");
		} finally {
			ConnectionMySql.endConnection(connect, stat, result);
		}

		return nome;
	}

	public ArrayList<String> agenciaBanco(String comando, String resultado, String id) {
		ArrayList<String> item = new ArrayList<String>();

		Connection connect = ConnectionMySql.getConnection();
		PreparedStatement stat = null;
		ResultSet result = null;

		try {

			stat = connect.prepareStatement(comando);
			stat.setString(1, id);
			result = stat.executeQuery();

			while (result.next()) {
				item.add(result.getString(resultado));
			}

		} catch (SQLException ex) {
			JOptionPane.showMessageDialog(null, "ERRO AO POPULAR STRING");
		} finally {
			ConnectionMySql.endConnection(connect, stat, result);
		}

		return item;
	}

	public ArrayList<String> passarParametrosAgencia(String id) {
		return agenciaBanco("SELECT * FROM agencia WHERE regional_regionalID = ?", "agenciaName", id);
	}

}
