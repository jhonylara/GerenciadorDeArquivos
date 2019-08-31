package controle;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.sql.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import modelo.AgenciaDAO;
import modelo.ArquivoDAO;

public class InserirArquivo {

	ArquivoDAO arq = new ArquivoDAO();
	
	public void insertFile(File file, String agencia, String description) throws FileNotFoundException, InterruptedException {
		String typeFile = regExrType(file.getName());
		String nameFile = regExtName(file.getName());
		
		AgenciaDAO ag = new AgenciaDAO();
		int agenciaID = ag.exibirValorAgencia(agencia);
		
		int iduser = UserLog.userID;
		
		int fileLenght = (int)file.length();
		InputStream fileBlob = new FileInputStream(file);
		
		Date localDate = new Date(System.currentTimeMillis());
		
		arq.adicionarArquivo(fileBlob, fileLenght, nameFile, typeFile, description, localDate, iduser, agenciaID);
	}
	
	private String regExtName(String value) {
		String valueReverse = new StringBuffer(value).reverse().toString();
		String regExr = new String();
		
		Matcher matcher = Pattern.compile("\\.(.*)").matcher(valueReverse);
		while (matcher.find()) {
			regExr = matcher.group(1);
		}

		String valueReturn = new StringBuffer(regExr).reverse().toString();
		
		return valueReturn;
	}

	public String regExrType(String value) {
		String valueReverse = new StringBuffer(value).reverse().toString();
		String regExr = new String();
		
		Matcher matcher = Pattern.compile("(.*)\\.").matcher(valueReverse);
		while (matcher.find()) {
			regExr = matcher.group(1);
		}

		String valueReturn = new StringBuffer(regExr).reverse().toString();

		return valueReturn;
	}
}
