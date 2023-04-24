package etu1836.framework;
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.*;
import java.sql.SQLException;
import java.io.*;
import etu1836.framework.annotation.*;
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
            System.out.println(pack);
           
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
                //System.out.println(classename.get(i));
                Method[] methods=Class.forName(classename.get(i)).getDeclaredMethods();
                for (int j = 0; j < methods.length; j++) {
                   if(methods[j].getAnnotation(Methods.class) != null){
                        //System.out.println(methods[j].getName());
                        //System.out.println(methods[j].getAnnotation(Methods.class).value());
                            Mapping map= new Mapping();
                            String clas= classename.get(i);
                            //System.out.println(clas);
                            int ind =0;
                            char[] charclass = clas.toCharArray();
                            for (int k = 0; k < charclass.length; k++) {
                                if(charclass[k] == '.'){
                                    ind =k;
                                }
                            }
                            String nom_classe = "";
                            for (int k = ind + 1; k < charclass.length; k++) {
                                nom_classe =nom_classe + charclass[k];
                            }

                            //System.out.println(ind);
                            System.out.println(nom_classe);

                            // System.out.println(classename.get(i));
                            // //System.out.println(nom_classe);
                            //System.out.println(nom_classe[nom_classe.length-1]);
                           
                            map.setClassName(classename.get(i));
                            map.setMethod(methods[j].getName());
                           
                           // System.out.println(map.getClassName());
                            //System.out.println(map.getMethod());
                            mappingUrls.put(methods[j].getAnnotation(Methods.class).value(), map);
                            System.out.println(mappingUrls);


                       
                  }
                }
            }  
        }
        return mappingUrls;
     }


     public Mapping  geMap(HashMap<String,Mapping> mappingUrls,String cle) throws Exception
    {
        Mapping map = new Mapping();
        for( String key : mappingUrls.keySet()){
            if(key.compareToIgnoreCase(cle)==0){
              map  = mappingUrls.get(key);
            }
           // System.out.println(key +"|" + value.getClassName() + " | " + value.getMethod());
        }
        return map;
     }



     public void printHM(HashMap<String,Mapping> mappingUrls)
     {
        for( String key : mappingUrls.keySet()){
            
            Mapping value = mappingUrls.get(key);

            //System.out.println(key +"|" + value.getClassName() + " | " + value.getMethod());

        }

     }

     public ModeleView get_View(String cle, HashMap<String,Mapping> mappingUrls) throws Exception
     {
        Mapping map = this.geMap(mappingUrls,cle);
        String nom_class = map.getClassName();
        String methode = map.getMethod();
        Object obj= new Object();
        Class<?> myClass=Class.forName(nom_class);
        obj = myClass.newInstance();
        Method[] methods=Class.forName(nom_class).getDeclaredMethods();
        ModeleView mv= new ModeleView();
       // ModeleView mv= new ModeleView();
        // for (int j = 0; j < methods.length; j++) {
        //     if(methods[j].getName().compareToIgnoreCase(methode)==0){
        //        mv=(ModeleView)Class.forName(nom_class).getDeclaredMethods()[j].invoke(obj);
        //     }
        // }
        mv = (ModeleView)myClass.getDeclaredMethod(methode).invoke(obj);
       
        //valueflds[i]=String.valueOf( lstobj[i].getClass().getMethod( to_getAttribu( namefield ) ).invoke(lstobj[i]) );//invoker l'attribu et le caster en String
        return mv;
     }




}