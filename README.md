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

In the same terminal, you now can deploy with : 

`mvn glassfish:deploy`

to redeploy, just use : 

`mvn glassfish:redeploy`


## On your browser

You now can open your browser on : 

`http://localhost:8080/allo-doc/`