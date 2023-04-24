package etu1836.framework.annotation;
import java.lang.annotation.*;
@Retention (RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface Methods {
    String value();
}
