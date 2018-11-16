//package fr.laerce.facturation.model;
//
//import javax.servlet.ServletException;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.util.ArrayList;
//import java.util.List;
//
//public class DetailClient extends HttpServlet {
//    protected void doGet(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws ServletException, IOException{
//
//        try {
//
//           // String query = "SELECT clt_num, clt_nom, clt_pnom, clt_loc, clt_pays FROM clients";
//            ResultSet res = req.executeQuery(query);
//            while(res.next()){
//                clients.add(new Client(res.getString("clt_num"),
//                        res.getString("clt_nom"),
//                        res.getString("clt_pnom"),
//                        res.getString("clt_loc"),
//                        res.getString("clt_pays")));
//            }
//            httpServletRequest.setAttribute("clients", clients);
//            String laVue = "clients.jsp";
//            getServletConfig().getServletContext()
//                    .getRequestDispatcher("/WEB-INF/jsp/"+laVue)
//                    .forward(httpServletRequest, httpServletResponse);
//
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }
//}
