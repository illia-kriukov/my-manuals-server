@echo on
call mvn clean install -P dev
call java -jar target/4DV611.mymanuals-1.0-SNAPSHOT.jar
pause