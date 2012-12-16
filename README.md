Milky-way team.
===============

What are inside: `monopoly`, `chess`, `draw&guess` & `gibbet` games.
 
Deployment instructions
-------

* in terminal  `mysql -u {username} -p` 
	> change sql-user and db-port in `webapp/WEB-INF/spring/servlet-context.xml` if needed
	> default setting are `root:root` on port=`3306`
* `create database worldgames;`
* update maven dependencies
* configure `${TOMCAT_HOME}/conf/tomcat-users.xml` (add manager\admin user)
	> don't forget to add `manager-script` to roles
* configure `${MAVEN_HOME}/settings.xml` (in server section add user from previous step)
* `mvn tomcat:deploy`
* goto browser on `localhost:8080/WildWest`
* enjoy ;)

------
Project development finished.