package etu1836.framework.modele;
import etu1836.framework.annotation.*;
import java.lang.annotation.*;
import java.time.LocalDateTime;
import etu1836.framework.ModeleView;
@model(table="departement")
public class Dept{
    int id;
    String libele;
    
    public Dept() {
    }
    public Dept(int id, String libele) {
        this.id = id;
        this.libele = libele;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getLibele() {
        return libele;
    }
    public void setLibele(String libele) {
        this.libele = libele;
    }
   
    @Methods("listDept") 
    public ModeleView  get_Deptlist(){
        System.out.println("Depart 1");
        System.out.println("Depart 2");
        ModeleView v= new ModeleView();
        v.setView("listdept.jsp");
        return v;

    }


}