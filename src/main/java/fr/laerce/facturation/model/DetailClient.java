package fr.laerce.facturation.model;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.*;
import java.util.HashMap;
import java.util.Map;

public class DetailClient extends HttpServlet {
    Template detailsClient;

    protected void doGet(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws ServletException, IOException{

        String id = httpServletRequest.getParameter("id");

        try {
            PreparedStatement prep = (PreparedStatement) getServletContext().getAttribute("PS_detail");
            prep.setString(1, id);
            ResultSet res = prep.executeQuery();

            Client client = null;
            while(res.next()){
                client = new Client(res.getString("clt_num"),
                        res.getString("clt_nom"),
                        res.getString("clt_pnom"),
                        res.getString("clt_loc"),
                        res.getString("clt_pays"));

            }

            Map<String,Object> datas = new HashMap<>();
            datas.put("client", client);

            detailsClient.process(datas, httpServletResponse.getWriter());

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
//            detailsClient = cfg.getTemplate("detail.ftl");
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
        detailsClient = (Template) getServletContext().getAttribute("templateDetail");
    }
}
