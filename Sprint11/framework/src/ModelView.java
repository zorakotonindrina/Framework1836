package modelview;
import java.util.HashMap;
public class ModelView {
    String view;
    HashMap<String,Object> data;
    HashMap<String,Object> session; 
    public void addSession(String key, Object value) {
        this.session.put(key, value);
    }
    public String getView() {
        return view;
    }
    public void setView(String view) {
        this.view = view;
    }
    public ModelView() {
        this.data = new HashMap<String,Object>();
        this.session = new HashMap<String,Object>();
    }
    public ModelView(String view) {
        this.setView(view);
    }
    public HashMap<String, Object> getData() {
        return data;
    }
    public void setData(HashMap<String, Object> data) {
        this.data = data;
    }
    public void addItem(String key,Object valeur) {
        this.data.put(key,valeur);
    }
    public ModelView(String view, HashMap<String, Object> data) {
        this.session = new HashMap<String,Object>();
        this.view = view;
        this.data = data;
    }
    public ModelView(String view, HashMap<String, Object> data, HashMap<String, Object> session) {
        this.view = view;
        this.data = data;
        this.session = session;
    }
    public HashMap<String, Object> getSession() {
        return session;
    }
    public void setSession(HashMap<String, Object> session) {
        this.session = session;
    }
}