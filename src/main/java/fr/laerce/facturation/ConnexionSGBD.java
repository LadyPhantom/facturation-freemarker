package fr.laerce.facturation;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import java.sql.*;
import java.util.Properties;

public class ConnexionSGBD extends HttpServlet {
    private Connection connection;
    private Statement req;

    public ConnexionSGBD() throws ServletException {

        String nom_driver = getServletContext().getInitParameter("nom_driver");
        String DB_url = getServletContext().getInitParameter("DB_url");
        String DB_name = getServletContext().getInitParameter("DB_name");

        String user = getServletContext().getInitParameter("id_user");
        String psw = getServletContext().getInitParameter("psw_user");

        try {
            Class.forName(nom_driver);
            Properties props = new Properties();
            props.setProperty("user", user);
            props.setProperty("password", psw);
            connection = DriverManager.getConnection(DB_url+DB_name, props);

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            throw new ServletException("Pas de Driver SQL");
        } catch (SQLException e) {
            e.printStackTrace();
            throw new ServletException("Pas de connexion à la base");
        }

        try{
            req = connection.createStatement();
        }catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Connection getConnection() {
        return connection;
    }

    public Statement getReq() {
        return req;
    }
}
