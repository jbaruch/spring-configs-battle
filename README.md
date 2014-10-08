spring-configs-battle
=================================================

[JavaDay Kiev 2014 edition](http://javaday.org.ua/)

* cd into one of the configuration versions (groovy/java/xml)
* run with `./gradlew appRun`
* test JsonParser with `curl -H "Content-Type: application/json" -d '{"username":"xyz","password":"xyz"}' http://localhost:8080/groovy/importData`
* test XmlParser with `curl -H "Content-Type: application/json" -d '<data><username>xyz</username><password>xyz</password></data>' http://localhost:8080/groovy/importData`
