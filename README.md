# Introduction 
Template for spring boot project

# Getting Started
1.  Download project in github : https://github.com/josepharul82/template.git
2.  Install maven Apache Maven 3.6.3
3.  Install postgreSQL 12.3.1
4.  run : mvn clean install
5.  Install tomcat 9.0.35
    *   In the file "C:\Users\josep\Documents\work\outils\apache-tomcat-9.0.35\conf\context.xml"
        -  Add database configuration
            <Resource name="jdbc/postgres-temaplatedb" auth="Container"
                                          type="javax.sql.DataSource" driverClassName="org.postgresql.Driver"
                                          url="jdbc:postgresql://localhost:5432/postgres?currentSchema=templatedb"
                                          username="postgres" password="admin" maxTotal="20" maxIdle="10" maxWaitMillis="-1"/>
        -  Add Spring config file
            <Parameter name="spring.config.location" value="C:\Users\josep\Documents\work\configuration\template\application-template.yml"/>   



# Build and Test
TODO: Describe and show how to build your code and run the tests. 

# Contribute
TODO: Explain how other users and developers can contribute to make your code better. 
