javac -parameters -d . *.java
jar -cf test2.jar .
copy test2.jar "E:\FIANARANA\Logiciel\apache-tomcat-9.0.64-windows-x64\apache-tomcat-9.0.64\webapps\test\WEB-INF\lib"
@REM jar -cf testFrameworkx.war "../test"
@REM copy testFramework.war "D:\FIANARANA\Logiciel\apache-tomcat-9.0.64-windows-x64\apache-tomcat-9.0.64\webapps"
