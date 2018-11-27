package fr.laerce.facturation;

import freemarker.template.Configuration;
import freemarker.template.Template;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.io.IOException;
import java.sql.*;

public class ContextListener implements ServletContextListener {//*
    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {//*

        // ------- Initialisation BdD -----------------------------------------------

        ServletContext sc = servletContextEvent.getServletContext();//*

        String url = sc.getInitParameter("DB_url");
        String user_name = sc.getInitParameter("id_user");
        String password = sc.getInitParameter("psw_user");
        String database = sc.getInitParameter("DB_name");

        try {
            Class.forName( sc.getInitParameter("nom_driver") );

            Connection conn = DriverManager.getConnection(url+database, user_name, password);
            Statement sta = conn.createStatement();

            sc.setAttribute("connexion",conn);
            sc.setAttribute("statement",sta);



            // --- Prepared Statement -------------------------------

            String listeDeToutLesClients = "SELECT clt_num, clt_nom, clt_pnom, clt_loc, clt_pays FROM clients;";
            String detailClient = "SELECT clt_num, clt_nom, clt_pnom, clt_loc, clt_pays FROM clients WHERE clt_num = ?;";

            PreparedStatement prepState_listeDeToutLesClients = conn.prepareStatement(listeDeToutLesClients);
            PreparedStatement prepState_detailClient = conn.prepareStatement(detailClient);

            sc.setAttribute("PS_listeAll",prepState_listeDeToutLesClients);
            sc.setAttribute("PS_detail",prepState_detailClient);

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        // ------- Initialisation Templates -------------------------------------------

        Configuration cfg = new Configuration(Configuration.VERSION_2_3_25);
        cfg.setServletContextForTemplateLoading(sc ,"/WEB-INF/templates");
        cfg.setDefaultEncoding("UTF-8");

        Template detailsClient, listeClients;
        try {
            detailsClient = cfg.getTemplate("detail.ftl");
            listeClients = cfg.getTemplate("clients.ftl");

            sc.setAttribute("templateDetail", detailsClient);
            sc.setAttribute("templateListe", listeClients);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        ServletContext sc = servletContextEvent.getServletContext();
        Connection conn = (Connection) sc.getAttribute("connexion");
        Statement stm = (Statement) sc.getAttribute("statement");

        try{
            stm.close();
            conn.close();
        }catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
