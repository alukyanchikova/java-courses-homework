package ru.stqa.jchw.addressbook.model;

public class ContactData {
    private final String firstname;
    private final String lastname;
    private final String address;
    private final String mobilePhone;
    private final String email;

    public ContactData(String firstname, String lastname, String address, String mobilePhone, String email) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.address = address;
        this.mobilePhone = mobilePhone;
        this.email = email;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public String getAddress() {
        return address;
    }

    public String getMobilePhone() {
        return mobilePhone;
    }

    public String getEmail() {
        return email;
    }
}
