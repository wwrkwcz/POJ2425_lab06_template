package com.pjatk.objects;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Objects;

public class Person {
    private int id;
    private String name;
    private LocalDate dateOfBirth;
    private ArrayList<Address> addresses;

    public Person(int id, String name, LocalDate dateOfBirth) {
        this.id = id;
        this.name = name;
        this.dateOfBirth = dateOfBirth;
        this.addresses = new ArrayList<>();
    }

    public Person copy() {
        Person copy = new Person(this.id, this.name, this.dateOfBirth);
        for (Address addr : this.addresses) {
            String[] parts = addr.toString().replace("(", "").replace(")", "").split(", ");
            int addrId = Integer.parseInt(parts[0]);
            String city = parts[1];
            String postalCode = parts[2];

            Address addrCopy = new Address(addrId, city, postalCode);
            addrCopy.getAddressLines().addAll(addr.getAddressLines());
            copy.getAddresses().add(addrCopy);
        }
        return copy;
    }

    public ArrayList<Address> getAddresses() {
        return addresses;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public LocalDate getDateOfBirth() { return dateOfBirth; }
    public void setDateOfBirth(LocalDate dateOfBirth) { this.dateOfBirth = dateOfBirth; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return id == person.id &&
                Objects.equals(name, person.name) &&
                Objects.equals(dateOfBirth, person.dateOfBirth) &&
                Objects.equals(addresses, person.addresses);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, dateOfBirth, addresses);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("(").append(id).append(", ").append(name).append(", ").append(dateOfBirth).append(")\n");
        sb.append("Adresy:\n");
        for (Address addr : addresses) {
            sb.append(addr.toString()).append("\n");
        }
        return sb.toString();
    }
}