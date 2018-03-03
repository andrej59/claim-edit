# Редактор заявок

## Требование к ПО.

 1. JDK 1.8.x (http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html)
 2. Tomcat 8.5.x (https://tomcat.apache.org/download-80.cgi)
 3. IDEA 2017.x.x или Eclipse Mars.2 Release (4.5.x)
 4. Maven v3.3.x (http://archive.apache.org/dist/maven/maven-3/3.3.9/binaries/)

## Сборка приложения

```sh
  mvn clean install
```

## Инструкция разворачивания приложения в веб-сервере Apache Tomcat.

 1. Установить `Java JDK 1.8` в директорию `${java.home}`. 
 
 2. Скачать web-сервер `Tomcat 8.5.x`, и установить в директорию `${catalina.home}`  
    (далее `${java.home}` - домашняя директория Java JDK 1.8, `${catalina.home}` - это домашняя директория, в которую установлен веб-сервер `Tomcat`).

 3. Отредактировать файл `${catalina.home}/bin/setenv.bat` (если нет такого файла, создать),
    добавить строку `set JAVA_HOME=${java.home}`.
 
 4. Скопировать файл `/claim-web/dist/claim-web.war` в директорию `${catalina.home}/webapp`. 
     
 5. Для запуска веб-сервера `Tomcat`, в консоли выполнить скрипт `${catalina.home}/bin/startup.bat` (`startup.sh` для Linux).   
 
 6. Открыть веб-приложение в браузере по адресу `http://localhost:8080/claim-web/` или `http://localhost:8080/claim-web/index.html`.
 
 7. Веб-приложение не требует дополнительных настроек подключения к базе данных, т.к. использует встроеную `In-memory` базу данных  `H2`, 
    которая запускается автоматически при старте приложения.