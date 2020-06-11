@echo off
rem echo [INFO] Start spring-boot security.
rem set MAVEN_OPTS=%MAVEN_OPTS% -Xms256m -Xmx768m -XX:PermSize=128M -XX:MaxNewSize=512m -XX:MaxPermSize=512m -Xdebug -Xrunjdwp:transport=dt_socket,server=y,suspend=n,address=9792
call mvn clean spring-boot:run
pause