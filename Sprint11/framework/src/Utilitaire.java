package utils;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Path;
import java.util.List;
import java.io.File;
import java.lang.annotation.Annotation;
import java.lang.reflect.Parameter;
import java.util.Vector;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Path;
import java.util.List;
import java.io.File;
import java.lang.annotation.Annotation;

import etu1836.framework.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.lang.reflect.*;
import note.*;
public class Utilitaire {

    public static String getNomParametreAnnote(Parameter param) throws Exception {
        String annotation = "note.Parametre";
        Class annotationClass = Class.forName(annotation);
        if(param.isAnnotationPresent(annotationClass)){
            Parametre paramName = (Parametre) param.getAnnotation(annotationClass);
            //Retourne le nom du parametre annnote
            return paramName.nomParametre();
        } else {
            throw new Exception("Annote par: @Parameter les parametres de la fonction");
        }
    }
    public void verif(String element,HashMap<String,Mapping> MappingUrls)throws Exception{
        Mapping test = null;
        int repere = 0;
        for (Map.Entry mapentry : MappingUrls.entrySet()){
            test = (Mapping)mapentry.getValue();
            if(element.compareToIgnoreCase(mapentry.getKey().toString()) == 0){
                repere++;
            }
        }
        if(repere == 0){
            throw new Exception("Le lien est introuvable");
        }
    }
    public String getData(String requete) {
        System.out.println(requete);
        String[] sans_slash = requete.split("/");
        Vector v = new Vector<>();
        int count = 0;
        for (int i = 4; i < sans_slash.length; i++) {
            v.add(sans_slash[i]);
        }
        Object[] value = v.toArray();
        String[] answer = new String[value.length];
        for (int i = 0; i < answer.length; i++) {
            answer[i] = (String)value[i];
            System.out.println("annswer "+answer[i]);
        }
        if (answer.length == 0) {
            return null;
        }
        return answer[0];
    }

    public static ArrayList<String> findAllFilesInFolder(File folder) {
		ArrayList<String> list= new ArrayList<>();
        for (File file : folder.listFiles()) {
			if (!file.isDirectory()) {
                String filen =  file.getName();
                String[] filename = filen.split(".class"); 
                for (int i = 0; i < filename.length; i++) {                  
                    list.add(filename[i]);  
                }
			} else {
				findAllFilesInFolder(file);
			}
		}
        return list;
	}   
    public boolean verifDirectory(String path){
        try {
            File dir  = new File(path);
            File[] liste = dir.listFiles();
            int count = 0;
            for(File item : liste){
                if(item.isDirectory()){
                    count++;
                }
            }
            if (count==0) {
                return false;
            }
            if (count!=0) {
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    public ArrayList<Class<?>> getAll_Classe(String lien , ArrayList<Class<?>> cl,String origine) {
        String path = "oulala";
        File file = null;
        ArrayList<String> list = null;
        try {
            File dir  = new File(lien);
            File[] liste = dir.listFiles();
            String k = "null";
            String fold = "null";
            for(File item : liste){
                if(item.isDirectory()){
                    System.out.format("Nom du répertoir: %s%n", item.getName()); 
                    path = lien+"\\"+item.getName();
                    file = new File(path); 
                    list = findAllFilesInFolder(file);
                    k = path.replace(origine, "");
                    fold = k.replace("\\", ".").substring(1);
                    for (int i = 0; i < list.size(); i++) {
                        cl.add(Class.forName(fold+"."+list.get(i)));
                    }
                    if (this.verifDirectory(path) == true) {
                        this.getAll_Classe(path,cl,origine);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return cl;
    }
    
}

