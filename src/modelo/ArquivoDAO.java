package modelo;

import conexao.ConnectionMySql;
import controle.ConsultaArquivo;

import java.util.List;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.Date;

import javax.swing.JOptionPane;

public class ArquivoDAO {

	public void adicionarArquivo(InputStream file, int fileLenght, String fileName, String type, String description,
		Date localDate, int userId, int agencia) throws InterruptedException {
		Connection connect = ConnectionMySql.getConnection();
		PreparedStatement stat = null;

		try {
			stat = connect.prepareStatement(
					"INSERT INTO archives "
					+ "(archive, archiveName, archiveType, archiveDescription, archiveDate, user_userID, agencia_agenciaID)"
					+ "VALUES "
					+ "(?,?,?,?,?,?,?)");
			stat.setBinaryStream(1, file, fileLenght);
			stat.setString(2, fileName);
			stat.setString(3, type);
			stat.setString(4, description);
			stat.setDate(5, localDate);
			stat.setInt(6, userId);
			stat.setInt(7, agencia);

			stat.execute();

			JOptionPane.showMessageDialog(null, "Arquivo enviado com sucesso", "Sucesso",
					JOptionPane.INFORMATION_MESSAGE);

		} catch (SQLException ex) {
			JOptionPane.showMessageDialog(null, "Erro ao salvar o arquivo " + ex.getMessage());
		} finally {
			ConnectionMySql.endConnection(connect, stat);
		}

	}
	
	public String querySelectorConsult() {
		ConsultaArquivo consultaArquivo = new ConsultaArquivo();
		String selectFormat = null;
		String data = consultaArquivo.getData();
		String nome = consultaArquivo.getNome();
		String usuario = consultaArquivo.getUsuario();
		
		if(!data.equals("") && nome.equals("") && usuario.equals("")) {
			selectFormat = 
					"SELECT * FROM archives where "
					+ "agencia_agenciaID = " + consultaArquivo.getAgencia()
					+ " and archiveDate = '" + consultaArquivo.getData() + "'";
		}
		
		if(data.equals("") && !nome.equals("") && usuario.equals("")) {
			selectFormat = 
					"SELECT * FROM archives where "
					+ "agencia_agenciaID = " + consultaArquivo.getAgencia()
					+ " and archiveName like '%" + consultaArquivo.getNome() + "%'";
		}
		
		if(data.equals("") && nome.equals("") && !usuario.equals("")) {
			selectFormat = 
					"SELECT * FROM archives where "
					+ "agencia_agenciaID = " + consultaArquivo.getAgencia()
					+ " and archiveUser = " + consultaArquivo.getUsuario();
		}
		
		if(!data.equals("") && !nome.equals("") && usuario.equals("")) {
			selectFormat = 
					"SELECT * FROM archives where "
					+ "agencia_agenciaID = " + consultaArquivo.getAgencia()
					+ " and archiveDate = '" + consultaArquivo.getData() + "'"
					+ " and archiveName like '%" + consultaArquivo.getNome() + "%'";
		}
		
		if(data.equals("") && !nome.equals("") && !usuario.equals("")) {
			selectFormat = 
					"SELECT * FROM archives where "
					+ "agencia_agenciaID = " + consultaArquivo.getAgencia()
					+ " and archiveName like '%" + consultaArquivo.getNome() + "%'"
					+ " and archiveUser = " + consultaArquivo.getUsuario();
		}
		
		if(!data.equals("") && nome.equals("") && !usuario.equals("")) {
			selectFormat = 
					"SELECT * FROM archives where "
					+ "agencia_agenciaID = " + consultaArquivo.getAgencia()
					+ " and archiveDate = '" + consultaArquivo.getData() + "'"
					+ " and archiveUser = " + consultaArquivo.getUsuario();
		}
		
		if(!data.equals("") && !nome.equals("") && !usuario.equals("")) {
			selectFormat = 
					"SELECT * FROM archives where "
					+ "agencia_agenciaID = " + consultaArquivo.getAgencia()
					+ " and archiveDate = '" + consultaArquivo.getData() + "'"
					+ " and archiveName = '" + consultaArquivo.getNome() + "%'"
					+ " and archiveUser = " + consultaArquivo.getUsuario();
		}
		
		if(data.equals("") && nome.equals("") && usuario.equals("")) {
			selectFormat = "SELECT * FROM archives";
		}
		
		System.out.println(selectFormat);
		
		return selectFormat;
	}
	
	public List<Arquivo> exibirArquivos() {
		Connection connect = ConnectionMySql.getConnection();
		PreparedStatement stat = null;
		ResultSet result = null;

		List<Arquivo> arquivos = new ArrayList<>();

		try {
			stat = connect.prepareStatement(querySelectorConsult());
			result = stat.executeQuery();

			while (result.next()) {

				Arquivo arquivo;
				arquivo = new Arquivo();

				arquivo.setArchive(result.getBlob("archive"));
				arquivo.setArchiveName(result.getString("archiveName"));
				arquivo.setArchiveType(result.getString("archiveType"));
				arquivo.setArchiveDescription(result.getString("archiveDescription"));
				arquivo.setAgencia_agenciaID(result.getInt("agencia_agenciaID"));

				arquivo.setArchiveDate(result.getDate("archiveDate"));
				arquivo.setUser_userID(result.getInt("user_userID"));
				arquivo.setArchiveID(result.getInt("archiveID"));

				arquivos.add(arquivo);

			}
		} catch (SQLException ex) {
			JOptionPane.showMessageDialog(null, "Erro ao exibir o arquivo");
		} finally {
			ConnectionMySql.endConnection(connect, stat, result);
		}
		return arquivos;

	}

	public Blob arquivoBlob(int arquivoID) {
		Connection connect = ConnectionMySql.getConnection();
		PreparedStatement stat = null;
		ResultSet result = null;
		Blob arquivo = null;

		try {
			stat = connect.prepareStatement("SELECT * FROM archives WHERE archiveID = ?");
			stat.setInt(1, arquivoID);
			result = stat.executeQuery();

			while (result.next()) {

				arquivo = (result.getBlob("archive"));

			}
		} catch (SQLException ex) {
			JOptionPane.showMessageDialog(null, "Erro ao exibir o arquivo");
		} finally {
			ConnectionMySql.endConnection(connect, stat, result);
		}
		return arquivo;
	}

}
