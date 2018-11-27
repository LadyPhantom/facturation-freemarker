package fr.laerce.facturation;

import fr.laerce.facturation.model.Client;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

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
    Template listeClients;

    @Override
    protected void doGet(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws ServletException, IOException {

        try {

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

            Map<String,Object> datas = new HashMap<>();
            datas.put("clients", clients);

            listeClients.process(datas, httpServletResponse.getWriter());

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (TemplateException e){
            e.printStackTrace();
        }
    }

    @Override
    public void init() throws ServletException {
        super.init();

//        Configuration cfg = new Configuration(Configuration.VERSION_2_3_25);
//        cfg.setServletContextForTemplateLoading(getServletContext(),"/WEB-INF/templates");
//        cfg.setDefaultEncoding("UTF8");
//        try {
//            listeClients = cfg.getTemplate("clients.ftl");
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
        listeClients = (Template) getServletContext().getAttribute("templateListe");
    }

}
