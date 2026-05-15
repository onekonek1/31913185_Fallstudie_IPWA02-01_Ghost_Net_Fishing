<h1>GhostNet Fishing – Prototyp</h1>
Dieser Prototyp dient der Erfassung und Koordination von Geisternetzen im Rahmen der Fallstudie IPWA02-01.

<h2>Verwendete Technologien</h2>

- Java 17
- Spring Boot
- Maven
- MySQL
- Thymeleaf

<h2>Projektstart in IntelliJ IDEA</h2>

1. Import des Projekts über die Datei pom.xml.
2. Automatischer Download der benötigten Abhängigkeiten durch Maven.
3. Erstellung einer leeren Datenbank mit dem Namen ghostnet in MySQL.
4. Hinterlegung der lokalen Datenbank-Zugangsdaten in der Datei src/main/resources/application.properties.
5. Ausführung der Anwendung über die Hauptklasse GhostNetApplication.
6. Aufruf der Weboberfläche im Browser unter der Adresse http://localhost:8080.

<h2>Datenbank-Hinweise</h2>
Die Tabellenstruktur wird beim ersten Start der Anwendung automatisch durch Hibernate generiert. Eine manuelle Anlage von Tabellen über SQL-Skripte ist nicht erforderlich. Bei Bedarf kann die Datenbank gelöscht und neu angelegt werden um mit einem leeren Datenbestand zu beginnen.
