USER KEY: HNXWTTF3DQLB
has�o: haslo123


1. W onCreate przypisanie id komponent�w oraz dodanie listenera do przycisku, po kt�rego klikni�ciu nast�puje utworzenie 
obiektu klasy WebService i wywo�anie na jego rzecz metody execute() w przypadku, gdy istnieje po��czenie z internetem.
2. Klasa WebService jest odpowiedzialna za po��czenie z us�ug�.
3. Metoda getCredits() odpowiedzialna za wys�anie stopni USERKEY oraz has�a do metody CheckCredits z webservicu,
pobranie odpowiedzi i przypisanie do pola wynik. Je�eli dane s� poprawne wy�wietlona ilo�� Credits, w przeciwnym wypadku
odpowiednie powiadomienie. Wykorzystany protokol https.
4. Wykorzystane biblioteki kSOAP 2.