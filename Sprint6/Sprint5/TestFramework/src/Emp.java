package etu1836.framework.modele;
import java.util.ArrayList;
import etu1836.framework.annotation.*;
import etu1836.framework.ModeleView;
import etu1836.framework.ModeleView;
public class Emp{
    int deptno;
    String name;
    int salaire;
    String job;
    public Emp(){ }
    public Emp(int deptno, String name, int salaire, String job) {
        this.deptno = deptno;
        this.name = name;
        this.salaire = salaire;
        this.job = job;
    }
    public int getDeptno() {
        return deptno;
    }
    public void setDeptno(int deptno) {
        this.deptno = deptno;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public int getSalaire() {
        return salaire;
    }
    public void setSalaire(int salaire) {
        this.salaire = salaire;
    }
    public String getJob() {
        return job;
    }
    public void setJob(String job) {
        this.job = job;
    }

    @Methods("listEmp") 
    public ModeleView  get_Emplist(){
        System.out.println("Nom : Rakoto");
        System.out.println("Nom : Rabe");
        ModeleView v= new ModeleView();
        v.setView("listemp.jsp");
        return v;

    }


    


    
}