# APD-Project


### Introducere <a id="1"></a>
<br>Acest proiect a fost facut in cadrul cursului Algoritmi Paraleli si Distribuiti.
<br>Programul este un simplu joc inspirat din titlul destul de cunoscut "Vampire Survivors", un joc relativ mic dar distractiv.

### Explicatia Jocului in sine <a id="2"></a>
<br>Jocul nu are un scop final, dar in schimb se bazeaza pe un model tip Pac-Man in care cel mai important aspect este high-score-ul.
<br>Acest proiect ruleaza pe 2 fire de executie in acelasi timp (Jocul in sine se afla pe Thread-0 iar Render-ul se afla pe Thread-1) deoarece acesta era scopul proiectului de la bun inceput. Puteam sa modific felul in care este impartit pe fire de executie dar am considerat ca acestea doua sunt cele mai importante de separat.

### Tutorial <a id="3"></a>
<br>Jocul este bazat pe "cicluri", adica totul se intampla la un interval bine realizat de timp.
<br>pre exemplu:
    <br>- caracterul principal ataca o data la 3 secunde indiferent de inputurile jucatorului;
    <br>- inamicii apar la un interval bine stabilit de 5 secunde.
Inputurile sunt in felul urmator:
    <br>- W - pentru a misca caracterul in sus (pe directia -Y in java);
    <br>- A - pentru a misca caracterul in sus (pe directia -X in java);
    <br>- S - pentru a misca caracterul in sus (pe directia +Y in java);
    <br>- D - pentru a misca caracterul in sus (pe directia +X in java).
<br>Jucatorul poate combina aceste inputuri intre ele pentru a misca caracterul pe diagonale. In schimb, daca jucatorul apasa doua taste de pozitie opusa (W + S sau A + D) caracterul se va opri pur si simplu din miscat pe directiile apasate. Daca aceste inputuri "OPUSE" vin impreuna cu unul singur pe alta axa caracterul se va misca pe axa respectiva ignorand imputurile opuse.
<br>Caracterul este in viata atat timp cat bara de viata (bara din stanga sus) inca exista. In momentul cand aceasta ajunge la 0 jocul se termina si este prezentat "Death screen-ul" sau meniul in care caracterul inca se poate misca dar inamicii, scorul, bara de viata, nivelul si aparitia inamicilor sunt oprite. 
<br>Atacul caracterului se bazeaza pe 8 proiectile in fiecare directie cardinala. Cand acestea intra in contact cu inamicul, inamicul dispare. Fiecare proiectil are o penetrare de 2 inamici. In contact cu primul inamic, proiectilul incetineste iar in contact cu al doilea inamic, acesta dispare de pe harta.
<br>Cand proiectilul iese din harta vizibila, acesta este distrus pentru a preveni milioane de corpuri in acelasi timp.

### Scor si Level <a id="4"></a>
<br>Scorul creste pe un ciclu de o secunda insemnand ca pentru fiecare secunda in care caracterul este in viata acesta va creste cu 1.
<br>Nivelul (sau Level) creste pe un ciclu de 5 secunde iar la fiecare trecere de nivel vor aparea in functie de nivelul curent.
<br>EXPLICATIE: pentru nivelul 1 va aparea un singur inamic, pentru nivelul 2 vor aparea inca 2 inamici s.a.m.d.
<br>Nivelul creste chiar daca nu au fost omorati toti inamicii prezenti in nivel ceea ce ofera un nivel de dificultate deoarece exista posibilitatea sa fie atat de multi inamici incat player-ul nu mai are mutari in care sa nu isi scada viata.

### Instalare si Executare <a id="5"></a>
<br>Pentru a putea juca acest joc aveti nevoie de urmatoarele:
    <br>- Eclipse IDE; (https://www.eclipse.org/downloads/download.php?file=/oomph/epp/2022-03/R/eclipse-inst-jre-win64.exe)
    <br>- OpenJDK. (https://jdk.java.net/18/)
<br>Pasi pentru instalare:
   <br> - Descarcati arhiva cu jocul ;
   <br> - Deschideti Eclipse IDE;
   <br> - Importati proiectul (File->Open project from FileSystem...->Archive->APD-Project-main.zip);
   <br> - De aici puteti da in executie jocul sau puteti creea un .jar executabil.
<br>Pasi pentru creearea unui .jar (necesita pasii de mai sus):
    <br>- File->Export->Java->Runnable JAR file->Next;
    <br>- In Launch configuration selectati Game - APD-Project-main;
    <br>- In export destination selectati directorul unde doriti sa aveti Jocul.jar;
    <br>- Finish.

### Detalii Tehnice
<br>Jocul a fost creat folosind limbajul de programare Java. Cu ajutorul librariei Swing am reusit sa desenez pe ecran elementele necesare acestuia.
<br>IDE-urile folosite au fost Eclipse pentru creearea si buna functionare a proiectului si VisualStudio Code pentru usurarea scrierii codului datorita multelor functionalitati unice ale sale.
<br>Impartirea acestuia in doua Thread-uri:
    <br>- Ciclul jocului este pe Thread-ul sau (Toata logica din spatele lui, creearea de inamici, proiectile, jucator, "AI-ul" inamicilor, tinerea scorului, etc);
    <br>- Randarea tuturor elementelor pe ecran.


### Gameplay - YouTube
https://youtu.be/WHjCcjkyuWE

### Mentiuni
Nota 10? :3