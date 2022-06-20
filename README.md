> # [schifoarer77-INFI_Hue-Abgaben_Jungmann_3AHWII](https://github.com/schifoarer77/schifoarer77-schifoarer77-INFI_Hue-Abgaben_Jungmann_3AHWII)
>
> ### [Hue01_KundeArtikelBestellung](https://github.com/schifoarer77/schifoarer77-schifoarer77-INFI_Hue-Abgaben_Jungmann_3AHWII/tree/master/Gruppenprojekt_NovakJungmann_3AHWII/Projekt)
>
> ◼ **Gruppenprojekt_NovakJungmann_Kleinanzeigen**
>
>>
>> daten werden aus csv-dateien eingelesen
>
>> es können später weiter CSV-dateien eingelesen werden
>
>> manche datenzeilen könnten in mehrere CSV dateien vorkommen! vorkehrungen treffen!
>
>> daten aus den CSV-Files müssen in Tabellen gespeichert werden
>
>> es sind immer 2 themengebiete vorhanden (Schüler-Klassen) (Fraktoren-Felder) usw.
>
>> in einem Menü im Terminal bietet man mehrere menüpunkte an:
>
>> CSV datei einlesen, nachfragen im Terminal dann, 
	zur welche Tabelle die Daten hinzugefügt werden sollen 
	und Pfad und Dateinamen muss man auch angeben
>
>> Daten anzeigen aus DB-Tabelle, nächste Abfrage ist dann welche Tabelle
>
>> Daten verknüpfen ZB Schüler A geht in die 3AHWII, 
	Verknüpfungszeitpunkt auch abspeichern!
>
>> export der Verknüpfungstabelle als JSON in einer Datei, 
	nächste Frage, Pfad und Dateiname zu schreiben
