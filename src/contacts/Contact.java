package contacts;

import contacts.Validation;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public abstract class Contact implements Serializable {

    private static final long serialVersionUID = 1L;

    private String phone;

    private final LocalDateTime timeCreated = LocalDateTime.now();
    private LocalDateTime lastModified;

    private static final DateTimeFormatter dtFormatter =
            DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");

    private boolean hasNumber;

    abstract public void showInfo();

    abstract public boolean findQuery(String query);

    abstract public void edit();

    Contact() {

    }

    Contact(String phone) {
        this.lastModified = LocalDateTime.now();
        setPhone(phone);
    }

    void setLastModified() {
        lastModified = LocalDateTime.now();
    }

    public String getPhone() {
        return this.phone.isEmpty() ? "[no number]" : this.phone;
    }

    public void setPhone(String phone) {
        if (Validation.validatePhone(phone)) {

            setLastModified();

            this.phone = phone;
            setHasNumber(true);

            return;
        }

        System.out.println("Wrong number format!");
        this.phone = "";
        setHasNumber(false);
    }

    public boolean isHasNumber() {
        return hasNumber;
    }

    private void setHasNumber(boolean hasNumber) {
        this.hasNumber = hasNumber;
    }

    public LocalDateTime getTimeCreated() {
        return timeCreated;
    }

    public LocalDateTime getLastModified() {
        return lastModified;
    }

    private void setLastModified(LocalDateTime dateTime) {
        this.lastModified = dateTime;
    }

    public static DateTimeFormatter getDtFormatter() {
        return dtFormatter;
    }



}
