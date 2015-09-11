spring-configs-battle
=================================================

[SpringOne2GX 2015 edition](https://2015.event.springone2gx.com/schedule/sessions/spring_framework_the_ultimate_configurations_battle.html)

* cd into one of the configuration versions (groovy/java/xml)
* run with `./gradlew appRun`
* tests **replace the ${projectName} with groovy/xml/java accordingly**:
    * JsonParser with `curl -H "Content-Type: application/json" -d '{"username":"xyz","password":"xyz"}' http://localhost:8080/${projectName}/importData`
    * XmlParser with `curl -H "Content-Type: application/json" -d '<data><username>xyz</username><password>xyz</password></data>' http://localhost:8080/${projectName}/importData`
