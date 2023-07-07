@REM Compilation du framework
javac -d framework/src framework/src/*.java

@REM TRansformation du framework en jar
cd framework\src
jar -cf etu1836fw.jar.jar .

@REM Copie dans le lib du test projet
copy frameworkS4.jar "..\..\test_framework\src\WEB-INF\lib"

@REM compilation du projet de test
    cd ..\..\test_framework
    javac -d WEB-INF/classes WEB-INF/classes/*.java


@REM suppression du contenu du repertoire temp
    cd ../temp

    rmdir /S /Q "WEB-INF"

    del /F /Q "."

@REM structuration du repertoire temp
    mkdir WEB-INF

    cd ../

    robocopy test_framework\src\WEB-INF temp\WEB-INF /E

    copy "test_framework\src\*.jsp" "temp"

@REM transformation du repertoire temp en fichier war
    cd temp

    jar -cf etu1836framework.war .

copy etu1836framework.war "D:\info\L2\S3\Tom cat 8\apache-tomcat-8.5.82\webapps"
