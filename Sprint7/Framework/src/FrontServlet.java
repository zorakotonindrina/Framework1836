package etu1836.framework.servlet;
import java.sql.SQLException;
import java.io.*;
import java.lang.reflect.Field;

import javax.servlet.*;
import javax.servlet.http.*;
import java.util.*;
import etu1836.framework.*;
import etu1836.framework.Utilitaire;
import etu1836.framework.Mapping;
import java.sql.Date;
public class FrontServlet extends HttpServlet 
{
    HashMap<String,Mapping> mappingUrls = new HashMap<>();
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
                try {
                    response.setContentType("text/plain");
                    PrintWriter out = response.getWriter();
                    Utilitaire u = new Utilitaire();
                    ServletContext context= getServletContext();
                    String path= u.getPath(request); //Premiere methode
                    System.out.println(path);
                    String meth = u.getMethode(request); // Deuxieme methode  
                    System.out.println(meth);
                    out.println(meth);   
                    String rep = u.Save(request, context);
                    
                } catch (Exception e) {
                    e.printStackTrace();
                    // TODO: handle exception
                }
               

    }


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    
        processRequest(request,response);
        
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        

    }
}
