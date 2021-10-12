package com.pdfeditor.sqlserver;

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

}
