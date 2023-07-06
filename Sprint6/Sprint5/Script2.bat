copy Testframework/lib/etu1836fw.jar Testframework/WEB-INF/classes/lib

javac -cp  Testframework/WEB-INF/lib/etu1836fw.jar -d TestFramework/src TestFramework/src/*.java

jar cvf etu1836fw.war -C  TestFramework .

copy etu1836fw.war  D:\L2\S3\Reseaux\apache-tomcat-8\webapps