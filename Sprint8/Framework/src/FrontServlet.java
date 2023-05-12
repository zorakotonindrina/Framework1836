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
                    mappingUrls= u.getHashmap( mappingUrls, context);
                    u.printHM(mappingUrls);
                    Mapping map = u.geMap(mappingUrls, meth);
                    out.println( "Key : "+meth );
                    out.println( map.getClassName() + " | " + map.getMethod());
                    ModeleView view= u.get_View(meth, mappingUrls, request);
                    out.println( "Page : "+ view.getView() );
                    HashMap<String,Object> mydata = new HashMap<>();
                    if(view.getData() instanceof  HashMap<String,Object>){
                        if( view.getData() != null){
                            mydata = view.getData();
                            for( String key : mydata.keySet()){
                                Object value = mydata.get(key);
                                request.setAttribute(key, value);
                            }
                        }
                       
                    }
                    String page = view.getView();
                    RequestDispatcher disp = request.getRequestDispatcher(page);
                    
                    disp.forward(request, response);
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
