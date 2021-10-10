### PaF - Gruppe F bastelt "TANKS" 
## 1. Projektbeteiligte
Line Kleemiß, Maximilian Rupprecht, Dirk Stricker

## 2. Projekt 
Wir haben uns für die Neuinterpretation des Arcade-Klassikers "Tanks" entschieden. 

## 2.1. Projektidee
Die grobe Idee ist, dass mindestens zwei Spieler in einer Arena sich mittels Panzer, die Kugelgeschosse abfeuern, 
sich bekämpfen. Dabei können die Spieler Deckung hinter Blöcken suchen. Diese Blöcke sind unterschiedlichen Materials
(Holz, Stein, Metall) und durch eine unterschiedliche Anzahl von Treffern zerstörbar.

Es sollen mindestens 2 Level verfügbar sein!
Es sollen mindestens 2 Benutzer als Dummy angelegt sein (Zwecks Prüfung)

## 2.2. Anforderungen an das Projekt
# Unabhängig von der Projektidee sollten folgende Anforderungen berücksichtig werden.
- Ein Anwender soll sich registrieren, einloggen und ausloggen können.
- Für jeden Anwender wird eine Historie seiner gespielten Spiele erfasst und mittels einfacher Auswertung dargestellt (gewonnene und verlorene  Spiele, mittlere Punktzahl, usw.).
- Es sollen an mind. Einer sinnvollen Stelle auch Bilder über die API angefragt und im Client dargestellt werden (z.B. Profilbilder der Anwender).

# 2.2.1 Server-Komponente (SC)
-	Die registrierten Anwender und ihre Spielhistorie sollen zentral in einer relationalen Datenbank gespeichert werden.
-	Es soll dazu ein Framework für das objekt-relationale Mapping eingesetzt werden
-	Es soll eine API für den Datenaustausch mit dem Client angeboten werden, über die Daten im JSON- oder XML-Format ausgetauscht werden. 
-	Die API soll JSON Web Tokens (JWT) zur Authentifizierung von Anwendern unterstützen.

# 2.2.2 Client-Komponente (CC)
-	Der Client soll ein grafisches UI umfassen und die obigen funktionalen Anforderungen abbilden.
-	Bei ≥ 3 Studierenden im Team sind 2 Clients in unterschiedlichen Frameworks zu erstellen, die sich eine serverseitige API teilen.
-	1. Client Nutzung JavaFX (oder alternativ Android Java API Framework).
-	2. Client Nutzung beliebiges anderes Framework inkl. Freier Auswahl der Programmiersprache.

# 2.5. UML Klassendiagramm als unbenotete PVL
-   UML-Klassendiagramm für Klassen des fachlichen Datenmodells.

## 3. Organisation
#  3.1. Sprache
-   Innerhalb des Codes, zb. Klassennamen, Methodenbezeichnungen, Dokumentation: englisch
-   Außerhalb wie z.B. im GitLab-Repository: deutsch
# 3.2. Kommunikation
-   Es finden regelmäßige Gruppeninterne Meetings statt (Sonntags, 11 Uhr).
-   Zwischenzeitlich soll die Signalgruppe für etwaige Klärung von Fragen oder Problemen sowie zur Absprache zusätzlicher Termine genutzt werden.
# 3.3. Vorgehen
-   Es wurde entschieden, soviel wie möglich im "Pair" oder "Mob" zu programmieren (Dabei "fährt" eine/einer während die anderen beiden kontrollieren und formulieren).
-   Einzelergebnisse werden in der Gruppe im regelmäßigen Meeting vorgestellt.
-   Es wird gemeinsam das Klassendiagramm erstellt.
-   Um ein gemeinsames Bild innerhalb der Gruppe vom Projekt zu bekommen werden gemeinsam Mockups der einzelnen Szenarien erstellt.  



