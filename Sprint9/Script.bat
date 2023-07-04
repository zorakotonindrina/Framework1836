javac -parameters -d . Framework/src/*.java

jar -cf Testframework/lib/etu1836fw.jar etu1836

jar -cf Testframework/WEB-INF/lib/etu1836fw.jar etu1836 


javac -cp  Testframework/WEB-INF/lib/etu1836fw.jar -parameters -d TestFramework/WEB-INF/classes TestFramework/WEB-INF/src/*.java

jar cvf etu1836fw.war -C  TestFramework .

copy etu1836fw.war  D:\L2\S3\Reseaux\apache-tomcat-8\webapps
                                                                                                                  a                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                           

