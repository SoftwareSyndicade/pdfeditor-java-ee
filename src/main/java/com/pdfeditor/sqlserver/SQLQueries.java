package com.pdfeditor.sqlserver;

public class SQLQueries {

    public static final String INSERT_RECENT_PDF = "INSERT INTO RECENT_PDF(FILE_NAME, UPLOADED_ON) VALUES(?,?)";
    public static final String FETCH_RECENT_PDF = "SELECT ID, FILE_NAME, UPLOADED_ON FROM RECENT_PDF";
    public static final String CLEAR_RECENT_PDF = "DELETE FROM RECENT_PDF";
}
