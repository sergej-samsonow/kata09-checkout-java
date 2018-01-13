# Kata09: Back to the Checkout - Java
About kata: http://codekata.com/kata/kata09-back-to-the-checkout/

[![Build state](https://travis-ci.org/sergej-samsonow/kata09-checkout-java.svg)](https://travis-ci.org/sergej-samsonow/kata09-checkout-java)

Die Schwierigkeit bei dem Entwurf von diesen System liegt erstens: Bei großen Varianz wie ein Preisangebot für ein Produkt oder Produktgruppe formuliert werden kann und zweitens in hohen Änderungsfrequenz von dieser Logik.

Folgende Architektur wir vorgeschlagen - das System wir in drei Bereiche aufgesplittet:
1. Checkout Komponente und das Regelsystem.
2. Eine Komponente das die einzelne Regeln enthält.
3. Regeln Verarbeitungskomponente.
Checkout Komponente wird mit Regelsystem initialisiert, das Regelsystem selbst wird mit eine Reihe von einzelnen Regeln und Regeln  Verarbeitungskomponente initialisiert. Regel  Verarbeitungskomponente wurde in eine Skriptsprache formuliert um Rekonfiguration einfach zu halten.

Das System wird unter Annahme folgende Verantwortlichkeiten entworfen.
1. Checkout Objekt wird vor Verwendung mit ein Regelsystem initialisirt.
2. Regelsystem wird vor Verwendung mit einzelnen Regeln und einer Verarbeitungskomponente initialisiert.
3. Produkt Kennungen können an den Checkout übergeben werden.
4. Total Preis für alle Produkt Kennungen wird unter Einbezug von Regeln berechnet.
5. Total Preis is 0 wenn keine Produkte übergeben wurden.

Folgende Verantwortlichkeiten liegen außerhalb von System und sollten von anderen Systemen sichergestellt werden.
1. Prüfung ob übergebene Produktkennung ist valid.
2. Sicherstellung das die Regeln selbst valid sind mit validen Daten initialisiert wurden.
3. Sicherstellung das übergebene Produktkennungen von hinterlegten verarbeitet werden können.
4. Festlegen maximaler Anzahl von von Produkten die von Checkout System verarbeitet werden können.
5. Festlegen von maximalen und minimalen Wert von Gesamtpreis.
6. Sicherstellen das die Verarbeitungskomponente valid ist und Regeln im Sinne dessen Schnittstelle verwendet.
7. Initialisierung von Regeln.
8. Initialisierung von Verarbeitungskomponente.
