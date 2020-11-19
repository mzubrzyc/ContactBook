package contacts;

import java.util.Scanner;

public class ContactCompany extends Contact {

    private static final long serialVersionUID = 1L;

    private String name;

    private String address;

    @Override
    public void showInfo() {
        System.out.printf("Organization name: %s%n" +
                          "Address: %s%n" +
                          "Number: %s%n" +
                          "Time created: %s%n" +
                          "Time last edit: %s%n", name, getAddress(), getPhone(),
                          getTimeCreated().format(getDtFormatter()),
                          getLastModified().format(getDtFormatter()));

    }

    @Override
    public boolean findQuery(String query) {

        return name.toLowerCase().contains(query) ||
               address.toLowerCase().contains(query) ||
               getPhone().toLowerCase().contains(query);

    }

    @Override
    public void edit() {

        Scanner scanner = new Scanner(System.in);

        String answer;

        System.out.print("Select a field (name, address, number): ");
        answer = scanner.nextLine().trim();
        EditOrganizationActions editAction = EditOrganizationActions.getEditAction(answer);

        if (editAction == null) {
            edit();
            return;
        }

        switch (editAction) {
            case NAME:
                System.out.print("Enter name: ");
                setName(scanner.nextLine().trim());
                break;
            case ADDRESS:
                System.out.print("Enter address: ");
                setAddress(scanner.nextLine().trim());
                break;
            case NUMBER:
                System.out.print("Enter number: ");
                setPhone(scanner.nextLine());
        }

        System.out.println("Saved");

        showInfo();

    }

    public ContactCompany() {
        super();
    }

    public ContactCompany(String name, String phone, String address) {
        super(phone);
        this.name = name;
        this.address = address;
    }

    public String getName() {
        super.setLastModified();
        return name;
    }

    public void setName(String name) {
        super.setLastModified();
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return name;
    }

    public static class BuildContact {

        private final static Scanner scanner = new Scanner(System.in);

        public static Contact build() {

            System.out.println("Enter the name:");
            String name = scanner.nextLine().trim();

            System.out.println("Enter address:");
            String address = scanner.nextLine().trim();

            System.out.println("Enter the number:");
            String phoneNumber = scanner.nextLine().trim();

            return new ContactCompany(name, phoneNumber, address);

        }

    }
}