>
>> Scanner benutzen fürs Terminal menü
>
> 🏁 **Fertigstellung 20.06.2022**
>
________________________________________________________________
> ### [Hue01_KundeArtikelBestellung](https://github.com/schifoarer77/schifoarer77-schifoarer77-INFI_Hue-Abgaben_Jungmann_3AHWII/tree/master/Hue01_KundeArtikelBestellung)
> 
> ◼ **Programmiere folgende Aufgabe mit JAVA+Sqlite**
>
>> Erzeuge eine Kundentabelle mit (id, name, email)
>
>> Erzeuge eine Artikeltabelle mit (id, bezeichnung, preis)
>> Autoincrement verwenden
>
>> Erzeuge alle notwendigen Methoden um die Tabellen anzulegen
und zu befüllen
>
>> Trenne Kunden- und Artikelbereiche in verschiedene Klassen
>
>> Erzeuge eine Bestelltabelle mit (kundenID, artikelID, anzahl)
(verwende Foreign Keys!!)
>
>> Erzeuge alle notwendigen Methoden um einen Artikel zu bestellen
>
>> Erzeuge eine Methode um die Bestellung von einem Kunden 
>
> 🏁 **Fertigstellung 13.12.2021**
>
________________________________________________________________
> 
> 
> ### [Hue02_AufMySQLUmschreiben](https://github.com/schifoarer77/schifoarer77-schifoarer77-INFI_Hue-Abgaben_Jungmann_3AHWII/tree/master/Hue02_AufMySQLUmgeschrieben)
> 
> ◼ **Aufgabe Kunde-Artikel erweitern.**
>
>> Als Methoden zu implementieren:
>
>> Bestellung soll durch Angabe der BestellID gelöscht werden können
>
>> Bestellung soll durch Angabe der BestellID und Anzahl der bestellten Artikel geupdatet werden.
>
>> Erweitern Sie die Artikeltabelle mit einer Lagerbestandspalte
>
>> Bei einer Bestellung, sollte der Lagerbestand geprüft werden. Ist dieser geringer, als die gewünschte Bestellmenge, wird die bestellung nicht durchgeführt.
>
>> Lagerbestand eines Artikels muss nach Bestellung angepasst werden können
>
> 🏁 **Fertigstellung 24.01.2022**
>
________________________________________________________________
> 
> 
> ### [Hue03_Testaufgabe_SchuelerKlassen_mysql](https://github.com/schifoarer77/schifoarer77-schifoarer77-INFI_Hue-Abgaben_Jungmann_3AHWII/tree/master/Hue03_Testaufgabe_SchuelerKlassen_mysql)
>
> ◼ **Eine Schule benötigt eine Schülerverwaltung.**
>
>> a) Entwerfen Sie die CREATE Statments für folgende Tabellen:
>> „alle“ Schüler
>> „alle“ Klassen
>>wählen Sie sinnvolle Spalten und Spaltentypen.
>
>> b) Entwerfen Sie das SQL-Statement für die Erzeugen der Schüler-zu-Klasse Tabelle. Diese Tabelle soll auch das Datum der Zuordnungszeitpunktes speichern. Vergessen Sie nicht Vorkehrungen gegen Dateninkonsistenzen einzubauen.
>
>> c) Schreiben Sie eine java-Methode, welcher (Connection, Klassenname, Schülername) als Parameter bekommt, und diesen Datensatz dann in die Tabelle speichert.
>
>> d) Rufen Sie diese Methode exemplarisch in der main auf
>
> 🏁 **Fertigstellung 07.03.2022**
>
________________________________________________________________
> 
> 
> ### [Hue04_csvDateiEinlesen](https://github.com/schifoarer77/schifoarer77-schifoarer77-INFI_Hue-Abgaben_Jungmann_3AHWII/tree/master/Hue04_csvDateiEinlesen)
> 
> ◼ **CSV Hausübung:**
>
>> Erzeuge eine CSV-Datei von Schülerinformationen
>
>> Überlege die passende TabellenStruktur zu der CSV-Datei
>
>> Erzeuge automatisch die Tabelle mittels JAVA
>
>> Lese mit SCANNER, zeileweise die CSV-Datei und schreib zeileweise in die neue Tabelle
>
>> Mach das Ganze nochmal für die Klassenräume
>
>> Selektiere die insertierten Schüler+Klassen
>
> 🏁 **Fertigstellung 28.03.2022**
>
________________________________________________________________
> 
> 
> ### [Hue05_csvEinlesen_SchuelerKlassen](https://github.com/schifoarer77/schifoarer77-schifoarer77-INFI_Hue-Abgaben_Jungmann_3AHWII/tree/master/Hue05_csvEinlesen_SchuelerKlassen)
>
> ◼ **Erzeuge eine CSV-Datei von Schülerinformationen**
>
>> Überlege die passende TabellenStruktur zu der CSV-Datei
>
>> Erzeuge automatisch die Tabelle mittels JAVA
>
>> Lese mit SCANNER, zeileweise die CSV-Datei und schreib zeileweise in die neue Tabelle
>
>> Mach das Ganze nochmal für die Klassenräume
>
>> Selektiere die insertierten Schüler+Klassen
>
> 🏁 **Fertigstellung 04.04.2022**
>
________________________________________________________________
> 
> 
> ### [Hue06_csvExportieren_SchuelerKlassen](https://github.com/schifoarer77/schifoarer77-schifoarer77-INFI_Hue-Abgaben_Jungmann_3AHWII/tree/master/Hue06_csvExportieren_SchuelerKlassen)
> 
> ◼ **Erzeuge beispielhaft einige Schüler+Klasse zuordnungen (Datum der Zuordnung soll auch gespeichert werden)**
>
>> Selektiere diese Zuordnungen und speichere die Selektion in eine CSV-Datei ab
>
> 🏁 **Fertigstellung 18.04.2022**
>
________________________________________________________________
> 
> 
> ### [Hue07_jsonEinlesen_SchuelerKlassen](https://github.com/schifoarer77/schifoarer77-schifoarer77-INFI_Hue-Abgaben_Jungmann_3AHWII/tree/master/Hue07_jsonEinlesen_SchuelerKlassen)
> 
> ◼ **Erzeuge eine JSON-Datei von Schülerinformationen**
>
>> Überlege die passende TabellenStruktur zu der JSON-Datei
>
>> Erzeuge automatisch die Tabelle mittels JAVA
>
>> Lese mit SCANNER, zeileweise die JSON-Datei und schreib zeileweise in die neue Tabelle
>
>> Mach das Ganze nochmal für die Klassenräume
>
>> Selektiere die insertierten Schüler+Klassen
>
> 🏁 **Fertigstellung 02.05.2022**
>
________________________________________________________________
> 
> 
> ### [Hue08_jsonExportieren_SchuelerKlassen](https://github.com/schifoarer77/schifoarer77-schifoarer77-INFI_Hue-Abgaben_Jungmann_3AHWII/tree/master/Hue08_jsonExportieren_SchuelerKlassen)
> 
> ◼ **Erzeuge beispielhaft einige Schüler+Klasse zuordnungen (Datum der Zuordnung soll auch gespeichert werden)**
>
>> Selektiere diese Zuordnungen und speichere die Selektion in eine JSON-Datei ab
>
> 🏁 **Fertigstellung 02.05.2022**
>
________________________________________________________________
>
> # [schifoarer77-INFI_Sue_Jungmann_3AHWII](https://github.com/schifoarer77/schifoarer77-schifoarer77-INFI_Hue-Abgaben_Jungmann_3AHWII)
>
> ### [Sue01_TestAufgabe2_AutoKundenVerleih_mysql](https://github.com/schifoarer77/schifoarer77-INFI_Hue-Abgaben_Jungmann_3AHWII/tree/master/Sue01_TestAufgabe2_AutoKundenVerleih_mysql)
> 
> ◼ **Ein Autohaus benötigt eine Verleihsverwaltung.**
>
>> a) Entwerfen Sie die CREATE Statments für folgende Tabellen:
>> „alle“ Kunden
>> „alle“ Autos
>> wählen Sie sinnvolle Spalten und Spaltentypen.
>
>> b) Entwerfen Sie das SQL-Statement für die Erzeugen der Kunden-zu-Auto Tabelle. Diese Tabelle soll auch das Datum der Zuordnungszeitpunktes speichern. Vergessen Sie nicht Vorkehrungen gegen Dateninkonsistenzen einzubauen.
>
>> c) Schreiben Sie eine java-Methode, welcher (Connection, KundenNr, Kennzeichen) als Parameter bekommt, und diesen Datensatz dann in die Tabelle speichert.
>
>> d) Rufen Sie diese Methode exemplarisch in der main auf
>
>
#### © schifoarer77
