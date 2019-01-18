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

```java -jar message-0.0.1-SNAPSHOT.jar -Dspring.profiles.active=local```

Alternativ könnt ihr einfach die Klasse `Application` in IDEA ausführen. In der Run-Konfiguration bitte auch bei "Active profile" `local` eintragen.


### 1.4 Eigenen Branch anlegen
Ihr könnt euch mit folgenden Befehlen einen eigenen Branch anlegen:
```
git checkout -b <branch>
git push
```

Sobald der Branch gepushed ist baut Travis-CI den Branch und deployed den Services ins Cluster.
Travis-CI: https://travis-ci.org/senacor/code-and-play-message-ms/branches

Im Chat-Frontend sollte daraufhin ein Channel mit dem Namen des Branches auftauchen.
Frontend-Url: http://35.242.204.241/frontend/

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
Annotation an und implementiert anschließend euer Model für Chatnachrichten (`ChatMessage`).
Ihr benötiged die folgenden Properties: channelId, sender, message, creationTimestamp, id

### 3.2 Repositories

Da ihr nun eure Datenklassen implementiert habt, sollt ihr euch jetzt anschauen
wie ihr diese mit einer Datenbank verknüpfen könnt. Spring bietet hierfür
sogenannte Repositories, die letztlich eure Schnittstelle zur Datenbank darstellen.

Das Stichwort (bzw. die Annotation) ist hier **@Repository**. Schaut euch dazu folgenden
Artikel an:

 * https://docs.spring.io/spring-data/data-commons/docs/1.6.1.RELEASE/reference/html/repositories.html
 
Implementiert nun ein Repository für eure Chatnachrichten.

Ihr könnt den Code in `ChatMessageRepositoryIT` auskommentieren um eure Repository zu testen.

### 3.3 Services

Nun steht also die Veknüpfung zur Datenbank, sodass ihr nur noch auf sie zugreifen
müsst. Hierfür bietet es sich an einen Service zu implementieren, der die ganze 
Arbeit macht und eure Business-Logik enthält. Auch hier bietet Spring eine 
entsprechende Annotation: **@Service**

Den genauen Unterschied zu einem Repository bzw. einem Controller könnt ihr 
[hier nachlesen](https://www.baeldung.com/spring-component-repository-service).

Implementiert nun einen Service für Chatnachrichten, der Nachrichten lesen und
speichern kann.

Siehe auch `ChatMessageServiceTest`.

### 3.4 Controller

Zuguterletzt fehlt nun eine Schnittstelle, über auf den Service zugegriffen werden
kann. Die Annotationen von Interesse sind hier:

* @RestController
* @RequestMapping, @GetMapping, @PostMapping
* @PathVariable, @RequestBody, @RequestParam

Spring bietet schon eine [kleines Tutorial](https://spring.io/guides/gs/rest-service/) hierzu.

Implementiert nun den Controller mit einem GET und POST Endpoint,
der euren Service aufruft.
 
Verwende den Unit-Test `ChatMessageControllerTest` und den Integrations-Test `ChatMessageControllerIT`

### 3 (Bonus-Aufgabe1) Löschen von Nachrichten

Implementiere das Löschen von Nachrichten. Dazu muss die REST API erweitert werden, so das auch DELETE unterstützt wird.
Ein Request auf `DELETE /api/channels/{channelId}/messages/{messageId}` soll die Nachricht aus der Datenbank löschen.

### 3 (Bonus-Aufgabe 2) CI/CD Pipeline verstehen

Wie kommen deine Änderungen am Service auf unseren Cluster?
Schau dir die Datei [.travis.yml](./.travis.yml) an und versuche zu verstehen, wie das Build und das Deployment des Services funktioniert.

## 4 GKE, Kubernetes, Istio

### Login via gcloud SDK

...

### Zugriff auf das Kubernetes Cluster via kubectl

Mit diesem Befehl holt man sich die Credentials um via kubectl auf das Cluster zuzugreifen.
```
gcloud container clusters get-credentials cap --zone europe-west3-c --project code-and-play
```
 
 Anschließen kann man z.B. folgende Befehle ausführen:
```
# Auflisten der Pods (im Namespace default)
kubectl get pods

# Beispiel-Output:
NAME                                  READY     STATUS    RESTARTS   AGE
channel-ms-54bf759d8f-5dlkf           1/1       Running   0          1d
message-ms-6fb6ccc4cd-8rmgt           1/1       Running   0          3d

# Mehr infos zu einem Pod
kubectl describe pod channel-ms-54bf759d8f-5dlkf

# Logs eines Pods ansehen
kubectl logs channel-ms-54bf759d8f-5dlkf
```
 
## Monitoring

### Prometheus

- https://prometheus.io/docs/concepts/metric_types/

### Build-In Metriken

Rufe `http://localhost:8080/prometheus` auf um alle Metriken zu sehen die dein Service ausgibt.

### Eigene Metriken erfassen

Erfasse zwei Metriken:
- `message_ms_messages_saved_total` - Zählt wie oft die save-Methode des `ChatMessageServices` aufgerufen wird.
- `message_ms_messages_requests_total` - Zählt die API Request. Verwende ein tag um zwischen POST und GET Request zu unterscheiden.

Definiere die Metriken in [`Metrics`](./src/main/kotlin/com/senacor/cap/service/message/Metrics.kt).

Info: Wir verwenden [Micrometer](https://micrometer.io/docs/registry/prometheus).

### Zugriff auf die Prometheus UI

```
kubectl -n istio-system port-forward $(kubectl -n istio-system get pod -l app=prometheus -o jsonpath='{.items[0].metadata.name}') 9090:9090
```

[http://localhost:9090/dashboards](http://localhost:9090/dashboards)

### Grafana

- http://docs.grafana.org/guides/getting_started/

### Zugriff aus Grafana

```
kubectl -n istio-system port-forward $(kubectl -n istio-system get pod -l app=grafana -o jsonpath='{.items[0].metadata.name}') 3000:3000
```

[http://localhost:3000/dashboards](http://localhost:3000/dashboards)

### Eigenes Dashboard erstellen