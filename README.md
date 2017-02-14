# JEE - project 

## Authors:

- NDIAYE Pape Ballé
- RABEARIJAO Maminirina Thierry

## Title

Allo-Doc

## build

### Requirements

#### Maven

You can download maven here : [download](https://maven.apache.org/download.cgi)

#### Glassfish server

You can download glassfish server here : [download](https://glassfish.java.net/download.html)

## Package

To package the application just access the project directory with a terminal an use the command : 

`mvn clean package`

## Deploy 

To be able to deploy easily the application on you glassfish server, copy de content of file **conf/settings.xml** in your file
**$HOME/.m2/settings.xml**

> Note : You have to copy the lines from `<profiles>` tag to the closing tag `</activeProfiles>`

Those lines are : 

```xml
<profiles>
        <profile>
            <id>glassfish-context</id>
            <properties>
                <local.glassfish.home>D:\\glassfish4\\glassfish</local.glassfish.home>
                <local.glassfish.user>admin</local.glassfish.user>
                <local.glassfish.adminPassword>adminPassword</local.glassfish.adminPassword>
                <local.glassfish.domain>allo-doc</local.glassfish.domain>
                <local.glassfish.passfile>
            ${local.glassfish.home}\\domains\\${local.glassfish.domain}\\config\\domain-passwords
                </local.glassfish.passfile>
            </properties>
        </profile>
    </profiles>
 
    <activeProfiles>
        <activeProfile>glassfish-context</activeProfile>
    </activeProfiles>
```

If you have something to change on this file ( or those lines ), that would only be the content of the tag `<local.glassfish.home>`

### Deployment 

#### Glassfish ( obsolete )

In the same terminal, you now can deploy with : 

`mvn glassfish:deploy`

to redeploy, just use : 

`mvn glassfish:redeploy`

#### Tomcat ( recommended )


##### Prerequisite 

In order to be able to deploy on tomcat, you need to have this line in the file
**conf/tomcat_users.xml** of your tomcat directory : 

`<user username="allodoc" password="tomcat" roles="tomcat,manager-gui,admin-gui,manager-jmx,manager-script" />`

> This line specifies the allodoc user for your tomcat server, so this user can deploy on the server.

You also need to configure your **${HOME}/.m2/settings.xml** file, by adding : 


```xml
    <servers>
        <server>
            <id>Tomcat</id>
            <username>allodoc</username>
            <password>tomcat</password>
        </server>
    </servers>
    <pluginGroups>
        <pluginGroup>org.apache.tomcat.maven</pluginGroup>
    </pluginGroups>
```
###### Authentication requirements

The project uses tomcat container realm for authentication.
To make it possible, you have to configure your tomcat, by adding these lines in the file : 
`${TOMCAT_HOME}/conf/server.xml`:

```xml
<Realm className="org.apache.catalina.realm.JDBCRealm"
      driverName="org.postgresql.Driver"
      resourceName="tomcat-realm"
   connectionURL="jdbc:postgresql://localhost:5432/allodoc?currentSchema=allodoc"
   connectionName="allodoc"
   connectionPassword="allodocPassword"
       userTable="account" userNameCol="username" userCredCol="password"
   userRoleTable="account_role" roleNameCol="role_name"/>
```

**You need to download postgres driver** : [download link](https://jdbc.postgresql.org/download.html)

Then put it in the **/lib** folder of your tomcat installation.

**Now, you should be able to deploy**:

In the same terminal, you new can deploy with :

`mvn tomcat7:deploy`

to redeploy, just use : 

`mvn tomcat7:redeploy`



## On your browser

You now can open your browser on : 

`http://localhost:8080/allo-doc/`


## Database

The database used is [PostgreSQL](https://www.postgresql.org/).

You can download it here : [download link](http://www.enterprisedb.com/products-services-training/pgdownload#windows). The version **9.6** is recommended.

### Configuration 

After downloading and setting up your PostgreSQL database:

- create the user **allodoc** with the password **allodocPassword**.
    - `psql -U postgres`
    - postgres>`create user allodoc password 'allodocPassword'`
    - **OR** you can also use the`createuser` command
- create the database **allodoc** associated with the user **allodoc** and the schema **allodoc** in this database
    - `createdb -U allodoc allodoc`
    
**Note** : In order to easily Postgresql commands, i recommend you to add the **bin/** folder of your Postgres installation location in your $PATH.
