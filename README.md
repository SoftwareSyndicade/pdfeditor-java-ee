# PDF Editor

Pre-requisites
* JAVA 11
  * Make sure JAVA_HOME variable is added to env.
* MS SQL Server
  * While Installing SQL Server Make ser to check Mix Mode and type in password: <b>pdfeditorAppDB</b>
  * Make sure TPC/IP has been enabled in SQL Server Manager for port 1433
* Tomcat 10



## SQL Script
```SQL
create DATABASE PDF_UPLOADS_DB

CREATE TABLE RECENT_PDF
(
    ID int IDENTITY(1,1),
    FILE_NAME varchar(max),
    UPLOADED_ON DATETIME,
    CONSTRAINT PK_RECENT_PDF PRIMARY KEY (ID)
)
```

## Steps to install war file
* Make sure pre-requisites are installed
* Then copy the pdfeditor.war file and extract it into: <tomcat folder>/webapps folder.
* Extract .war file in webapps folder:
  * Open command prompt in the web apps folder
  * Then type the following command
    * jar -xvf <filename>.war
  * And hit enter
* Now navigate to bin folder in tomcat and run startup.bat for windows and startup.sh for linux or Mac.
* You can now access the application in your browser: http://localhost:8080/pdfeditor/
