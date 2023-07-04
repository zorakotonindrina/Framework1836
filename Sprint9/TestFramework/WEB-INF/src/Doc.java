package etu1836.framework.modele;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.*;
import etu1836.framework.annotation.*;
import etu1836.framework.ModeleView;
public class Doc{
    String Nom;
    UploadFile Pdp;
    public String getNom() {
        return Nom;
    }
    public void setNom(String nom) {
        Nom = nom;
    }
    public UploadFile getPdp() {
        return Pdp;
    }
    public void setPdp(UploadFile pdp) {
        Pdp = pdp;
    }
    public Doc(){}

    @Methods("saveFile")
    public void save(){
        System.out.println("Voantso ilay file");
    }
}