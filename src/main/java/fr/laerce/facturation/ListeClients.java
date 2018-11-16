package fr.laerce.facturation;

import fr.laerce.facturation.model.Client;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.*;
import java.util.*;


/*
* Externaliser
* - l'hote du sgbd;
* - le nom de la BDD;
* - le nom de l'user;
* - le MdP de l'user.
*
* Pour chaque client afficher une fiche détaillé et ne laissez
* que le nom et prénom dans la liste
* */
public class ListeClients extends HttpServlet {
    Connection conn;
    Statement req;

    @Override
    protected void doGet(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws ServletException, IOException {

        try {

            String query = "SELECT clt_num, clt_nom, clt_pnom, clt_loc, clt_pays FROM clients;";
            ResultSet res = req.executeQuery(query);
            List<Client> clients = new ArrayList<Client>();
            while(res.next()){
                clients.add(new Client(res.getString("clt_num"),
                        res.getString("clt_nom"),
                        res.getString("clt_pnom"),
                        res.getString("clt_loc"),
                        res.getString("clt_pays")));
            }
            httpServletRequest.setAttribute("clients", clients);

            String laVue = "clients.jsp";
            getServletConfig().getServletContext()
                    .getRequestDispatcher("/WEB-INF/jsp/"+laVue)
                    .forward(httpServletRequest, httpServletResponse);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void init() throws ServletException {
        super.init();

//        ConnexionSGBD configConn = new ConnexionSGBD();
//
//        conn = configConn.getConnection();
//        req = configConn.getReq();
//
        String nom_driver = getServletContext().getInitParameter("nom_driver");
        String DB_url = getServletContext().getInitParameter("DB_url");
        String DB_name = getServletContext().getInitParameter("DB_name");

        String user = getServletContext().getInitParameter("id_user");
        String psw = getServletContext().getInitParameter("psw_user");

        try {
            Class.forName(nom_driver);
//            Class.forName("org.postgresql.Driver");
            Properties props = new Properties();
            props.setProperty("user", user);
            props.setProperty("password", psw);
            conn = DriverManager.getConnection(DB_url+DB_name, props);
//            conn = DriverManager.getConnection("jdbc:postgresql://localhost/exemple", props);

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            throw new ServletException("Pas de Driver SQL");
        } catch (SQLException e) {
            e.printStackTrace();
            throw new ServletException("Pas de connexion à la base");
        }

        try{
            req = conn.createStatement();
        }catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
