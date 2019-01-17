# code-and-play-message-ms

This microservice provides the REST endpoint `/api/channels/{channelId}/messages` to load and save message.


##  Setup

### 1.1 Benötigte Tools

Folgende Tools solltet ihr idealerweise installiert haben:

- JDK 8 - https://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html
- Git - e.g. https://git-scm.com/download
- IDE für Kotlin - e.g. https://www.jetbrains.com/idea/download (Community reicht aus)
- Google Cloud SDK - https://cloud.google.com/sdk/install
- kubectl - kann via Google Cloud SDK installiert werden:
         - gcloud components install kubectl
         - see also: https://cloud.google.com/sdk/docs/managing-components
 
Optional könnt ihr auch Docker installieren, wird aber nur für Zusatzaufgaben gebraucht:

- Docker - https://www.docker.com/products/docker-desktop

Und bitte stellt sicher dass ihr ein GitHub und ein Google Account habt:
- GitHub: https://github.com
- Google: https://accounts.google.com

### 1.2 Repository clonen und branch anlegen

Als nächstes cloned euch das Repository mittels:
```
git clone https://github.com/senacor/code-and-play-message-ms.git
```

Oder über eure IDE (IntelliJ) mittels:
```New->Project from Version Control->Git```
und fügt die URL des Repositories ein.

### 1.3 Build / Run
IntelliJ sollte das Projekt automatisch als Maven Projekt erkennen und auch schon eine
Run / Build Config anlegen. Ansonsten könnt ihr den Source Code mit folgendem Befehl
bauen: ```mvn clean install```

Ihr findet anschließend im **target** Ordner eine JAR die ihr folgendermaßen ausführen
könnt:

```java -jar message-0.0.1-SNAPSHOT.jar```

Alternativ könnt ihr den Service auch ohne vorher zu builden ausführen:
```mvn spring-boot:run```

TODO: `Application` mit Spring Profile `local` starten.

### 1.4 Eigenen Branch anlegen
Ihr könnt euch mit folgenden Befehlen einen eigenen Branch anlegen:
```
git checkout -b <branch>
git push
```


## 2 Kotlin Basics

Kotling kann, wie Java, ebenfalls auf der Java Virtual Machine (JVM) ausgeführt werden.
Das ist ganz cool, weil Kotlin auch Java Code wiederverwenden kann und man nicht dazu
gezwungen ist, Vorhandenes neu zu schreiben.

Kotlin braucht **keine** Semikolons! Natürlich könnt ihr welche setzen, wenn es euch
besser gefällt. Ihr könnt euch die Syntax auf der [Kotlin Seite anschauen](https://kotlinlang.org/docs/reference/basic-syntax.html)


## 3 Implementierung

In den nächsten Schritten sollt ihr nun den Service implementieren. Oftmals wird 
bei Controllern angefangen (top-down), wir wollen jedoch mit dem Data Access Layer 
beginnen, also bottom-up.

### 3.1 Models

Spring bietet die Möglichkeit seine Datenklassen (auch gern Models genannt) 
direkt als Entitäten zu kennzeichnen. Schaut euch hierzu die **@Entity** 
Annotation an und implementiert anschließend euer Model für Chatnachrichten (Messages).

### 3.2 Repositories

Da ihr nun eure Datenklassen implementiert habt, sollt ihr euch jetzt anschauen
wie ihr diese mit einer Datenbank verknüpfen könnt. Spring bietet hierfür
sogenannte Repositories, die letztlich eure Schnittstelle zur Datenbank darstellen.

Das Stichwort (bzw. die Annotation) ist hier **@Repository**. Schaut euch dazu folgenden
Artikel an:

 * https://docs.spring.io/spring-data/data-commons/docs/1.6.1.RELEASE/reference/html/repositories.html
 
Implementiert nun ein Repository für eure Chatnachrichten.

### 3.3 Services

Nun steht also die Veknüpfung zur Datenbank, sodass ihr nur noch auf sie zugreifen
müsst. Hierfür bietet es sich an einen Service zu implementieren, der die ganze 
Arbeit macht und eure Business-Logik enthält. Auch hier bietet Spring eine 
entsprechende Annotation: **@Service**

Den genauen Unterschied zu einem Repository bzw. einem Controller könnt ihr 
[hier nachlesen](https://www.baeldung.com/spring-component-repository-service).

Implementiert nun einen Service für Chatnachrichten, der Nachrichten lesen und
speichern kann.



### 3.4 Controller

Zuguterletzt fehlt nun eine Schnittstelle, über auf den Service zugegriffen werden
kann. Die Annotationen von Interesse sind hier:

* @RestController
* @RequestMapping, @GetMapping, @PostMapping
* @PathVariable, @RequestBody, @RequestParam

Spring bietet schon eine [kleines Tutorial](https://spring.io/guides/gs/rest-service/) hierzu.

Implementiert nun den Controller mit einem GET und POST Endpoint,
der euren Service aufruft.
 

## 4 GKE, Kubernetes, Istio

 
## Bonus
