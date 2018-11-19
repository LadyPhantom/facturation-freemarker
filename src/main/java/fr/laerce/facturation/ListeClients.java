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

//            conn = httpServletRequest.getServletContext().(Connection)getInitParameter("connexion"); //original

//            conn = (Connection) getServletContext().getAttribute("connexion");
//            req = (Statement) getServletContext().getAttribute("statement");
//            String query = "SELECT clt_num, clt_nom, clt_pnom, clt_loc, clt_pays FROM clients;";
//            ResultSet res = req.executeQuery(query);

            PreparedStatement prep = (PreparedStatement) getServletContext().getAttribute("PS_listeAll");
            ResultSet res = prep.executeQuery();
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

//    @Override
//    public void init() throws ServletException {
//        super.init();
//    }
}
