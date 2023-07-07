package etu1836.framework.servlet;
import utils.Utilitaire;
import java.io.*;
import javax.servlet.*;
import javax.servlet.ServletContext;
import javax.servlet.http.*;
import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.Date;
import etu1836.framework.*;
import fileUpload.FileUpload;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Vector;
import java.util.Iterator;
import java.util.Map;
import java.util.Objects;
import modelview.ModelView;
import note.Fonction;
import note.Singleton;
import note.Auth;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.net.URI;
//........2Mai
import java.util.Enumeration;
import java.lang.reflect.Parameter;
import javax.servlet.annotation.MultipartConfig;

@MultipartConfig(
        fileSizeThreshold = 1024 * 1024, // 1MB
        maxFileSize = 1024 * 1024 * 5, // 5MB
        maxRequestSize = 1024 * 1024 * 10 // 10MB
)
public class FrontServlet extends HttpServlet {
    HashMap<String,Mapping> MappingUrls;
    HashMap<Class,Object> listSingleton;
    public void init()throws ServletException{
        ServletContext context = this.getServletContext();
        //...traitement
        Utilitaire use = new Utilitaire();
        Mapping mapping = null;
        String nom = "null";
        ArrayList<Class<?>> params = new ArrayList<>();
        try {
                //Avoir url
            ClassLoader loader = context.getClassLoader();
            URI uri = Objects.requireNonNull(loader.getResource("")).toURI();
            File f = new File(uri);
            String classPath = f.getPath();
            // out.print(classPath); 
            // out.print("voici le path");
            String lien = classPath;
            ArrayList<Class<?>> cl = use.getAll_Classe(lien,params,lien);
            HashMap<String,Mapping> huhu = new  HashMap<String,Mapping>();
            HashMap<Class,Object> hoho = new  HashMap<Class,Object>();
            Annotation fonction = null;
            Annotation singleton = null;
            for (int i = 0; i < cl.size(); i++) {
                for (int j = 0; j < cl.get(i).getDeclaredMethods().length; j++) {
                    if(cl.get(i).getDeclaredMethods()[j].isAnnotationPresent(Fonction.class)){
                            fonction = cl.get(i).getDeclaredMethods()[j].getAnnotation(Fonction.class);
                            mapping = new Mapping(cl.get(i).getName(),cl.get(i).getDeclaredMethods()[j].getName());
                            nom = cl.get(i).getDeclaredMethods()[j].getAnnotation(Fonction.class).getClass().getMethod("nomMethod").invoke(fonction).toString();
                            huhu.put(nom,mapping);
                    }
                    //Pour checker les classes singleton 
                }
                if (cl.get(i).isAnnotationPresent(Singleton.class)){
                        singleton = cl.get(i).getAnnotation(Singleton.class);
                        System.out.println("MIsy present"+cl.get(i).getName());
                        Class laClasse = cl.get(i);
                        hoho.put(laClasse,null);
                }
            }
        this.listSingleton = hoho;
        this.MappingUrls = huhu;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public boolean checkSingletonClass( Class className ){
        if(this.listSingleton.containsKey(className) == true){
            return true;
        }
        return false;
    }
    protected void processRequest(HttpServletRequest req, HttpServletResponse res)throws ServletException, IOException {
        PrintWriter out = res.getWriter();
        Utilitaire use = new Utilitaire();
        HttpSession session = req.getSession();
        String composant = use.getData(req.getRequestURL().toString());
        out.println("HUELOOOO");
        out.print(composant);
        try {
            Object objet = null;
            use.verif(composant,this.MappingUrls);
            Mapping test = null;
            for (Map.Entry mapentry : this.MappingUrls.entrySet()) {
                out.print("\nLa cle est : "+mapentry.getKey());
                if(mapentry.getKey().toString().compareToIgnoreCase(composant) == 0){
                    test = (Mapping)mapentry.getValue();
                }
            }
            out.print("\n"+"La Classe est :"+test.getClassName());
            Class laclasse = Class.forName(test.getClassName());
            // verification si la classe est un singleton
            if(this.checkSingletonClass(laclasse) == true){
                System.out.println("--------------------------------");
                System.out.println("--------------------------------");
                if(this.listSingleton.get(laclasse) != null){
                    System.out.println("---------Ancien instance Singleton de "+laclasse.getName()+"---------");

                    objet = this.listSingleton.get(laclasse);
                } else {
                    System.out.println("---------Nouveau instance Singleton de "+laclasse.getName()+"---------");
                    objet = laclasse.getConstructor().newInstance();
                    this.listSingleton.put(laclasse, objet);
                }
            } else {
                System.out.println("---------Nouveau instance sans Singleton de "+laclasse.getName()+" ---------");
                objet = laclasse.getConstructor().newInstance();
            }
            out.print("debut du traitement");

            //Verifier si il ya des variables 
            Field[] les_attributs = laclasse.getDeclaredFields();
            //out.println("HUELOOOO");
            Class[] parametre = new Class[1];
            parametre[0] = String.class;
            for (int i = 0; i < les_attributs.length; i++) {
                out.println(les_attributs[i].getName() + " Nom des attributs");
                out.println(req.getParameter(les_attributs[i].getName())+"lA VALEUR");
                
                if (req.getParameter(les_attributs[i].getName())!= null) {
                    parametre[0] = les_attributs[i].getType();
                    if (les_attributs[i].getType().getSimpleName().compareToIgnoreCase("String")==0) {
                        out.print(les_attributs[i].getType().getSimpleName());
                        objet.getClass().getDeclaredMethod("set"+les_attributs[i].getName(),parametre).invoke(objet,req.getParameter(les_attributs[i].getName()));
                    }
                    else if(les_attributs[i].getType().getSimpleName().compareToIgnoreCase("double")==0){
                        out.print(les_attributs[i].getType().getSimpleName());
                        objet.getClass().getDeclaredMethod("set"+les_attributs[i].getName(),parametre).invoke(objet,Double.parseDouble(req.getParameter(les_attributs[i].getName())));
                    }
                    else if(les_attributs[i].getType().getSimpleName().compareToIgnoreCase("float")==0){
                        out.print(les_attributs[i].getType().getSimpleName());
                        objet.getClass().getDeclaredMethod("set"+les_attributs[i].getName(),parametre).invoke(objet,Float.parseFloat(req.getParameter(les_attributs[i].getName())));
                    }
                    else if(les_attributs[i].getType().getSimpleName().compareToIgnoreCase("int")==0){
                        out.print(les_attributs[i].getType().getSimpleName());
                        objet.getClass().getDeclaredMethod("set"+les_attributs[i].getName(),parametre).invoke(objet,Integer.parseInt(req.getParameter(les_attributs[i].getName())));
                    }
                    else if(les_attributs[i].getType().getSimpleName().compareToIgnoreCase("date")==0){
                        out.print(les_attributs[i].getType().getSimpleName());
                        objet.getClass().getDeclaredMethod("set"+les_attributs[i].getName(),parametre).invoke(objet,Date.valueOf(req.getParameter(les_attributs[i].getName())));
                    }
                    out.println("Okey le mamorina anle objet a partir le formulaire");
                }
                else{
                        String reqContent = req.getContentType();
                        if(reqContent != null && reqContent.startsWith("multipart")){
                            parametre[0] = les_attributs[i].getType();
                            out.print("Tafiditra ato @ le upload");
                            Part toupload = req.getPart(les_attributs[i].getName());
                            out.print("Okey lese");
                            FileUpload fupld = new FileUpload();
                            fupld.setNom(toupload.getSubmittedFileName());
                            fupld.setData(toupload.getInputStream().readAllBytes());
                     
                            Method theMethod = objet.getClass().getDeclaredMethod("set"+les_attributs[i].getName(),parametre);
                            theMethod.invoke(objet,fupld);
                        }
                        // out.print(les_attributs[i].getType().getSimpleName());
                        // objet.getClass().getDeclaredMethod("set"+les_attributs[i].getName(),parametre).invoke(objet,Date.valueOf(req.getParameter(les_attributs[i].getName())));
                     out.println("Misy file Upload ao \n");
                }
            }
            //..........SPRINT 8
            out.print("Voici l'url "+req.getRequestURL().toString());
            //Url recent 
            out.print("Voici l'url "+req.getQueryString());


            //Tokony ao anaty utilitaire :
            boolean isArgsMitovy = false;
            Method[] function = laclasse.getDeclaredMethods();
            Method theMeth = null;
            for (int i = 0; i < function.length; i++) {
                if (function[i].getName().compareToIgnoreCase(test.getMethod()) == 0) {
                    theMeth = function[i];
                }
            }
            Parameter[] parameters = theMeth.getParameters();
            out.print("io le izy teo");
            out.print(req.getParameter("id"));
            Vector params = new Vector<>();
            for (int i = 0; i < parameters.length; i++) {
                params.add(req.getParameter(Utilitaire.getNomParametreAnnote(parameters[i])));
            }
            Object[] tabParametreString = params.toArray();
            Object[] parametreFonction = new Object[tabParametreString.length];
            int temp = 0;
            for (Parameter p : parameters) {
                System.out.println("\n"+p.getType());
                Class targetClass = p.getType();
                System.out.println(targetClass.getSimpleName());
                if (targetClass.getSimpleName().compareToIgnoreCase("String") == 0){
                    parametreFonction[temp] = String.valueOf(tabParametreString[temp]);
                    out.print("Nivadika String");
                }
                else if (targetClass.getSimpleName().compareToIgnoreCase("int") == 0){
                    parametreFonction[temp] = Integer.parseInt(String.valueOf(tabParametreString[temp]));
                    out.print("Nivadika Int");
                }
                else if (targetClass.getSimpleName().compareToIgnoreCase("double") == 0) {
                    parametreFonction[temp] = Double.parseDouble(String.valueOf(tabParametreString[temp]));
                }
                isArgsMitovy = true ;
                temp++;
            }
            out.print(theMeth.getParameterTypes());
            out.print(theMeth.getName() + " le nom du method attendue");
                        
            Object Objetview = null;
            if(isArgsMitovy == true){
                Objetview =  theMeth.invoke(objet,parametreFonction);
            }else{
                Objetview =  theMeth.invoke(objet);
                System.out.println(objet.getClass().getName());
                System.out.println(objet);
            }
            
            out.print("Tonga ato");
            ModelView view = (ModelView)Objetview;
            String profilEnSession = "Session";
            String profilVaoTonga = "NSession";
            boolean connecte = false;
            String profilMethode = "kokoo";
            //Verif de l'annotation Auth
            if (theMeth.isAnnotationPresent(Auth.class)) {
                System.out.println("L'annotation AUTH est present");
                profilEnSession = String.valueOf(session.getAttribute("profil")) ;
                connecte = (boolean)session.getAttribute("isConnected");
                System.out.println("voice le prof sess "+profilEnSession);
                Auth aut = theMeth.getAnnotation(Auth.class);
                profilMethode = aut.profil();
                System.out.println("voici le profil du methode"+profilMethode);
                if (profilEnSession.compareToIgnoreCase(profilMethode)!=0) {
                    throw new Exception("Profil "+profilEnSession+" requis!!!!");
                }
                // if (view.getSession() != null) {
                //     for (Map.Entry mapentry : view.getSession().entrySet()) {
                //         if (mapentry.getKey().toString().compareToIgnoreCase("profil") == 0) {
                //             profilVaoTonga = String.valueOf(mapentry.getValue()) ;
                //         }
                //         // if (mapentry.getKey().toString().compareToIgnoreCase("isConnected") == 0) {
                //         //     connecte = (boolean)mapentry.getValue();
                //         // }
                //     }
                // }
                if (profilEnSession.compareToIgnoreCase(profilMethode) == 0 && connecte == true) {
                        if (view.getSession() != null) {
                            for (Map.Entry mapentry : view.getSession().entrySet()) {
                                System.out.println("LE VOILA\n");
                                req.setAttribute(mapentry.getKey().toString(),mapentry.getValue());
                                System.out.print(mapentry.getKey().toString()+"\n");
                                session.setAttribute(mapentry.getKey().toString(),mapentry.getValue());
                        }
                    }
                }else{
                    throw new Exception("Profil "+profilEnSession+" requis!!!!");
                }
            }else{
                System.out.println("Tonga ato @ le else");
                if (view.getSession() != null) {
                    for (Map.Entry mapentry : view.getSession().entrySet()) {
                        System.out.println("LEsasasa VOILA\n");
                        System.out.print(mapentry.getKey().toString()+"\n");
                        session.setAttribute(mapentry.getKey().toString(),mapentry.getValue());
                    }
                }
            }
            //...................
            if (view.getData() != null) {
                for (Map.Entry mapentry : view.getData().entrySet()) {
                    req.setAttribute(mapentry.getKey().toString(),mapentry.getValue());
                }
            }
            

            // .... On a deja l'objet et comment le rediriger ? dans quel view?
            // ServletContext context = this.getServletContext();
            // context.setAttribute("Objet",objet);

            RequestDispatcher dispat = req.getRequestDispatcher(view.getView()); 
            dispat.forward(req,res);

        } catch (Exception e) {
            e.printStackTrace();
            out.print(e.getMessage());
        }
        
    }
    protected void doGet(HttpServletRequest req, HttpServletResponse res)throws ServletException, IOException {
        processRequest(req, res);
    }
    protected void doPost(HttpServletRequest req, HttpServletResponse res)throws ServletException, IOException {
        processRequest(req, res);
    }
}
