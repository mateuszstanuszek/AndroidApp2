USER KEY: HNXWTTF3DQLB
has³o: haslo123


1. W onCreate przypisanie id komponentów oraz dodanie listenera do przycisku, po którego klikniêciu nastêpuje utworzenie 
obiektu klasy WebService i wywo³anie na jego rzecz metody execute() w przypadku, gdy istnieje po³¹czenie z internetem.
2. Klasa WebService jest odpowiedzialna za po³¹czenie z us³ug¹.
3. Metoda getCredits() odpowiedzialna za wys³anie stopni USERKEY oraz has³a do metody CheckCredits z webservicu,
pobranie odpowiedzi i przypisanie do pola wynik. Je¿eli dane s¹ poprawne wyœwietlona iloœæ Credits, w przeciwnym wypadku
odpowiednie powiadomienie. Wykorzystany protokol https.
4. Wykorzystane biblioteki kSOAP 2.