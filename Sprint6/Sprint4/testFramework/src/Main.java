package main;
import  etu1836.framework.Utilitaire;
import java.util.ArrayList;
import java.util.HashMap;
import java.lang.annotation.*;
import etu1836.framework.Mapping;
import java.lang.reflect.Field;
import javax.print.attribute.standard.Media;
import annotation.*;
public class Main{
    public static void main(String[] args) {
       
        try {
            Utilitaire u= new Utilitaire();
           // ArrayList<String> classes= u.get_AllClass("etu1836/framework/modele");
            HashMap<String,Mapping> mappingUrls = new HashMap<>();
            mappingUrls= u.getHashmap( mappingUrls,  "D:/L2/S3/Reseaux/apache-tomcat-8/webapps/Script1/WEB-INF/classes/etu1836/framework/modele");
            System.out.println(mappingUrls);
            u.printHM(mappingUrls);

            
        } catch (Exception e) {
            e.printStackTrace();
            // TODO: handle exception
        }
       
    }
}