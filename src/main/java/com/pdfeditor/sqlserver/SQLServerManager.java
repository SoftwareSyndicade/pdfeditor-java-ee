package com.pdfeditor.sqlserver;

import com.pdfeditor.datamodels.RecentUpload;
import com.pdfeditor.properties.MSSqlServerProps;

import java.lang.reflect.InvocationTargetException;
import java.sql.*;
import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.TimeZone;

public class SQLServerManager {

    public boolean saveUpload(String fileName) throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException, SQLException {

        boolean isSaved = false;
        int index = 0;
        Class.forName(MSSqlServerProps.DB_DRIVER).getDeclaredConstructor().newInstance();
        try(Connection conn = DriverManager.getConnection(MSSqlServerProps.CONNECTION_STRING)){
            try(PreparedStatement ps = conn.prepareStatement(SQLQueries.INSERT_RECENT_PDF)){
                ps.setString(++index, fileName);
                ps.setTimestamp(++index, Timestamp.from(Instant.now()));

                isSaved = ps.executeUpdate() > 0;
            }
        }

        return isSaved;
    }

    public List<RecentUpload> fetchUploads() throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException, SQLException{
        List<RecentUpload> recentUploads = new ArrayList<>();
        int index = 0;
        Class.forName(MSSqlServerProps.DB_DRIVER).getDeclaredConstructor().newInstance();
        try(Connection conn = DriverManager.getConnection(MSSqlServerProps.CONNECTION_STRING)){
            try(PreparedStatement ps = conn.prepareStatement(SQLQueries.FETCH_RECENT_PDF); ResultSet rs = ps.executeQuery()){
                while (rs.next()){
                    recentUploads.add(new RecentUpload(){{
                        setID(rs.getInt("ID"));
                        setFILE_NAME(rs.getString("FILE_NAME"));
                        setUPLOADED_ON(rs.getTimestamp("UPLOADED_ON").toInstant().atZone(TimeZone.getDefault().toZoneId()));
                    }});
                }
            }
        }

        return  recentUploads;
    }

    public boolean clearDB() throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException, SQLException{
        boolean isClear = false;
        Class.forName(MSSqlServerProps.DB_DRIVER).getDeclaredConstructor().newInstance();
        try(Connection conn = DriverManager.getConnection(MSSqlServerProps.CONNECTION_STRING)){
            try(PreparedStatement ps = conn.prepareStatement(SQLQueries.CLEAR_RECENT_PDF);){
                isClear = ps.executeUpdate() > 0;
            }
        }
        return isClear;
    }



}
