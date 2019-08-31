/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.sql.Blob;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Arquivo {
    private int archiveID;
    private Blob archive;
    private String archiveName;
    private String archiveType;
    private String archiveDescription;
    private Date archiveDate;
    private int user_userID;
    private int agencia_agenciaID;  
        
    public int getArchiveID() {
        return archiveID;
    }

    public void setArchiveID(int archiveID) {
        this.archiveID = archiveID;
    }

    public Blob getArchive() {
        return archive;
    }

    public void setArchive(Blob archive) {
        this.archive = archive;
    }

    public String getArchiveName() {
        return archiveName;
    }

    public void setArchiveName(String archiveName) {
        this.archiveName = archiveName;
    }

    public String getArchiveType() {
        return archiveType;
    }

    public void setArchiveType(String archiveType) {
        this.archiveType = archiveType;
    }


    public String getArchiveDate() throws ParseException {
		String formatDateDB = "yyyy-MM-dd";
		String formatDateView = "dd/MM/yyyy";

		String dateDB = archiveDate.toString();
		SimpleDateFormat transformDate = new SimpleDateFormat(formatDateDB);
		Date date = transformDate.parse(dateDB);
		transformDate.applyPattern(formatDateView);
		
        return transformDate.format(date);
    }

    public void setArchiveDate(Date archiveDate) {
        this.archiveDate = archiveDate;
    }

    public int getUser_userID() {
        return user_userID;
    }

    public void setUser_userID(int user_userID) {
        this.user_userID = user_userID;
    }

    public int getAgencia_agenciaID() {
        return agencia_agenciaID;
    }

    public void setAgencia_agenciaID(int agencia_agenciaID) {
        this.agencia_agenciaID = agencia_agenciaID;
    }

	public String getArchiveDescription() {
		return archiveDescription;
	}

	public void setArchiveDescription(String archiveDescription) {
		this.archiveDescription = archiveDescription;
	}

}
