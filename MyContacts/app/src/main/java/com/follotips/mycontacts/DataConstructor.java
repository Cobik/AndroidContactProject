package com.follotips.mycontacts;

/**
 * Created by cobik99 on 09.11.17.
 */

public class DataConstructor {

    private int contactID;
    private String contactName;
    private String contactSurname;
    private String contactPhoneNumber;
    private String contactBirthday;


    public DataConstructor(int contactID,String contactName, String contactSurname, String contactPhoneNumber, String contactBirthday) {
        this.contactID = contactID;
        this.contactName = contactName;
        this.contactSurname = contactSurname;
        this.contactPhoneNumber = contactPhoneNumber;
        this.contactBirthday = contactBirthday;

    }

    //Getters and Setters


    public int getContactID() {
        return contactID;
    }

    public void setContactID(int contactID) {
        this.contactID = contactID;
    }

    public String getContactName() {
        return contactName;
    }

    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    public String getContactSurname() {
        return contactSurname;
    }

    public void setContactSurname(String contactSurname) {
        this.contactSurname = contactSurname;
    }

    public String getContactPhoneNumber() {
        return contactPhoneNumber;
    }

    public void setContactPhoneNumber(String contactPhoneNumber) {
        this.contactPhoneNumber = contactPhoneNumber;
    }

    public String getContactBirthday() {
        return contactBirthday;
    }

    public void setContactBirthday(String contactBirthday) {
        this.contactBirthday = contactBirthday;
    }
}







