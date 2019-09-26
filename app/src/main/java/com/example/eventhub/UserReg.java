package com.example.eventhub;

public class UserReg {

    private String CustomerID01;
    private String Email;
    private String ContactNum;
    private  String Password;

    public UserReg() {}

    public String getCustomerID01() {
        return CustomerID01;
    }

    public void setCustomerID(String customerID01) {
        CustomerID01 = customerID01;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getContactNum() {
        return ContactNum;
    }

    public void setContactNum(String contactNum) {
        ContactNum = contactNum;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }
}
