package fr.laerce.facturation.model;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.rmi.server.ExportException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DetailClient extends HttpServlet {

    protected void doGet(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws ServletException, IOException{
//        Connection conn;
//        Statement req;
        Client client = null;

        String pnom = httpServletRequest.getParameter("prenom");
        System.out.println(pnom);

        try {
//            conn = (Connection) getServletContext().getAttribute("connexion");
//            req = (Statement) getServletContext().getAttribute("statement");

//            String query = "SELECT clt_num, clt_nom, clt_pnom, clt_loc, clt_pays FROM clients WHERE clt_pnom='" + pnom + "';";
//            ResultSet res = req.executeQuery(query);

            PreparedStatement prep = (PreparedStatement) getServletContext().getAttribute("PS_detail");
            prep.setString(1, pnom);
            ResultSet res = prep.executeQuery();

            while(res.next()){
                client = new Client(res.getString("clt_num"),
                        res.getString("clt_nom"),
                        res.getString("clt_pnom"),
                        res.getString("clt_loc"),
                        res.getString("clt_pays"));

            }
            httpServletRequest.setAttribute("client", client);
            String laVue = "detail.jsp";
            getServletConfig().getServletContext()
                    .getRequestDispatcher("/WEB-INF/jsp/"+laVue)
                    .forward(httpServletRequest, httpServletResponse);

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e){
            e.printStackTrace();
        }

    }
}
