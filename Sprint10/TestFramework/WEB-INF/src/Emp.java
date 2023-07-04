package etu1836.framework.modele;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.*;
import etu1836.framework.annotation.*;
import etu1836.framework.ModeleView;
import etu1836.framework.ModeleView;
@model(table="emp")
@Scope("singleton")
public class Emp{
    int empno;
    public Emp(int empno, int deptno, String name, int salaire, String job) {
        this.empno = empno;
        this.deptno = deptno;
        this.name = name;
        this.salaire = salaire;
        this.job = job;
    }
    public int getEmpno() {
        return empno;
    }
    public void setEmpno(int empno) {
        this.empno = empno;
    }

    int deptno;
    String name;
    int salaire;
    String job;
    public Emp(){ 
        System.out.println("New instance Emp");
    }
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
       // System.out.println("Nom : Rakoto");
        //System.out.println("Nom : Rabe");
        List<Emp> listemp = null;
        listemp.add(new Emp(1, 1, "Jean", 50000, "comptable"));
        listemp.add(new Emp(2, 1, "Marc", 40000, "Agent"));
        listemp.add(new Emp(3, 2, "Luc", 60000, "Manager"));
        listemp.add(new Emp(4, 2, "jeanne", 30000, "Agent"));
        ModeleView v= new ModeleView();
        v.setView("listemp.jsp");
        v.addItem("listemp", listemp);
        System.out.println(v.getView());
        return v;

    }

    @Methods(value = "VoirEmp")
    public ModeleView FindById(int empno){
        System.out.println("tonga method");
        // System.out.println("Nom : Rakoto");
         //System.out.println("Nom : Rabe");
         ArrayList<Emp> listemp = new ArrayList<Emp>();
         listemp.add(new Emp(1, 1, "Jean", 50000, "comptable"));
         listemp.add(new Emp(2, 1, "Marc", 40000, "Agent"));
         listemp.add(new Emp(3, 2, "Luc", 60000, "Manager"));
         listemp.add(new Emp(4, 2, "jeanne", 30000, "Agent"));
         ModeleView v= new ModeleView();
         v.setView("emp.jsp");
         Emp emp = new Emp();
         emp =  listemp.get(empno-1);
         v.addItem("emp",emp);
         System.out.println(v.getView());
         return v;
     }

    @Methods("newEmp") 
    public ModeleView  Add_Emp(){
        ModeleView v= new ModeleView();
        v.setView("newEmp.jsp");
        System.out.println(v.getView());
        return v;

    }


    public void Save(){
        System.out.println("Deptno :" +  this.getDeptno());
        System.out.println("Name :"  + this.getName());
        System.out.println("Job :"  + this.getJob());
        System.out.println("Salaire :"  + this.getSalaire());   
    }


    


    
}