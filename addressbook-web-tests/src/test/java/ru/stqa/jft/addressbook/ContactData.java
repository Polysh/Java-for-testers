package ru.stqa.jft.addressbook;

public class ContactData {
    private final String firstName;
    private final String middleName;
    private final String lastName;
    private final String nik;
    private final String phone;
    private final String email;

    public ContactData(String firstName, String middleName, String lastName, String nik, String phone, String email) {
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.nik = nik;
        this.phone = phone;
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getNik() {
        return nik;
    }

    public String getPhone() {
        return phone;
    }

    public String getEmail() {
        return email;
    }
}
