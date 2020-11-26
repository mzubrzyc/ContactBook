package contacts;

import java.io.ObjectInputStream;
import java.lang.reflect.Field;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class ContactPerson extends Contact {

    private static final long serialVersionUID = 1L;

    private String name;
    private String surname;

    private LocalDate birthDate;

    private String gender;

    private final String dateTimePattern = "yyyy-MM-dd";

    boolean hasBirthDate;

    boolean hasGender;

    private transient final DateTimeFormatter formatter = DateTimeFormatter.ofPattern(dateTimePattern);

    @Override
    public void showInfo() {
        System.out.printf("Name: %s%n" +
                          "Surname: %s%n" +
                          "Birth date: %s%n" +
                          "Gender: %s%n" +
                          "Number: %s%n" +
                          "Time created: %s%n" +
                          "Time last edit: %s%n", name, surname,
                          hasBirthDate ? getBirthDate() : "[no data]",
                          hasGender ? getGender() : "[no data]", getPhone(),
                          getTimeCreated().format(getDtFormatter()),
                          getLastModified().format(getDtFormatter()));

    }

    @Override
    public boolean findQuery(String query) {

        return name.toLowerCase().contains(query) ||
               surname.toLowerCase().contains(query) ||
               getPhone().toLowerCase().contains(query);

    }

    @Override
    public void edit() {

        Scanner scanner = new Scanner(System.in);

        String answer;

        System.out.println("Select a field (name, surname, birth, gender, number): ");
        answer = scanner.nextLine().trim();
        EditPersonActions editAction = EditPersonActions.getEditAction(answer);

        if (editAction == null) {
            edit();
            return;
        }

        switch (editAction) {
            case NAME:
                System.out.print("Enter name: ");
                String newName = scanner.nextLine().trim();
                if (newName.isBlank()) {break;}
                setName(newName);
                break;
            case SURNAME:
                System.out.print("Enter the surname: ");
                setSurname(scanner.nextLine().trim());
                break;
            case BIRTH:
                System.out.print("Enter the birth date (yyyy-MM-dd): ");
                setBirthDate(scanner.nextLine().trim());
                break;
            case GENDER:
                System.out.print("Enter gender (M or F): ");
                setGender(scanner.nextLine().trim());
                break;
            case NUMBER:
                System.out.print("Enter number: ");
                setPhone(scanner.nextLine());
        }

        System.out.println("Saved\n");

        showInfo();

    }

    private ContactPerson(String name, String surname, String birthDay, String gender, String phone) {
        super(phone);
        this.name = name;
        this.surname = surname;
        setBirthDate(birthDay);
        setGender(gender);
    }

    private void readObject(ObjectInputStream ois) throws Exception {

        ois.defaultReadObject();

        Class<ContactPerson> c = ContactPerson.class;

        Field fieldName = c.getDeclaredField("formatter");
        fieldName.setAccessible(true);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(dateTimePattern);

        fieldName.set(this, formatter);

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        super.setLastModified();
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        super.setLastModified();
        this.surname = surname;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        if (birthDate.isEmpty()) {
            this.birthDate = null;
            this.hasBirthDate = false;
            System.out.println("Bad birth date!");
            return;
        }

        this.birthDate = LocalDate.parse(birthDate, formatter);
        this.hasBirthDate = true;
    }

    public String getGender() {
        return this.gender;
    }

    public void setGender(String gender) {

        boolean valid = Validation.validateGender(gender);

        if (!valid) {
            this.gender = null;
            this.hasGender = false;
            System.out.println("Bad gender!");
            return;
        }

        this.gender = gender;
        this.hasGender = true;
    }

    public String getDateTimePattern() {
        return dateTimePattern;
    }

    @Override
    public String toString() {
        return name + " " + surname;
    }

    public static class BuildContact {

        private final static Scanner scanner = new Scanner(System.in);

        public static Contact build() {

            System.out.println("Enter the name:");
            String name = scanner.nextLine().trim();

            System.out.println("Enter the surname:");
            String surname = scanner.nextLine().trim();

            System.out.println("Enter the birth date:");
            String birthDate = scanner.nextLine().trim();

            System.out.println("Enter the gender (M, F):");
            String gender = scanner.nextLine().trim();

            System.out.println("Enter the number:");
            String phoneNumber = scanner.nextLine().trim();

            return new ContactPerson(name, surname, birthDate, gender, phoneNumber);

        }

    }

}
