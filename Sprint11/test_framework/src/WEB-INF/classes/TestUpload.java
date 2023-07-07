package test;
import modelview.*;
import fileUpload.FileUpload;
import note.Fonction;
import note.Singleton;
@Singleton()
public class TestUpload{
    String Nom;
    FileUpload Pdp;
    public String getNom() {
        return Nom;
    }
    public void setNom(String nom) {
        Nom = nom;
    }
    public FileUpload getPdp() {
        return Pdp;
    }
    public void setPdp(FileUpload pdp) {
        Pdp = pdp;
    }
    public TestUpload(){}
    @Fonction(nomMethod = "saveFile")
    public void save(){
        System.out.println("Voantso ilay file");
    }
}