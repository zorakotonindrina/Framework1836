package etu1836.framework;
import javax.servlet.*;
import javax.servlet.http.*;
import etu1836.framework.Mapping;
import etu1836.framework.modele.*;
import java.util.*;
import java.sql.SQLException;
import java.io.*;
import etu1836.framework.Mapping;

import annotation.*;
import java.lang.reflect.Method;
import java.nio.file.Path;
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

    public ArrayList<String> get_AllClass(String pack)throws Exception
    {
            File folder=new File(pack);
            if(folder.exists()==false){
                  throw new Exception(pack+" n'existe pas");
            }
            File [] tables=folder.listFiles();
            pack = pack.replace("\\", "/");
            String[] tabstr= pack.split("/");
            int ind=0;
            for (int i = 0; i < tabstr.length; i++) {
               // System.out.println(tabstr[i]);
                if(tabstr[i].compareToIgnoreCase("classes")==0 ){
                    ind = i;
                }
            }
            String path = "";

            for (int i = ind + 1; i < tabstr.length; i++) {
                path = path + tabstr[i]+".";
            };
            //System.out.println(path);
            if(tables==null){ return null;}
            else if(tables.length<1){   return null;  }
            ArrayList<String> vTables=new ArrayList<>();
            for(int i=0;i<tables.length;i++){
                  if(tables[i].length()>=5){
                        ///nom.class
                        if(tables[i].getName().substring(tables[i].getName().length()-6 , tables[i].getName().length()).compareToIgnoreCase(".class")==0){
                            System.out.println(tables[i].getName().substring(0, tables[i].getName().length()-6)); 
                           
                            vTables.add(path+tables[i].getName().substring(0, tables[i].getName().length()-6) );
                        }
                  }
            }
           
            return vTables;
    }



    public HashMap<String,Mapping>  getHashmap(HashMap<String,Mapping> mappingUrls,ServletContext context) throws Exception
    {
     
       
        String path = context.getRealPath( "/WEB-INF/classes/etu1836/framework/modele");
        ArrayList <String> classename= this.get_AllClass(path);
        
        for (int i = 0; i < classename.size(); i++) {
           // Class<?> Class.forName(classename.get(i)) ;
            if(Class.forName(classename.get(i))!= null){
                //Class.forName(classename.get(i)) = Class.forName(classename.get(i)); 
                System.out.println(classename.get(i));
                Method[] methods=Class.forName(classename.get(i)).getDeclaredMethods();
                for (int j = 0; j < methods.length; j++) {
                   if(methods[j].getAnnotation(Methods.class) != null){
                        //System.out.println(methods[j].getName());
                        //System.out.println(methods[j].getAnnotation(Methods.class).value());
                            Mapping map= new Mapping();
                            String clas= classename.get(i);
                            String[] nom_classe=clas.split(".");
                            // System.out.println(classename.get(i));
                            // //System.out.println(nom_classe);
                            //System.out.println(nom_classe[nom_classe.length-1]);
                            map.setClassName(classename.get(i));
                            map.setMethod(methods[j].getName());
                            System.out.println(map.getClassName());
                            System.out.println(map.getMethod());
                            mappingUrls.put(methods[j].getAnnotation(Methods.class).value(), map);
                            System.out.println(mappingUrls);


                       
                  }
                }
            }  
        }
        return mappingUrls;
     }



     public void printHM(HashMap<String,Mapping> mappingUrls)
     {
        for( String key : mappingUrls.keySet()){
            Mapping value = mappingUrls.get(key);
            System.out.println(key +"|" + value.getClassName() + " | " + value.getMethod());

        }

     }
}