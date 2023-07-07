package etu1836.framework;
public class Mapping {
    String className;
    String Method;
    public Mapping(String className, String method) {
        this.className = className;
        Method = method;
    }
    public String getClassName() {
        return className;
    }
    public String getMethod() {
        return Method;
    }
    public void setClassName(String className) {
        this.className = className;
    }
    public void setMethod(String method) {
        Method = method;
    }
}