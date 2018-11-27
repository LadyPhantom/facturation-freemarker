package fr.laerce.facturation;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public class TestConnexionFilter implements Filter {

    public  void init(FilterConfig filterConfig) {
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest httpRequest = (HttpServletRequest)servletRequest ;

        if( httpRequest.getParameter("connexion")==null ){
//            System.out.println("Deco de la BdD");
            RequestDispatcher requestDispatcher =
                    httpRequest.getRequestDispatcher(httpRequest.getContextPath()+"/jsp/ErreurConnexion.jsp") ;
            requestDispatcher.forward(httpRequest, servletResponse) ;
        }else{
            filterChain.doFilter(servletRequest, servletResponse) ;
        }
    }

    public  void destroy() {
    }
}
