package etu1836.framework.servlet;
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.*;
import java.sql.SQLException;
import java.io.*;
public class Utilitaire{

    public String getMethode(HttpServletRequest request) throws ServletException
    {
        String url = request.getRequestURI();
        String[] urls= url.split("/");
        String method = urls[urls.length-1];
        return method;
    }

    public String getPath(HttpServletRequest request) throws ServletException
    {
        return request.getServletPath();
    }


}