package com.pjatk.objects;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;

public class Main {

    public static void main(String[] args) {

        /**
         * Zadanie 1:
         * Utwórz klasę 'Person' z konstruktorem, który przyjmuje i ustawia pola: id, name, dateOfBirth
         */
        Person jan = new Person(1, "jan", LocalDate.of(1990, 1, 1));

        /**
         * Zadanie 2:
         * Dodaj do klasy metodę 'copy()', która tworzy nowy obiekt Person z identycznymi danymi
         */
        Person jan_kopia = jan.copy();

        if (jan == jan_kopia) {
            System.out.println("Błąd: kopia nie powinna wskazywać na ten sam obiekt.");
            return;
        }
        if (!jan.getName().equals(jan_kopia.getName()) || jan.getId() != jan_kopia.getId()
                || !jan.getDateOfBirth().equals(jan_kopia.getDateOfBirth())) {
            System.out.println("Błąd: kopia nie zawiera tych samych danych.");
            return;
        }

        /**
         * Zadanie 3:
         * Nadpisz metodę 'equals' z klasy Object. Dwa obiekty Person są równe,
         * jeśli mają te same wartości we wszystkich polach.
         */
        if (!jan.equals(jan_kopia)) {
            System.out.println("Błąd: obiekty powinny być sobie równe (equals).\n" +
                    "Upewnij się, że metoda 'equals' porównuje wszystkie istotne pola.");
            return;
        }

        Person zdzislaw = jan.copy();
        zdzislaw.setName("zdzislaw");
        zdzislaw.setId(2);
        zdzislaw.setDateOfBirth(jan.getDateOfBirth().plusMonths(2));

        if (zdzislaw.equals(jan)) {
            System.out.println("Błąd: obiekty nie powinny być sobie równe, bo różnią się polami.");
            return;
        }

        /**
         * Zadanie 4:
         * Utwórz klasę 'Address' z konstruktorem przyjmującym: id, city, postalCode.
         * Dodaj pole 'addressLines' typu ArrayList<String> z getterem (bez settera).
         */
        Address adresJana = new Address(1, "Gdansk", "80-001");
        adresJana.getAddressLines().add("Brzegi 55");
        Address adresKopiiJana = new Address(1, "Gdynia", "80-002");
        adresKopiiJana.getAddressLines().add("Brzegi 55");

        /**
         * Zadanie 5:
         * Dodaj pole 'addresses' typu ArrayList<Address> do klasy Person i udostępnij je tylko przez getter.
         */
        jan.getAddresses().add(adresJana);
        jan_kopia.getAddresses().add(adresKopiiJana);

        /**
         * Zadanie 6:
         * Zaktualizuj metodę 'equals', aby porównywała również listę adresów.
         */
        if (jan.equals(jan_kopia)) {
            System.out.println("Błąd: jan i jego kopia mają różne adresy!\n" +
                    "Metoda 'equals' powinna porównywać również pola adresowe.");
            return;
        }

        /**
         * Zadanie 7:
         * Nadpisz metodę 'toString', aby obiekt Person drukował się w postaci:
         * (id, name, dateOfBirth)
         * Adresy:
         * (id, city, postalCode, {addressLine1, addressLine2, ...})
         */
        adresJana.getAddressLines().add("dziekanat szkoly");
        System.out.println("Wypisanie obiektu jan:\n" + jan);

        /**
         * Zadanie 8:
         * Utwórz HashMap<Person, ArrayList<Address>> do przechowywania adresów osób.
         * Nadpisz metodę 'hashCode' zgodnie z 'equals', aby uniknąć duplikatów kluczy.
         */
        HashMap<Person, ArrayList<Address>> addressesByPerson = new HashMap<>();
        addressesByPerson.put(jan, jan.getAddresses());
        addressesByPerson.put(jan_kopia, jan_kopia.getAddresses());
        addressesByPerson.put(zdzislaw, jan.getAddresses());

        Person drugaKopiaJana = jan.copy();
        addressesByPerson.put(drugaKopiaJana, drugaKopiaJana.getAddresses());

        if (addressesByPerson.keySet().size() > 3) {
            System.out.println("Błąd: druga kopia jana powinna zastąpić poprzednią wersję w HashMapie.\n" +
                    "Upewnij się, że hashCode() jest zgodny z equals().");
            return;
        }

        Person trzeciaKopiaJana = jan.copy();
        trzeciaKopiaJana.getAddresses().add(new Address(2, "Sopot", "80-003"));
        addressesByPerson.put(trzeciaKopiaJana, trzeciaKopiaJana.getAddresses());

        /**
         * Zadanie 9:
         * Dodanie innego adresu powinno zmieniać wynik metody 'hashCode',
         * więc obiekt powinien być traktowany jako inny i dodać się jako nowy wpis.
         */
        if (addressesByPerson.keySet().size() != 4) {
            System.out.println("Błąd: dodanie adresu do trzeciej kopii jana powinno zmienić wynik hashCode.");
            return;
        }

        System.out.println("Wszystko działa poprawnie!");
    }
}
