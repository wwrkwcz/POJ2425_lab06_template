package com.pjatk.objects;

import java.util.ArrayList;
import java.util.Objects;

public class Address {
    private int id;
    private String city;
    private String postalCode;
    private ArrayList<String> addressLines;

    public Address(int id, String city, String postalCode) {
        this.id = id;
        this.city = city;
        this.postalCode = postalCode;
        this.addressLines = new ArrayList<>();
    }

    public ArrayList<String> getAddressLines() {
        return addressLines;
    }

    @Override
    public String toString() {
        return "(" + id + ", " + city + ", " + postalCode + ", " + addressLines + ")";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Address address = (Address) o;
        return id == address.id &&
                Objects.equals(city, address.city) &&
                Objects.equals(postalCode, address.postalCode) &&
                Objects.equals(addressLines, address.addressLines);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, city, postalCode, addressLines);
    }
}