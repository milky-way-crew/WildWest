Milky-way team.
===============

What are inside: `monopoly`, `chess`, `draw&guess` & `gibbet` games.
 
Deployment instructions
-------

* in terminal  `mysql -u {username} -p` -> default mysql settings are `root:root` on port=`3306` 
* or change mysql-user and db-port in `webapp/WEB-INF/spring/servlet-context.xml` if needed
* `create database worldgames;`
* update maven dependencies
* configure `${TOMCAT_HOME}/conf/tomcat-users.xml` (add manager\admin user) don't forget to add `manager-script` to roles
* configure `${MAVEN_HOME}/settings.xml` (in server section add user from previous step)
* `mvn tomcat:deploy`
* goto browser on `localhost:8080/WildWest`
* enjoy ;)

------
Project development finished. 

Contributors:
* stochansky yaroslav
* petryshyn inna
* romankevych pavlo
* kolbukh taras
* fedyna mykhailo