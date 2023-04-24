package etu1836.framework;
import java.util.*;
public class ModeleView {
    String view;
    HashMap<String,Object> data;
    
    public ModeleView(String view, HashMap<String, Object> data) {
        this.view = view;
        this.data = data;
    }

    public ModeleView() {
    }

    public ModeleView(String view) {
        this.view = view;
    }

    public String getView() {
        return view;
    }

    public void setView(String view) {
        this.view = view;
    }

    public HashMap<String, Object> getData() {
        return data;
    }

    public void setData(HashMap<String, Object> data) {
        this.data = data;
    }

    public void addItem(String cle, Object valeur){
        data.put(cle, valeur);
    }

}
