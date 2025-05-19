# Rendszerterv

## 1. A rendszer célja

A projekt célja egy felhasználóbarát és intuitív Habit Tracker alkalmazás elkészítése Java platformon, amely segíti a felhasználókat napi és heti szokásaik követésében és kezelésében. Az alkalmazás vizuális visszajelzésekkel motiválja a szokások betartását, egyszerű kezelhetőséget és átlátható funkcionalitást biztosítva.

## 2 Főbb funkciók
   * Szokások létrehozása és törlése

   * Szokások napi/heti teljesítésének követése

   * Szokások megjelölése extra tulajdonságokkal (pl. fontos, jutalmazott)

   * Egyszerű statisztikai nézet, amely mutatja a szokások követési hatékonyságát
     
## 3. Technológiai háttér
   * Java nyelven íródó asztali alkalmazás JavaFX használatával

   * SQLite adatbázis használata adatok tárolására

   * Tervezési minták alkalmazása: Strategy (szokástípusok kezelése) és Decorator (szokások extra tulajdonságokkal való kiegészítése)
     
## 4. Architekturális vázlat

   * Felhasználói interfész: JavaFX

   * Alkalmazáslogika: Java backend

   * Adattárolás: SQLite adatbázis

## 5. Adatbázis terv

| Tábla | Oszlopok |
| Habits | id (PK), name, type (daily/weekly), isImportant, isRewarded, created_at |
| HabitRecords | id (PK), habit_id (FK), date, completed |


## 6. Használt tervezési minták

   * Strategy: napi és heti szokások eltérő viselkedésének kezelése

   * Decorator: szokások extra tulajdonságokkal történő kiegészítése (pl. fontos, jutalmazott szokások)

## 7. Fejlesztői eszközök
   * Java 17

   * JavaFX

   * SQLite

   * Git (verziókezeléshez)

## 8. Funkcionális követelmények

| Id | Funkció | Leírás |
| K1 | Szokás létrehozása | A felhasználó új szokást adhat meg névvel és típus (napi vagy heti) szerint |
| K2 | Szokás törlése | A felhasználó törölheti a már létrehozott szokásokat |
| K3 | Szokás teljesítése | A felhasználó kipipálhatja a napi vagy heti szokásait az aktuális dátumra vonatkozóan |
| K4 | Szokások extra tulajdonságokkal való kiegészítése | Szokásokhoz hozzárendelhető extra jelölések, például fontos vagy jutalmazott szokás |
| K5 | Statisztikák megtekintése | A felhasználó áttekintheti szokásainak teljesítési arányát egyszerű statisztikák segítségével |

## 9. Nemfunkcionális követelmények

   * Az alkalmazás gyors, megbízható és zökkenőmentes működést biztosít.

   * Egyszerű és átlátható felhasználói felület, amely intuitív és könnyen tanulható.

   * Az alkalmazásnak hatékonyan kell működnie alacsony hardverigény mellett is.

   * A felhasználók személyes adatai biztonságban vannak, helyileg, SQLite adatbázisban tárolódnak.

## 10. Támogatott eszközök

   * Ez az alkalmazás Java-alapú, így Windows, Linux és MacOS rendszereken is futtatható.

   * Internetkapcsolat nem szükséges, mivel offline módban működik.

## 11. Fejlesztő eszközök

-Git: Verziókezeléshez és csapatmunkához.

-Notepad++: Az ötletek rögzítéséhez és egyszerű jegyzetek készítéséhez.

-Trello: A projekt nyomon követéséhez és a feladatok kezeléséhez.

## 12. Implementációs terv

   Az alkalmazás megvalósítása a következő lépésekből áll:

   * Adatmodell megtervezése: SQLite adatbázis létrehozása, amely két táblát tartalmaz (Habits, HabitRecords).

   * Backend fejlesztés: Java backend osztályok létrehozása a szokások kezelésére, beleértve a szükséges tervezési minták (Strategy, Decorator) implementálását.

   * Frontend fejlesztés: JavaFX alapú felhasználói felület kialakítása, egyszerű navigáció és interakció biztosítása.

   * Integráció: Frontend és backend összekapcsolása, az adatbázis integrálása.

   * Tesztelés: Unit tesztek és egyszerű integrációs tesztek készítése és végrehajtása.


## 12. Tesztterv

A tesztelések célja a rendszer és komponensei funkcionalitásának teljes vizsgálata, valamint a rendszer által megvalósított üzleti szolgáltatások verifikálása. A teszteléseket a fejlesztői csapat minden tagja elvégzi, és az eredményeket külön fájlokban dokumentálják.

### Tesztesetek

Teszteset                   | Elvárt eredmény


Szokás létrehozása            | Új szokás sikeresen hozzáadódik a listához és az adatbázishoz.

Szokás kipipálása       | Az adott napra/heti ciklusra szóló teljesítés elmentődik

Szokás törlése   | A törölt szokás eltűnik a felületről és az adatbázisból

Extra tulajdonság (Decorator) teszt   | Az extra tulajdonságok helyesen jelennek meg a szokások mellett

Strategy minta tesztelése            |Napi és heti szokások eltérően viselkednek, helyesen működnekstatisztikái

Statisztika nézet          | Megfelelően jelennek meg a szokások teljesítési statisztikái


## 13. Telepítés
   * Java futtatókörnyezet telepítése szükséges

   * Az alkalmazás JAR fájlként futtatható

## 14. Karbantartási terv

Ez a projekt egyszeri beadandó feladatként készül, így hosszú távú karbantartást nem igényel. Az esetlegesen felmerülő hibák a projekt ideje alatt javításra kerülnek.
