# IV1350 VT25 – Objektorienterad design: POS‑system

En kursuppgift i IV1350 – Objektorienterad design vid KTH under våren 2025. Projektet består av att iterativt analysera, designa och implementera ett point-of-sale­­‑system (POS) i Java, med fokus på god design, designmönster och UML‑dokumentation. Utvecklingen sker i fyra seminarier som fokuserar på:

1. Analys – domänmodell och sekvensdiagram
2. Design – arkitektur, klassdiagram och ansvarsfördelning
3. Implementation – Java-programmering och testning
4. Undantagshantering och designmönster – förbättrad robusthet och användning av GoF-mönster

---

## Innehåll
1. [Struktur & moduler](#struktur--moduler)  
2. [Installation](#installation)  
3. [Körning](#körning)  
4. [Författare](#författare)

---

## Struktur & moduler
- `Sem1`
  - Domänmodel
  - Systemsekvensdiagram
  - Astahfilen med båda diagramen
- `Sem2`
  - Klassdiagram
  - Kommunikationsdiagram
- `Sem3/retailStore`
  - `src/`: Innehåller Java‑källkod för POS‑systemet.
    - Paket som `se.kth.iv1350.pos.model`, `...controller`, `...view`, etc.
  - `test/`: Enhetstester för domänlogik och controllers.
  - `docs/`: Javadocsfiler.
  - Makefile
  - Exempel utskrifter

---

## Installation
1. Klona repot:
   ```bash
   git clone https://github.com/KebabKent/IV1350-VT25-Nikodemus-Ohm--Simon-Moradbakti.git
   cd IV1350-VT25-Nikodemus-Ohm--Simon-Moradbakti/Sem3/retailStore

## Körning
1. Exekvera programmet:
   ```bash
   make run
   
2. Ta bort kompilerade filer
   ```bash
   make clean

3. Kompilera javadoc
   ```bash
   make run
   
5. Kör javadoc
   ```bash
   make viewDoc

## Författare
#### Nikodemus Pellerin Nilsson Ohm
#### Simon Mouradbakti

## Licens
Använd om du vill men kopiera inte rakt av.
