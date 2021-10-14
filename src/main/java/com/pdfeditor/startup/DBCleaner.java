package com.pdfeditor.startup;

import com.pdfeditor.sqlserver.SQLServerManager;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebListener;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.Set;

@WebListener
public class DBCleaner implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        SQLServerManager manager = new SQLServerManager();
        try {
            manager.clearDB();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
