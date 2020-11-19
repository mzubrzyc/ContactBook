package contacts;

import java.util.List;
import java.util.Scanner;

public class UserInterface {

    private final Scanner scanner;

    UserInterface() {

        this.scanner = new Scanner(System.in);

    }

    public void initActions(Contacts contactsHandler, FileHandler fileHandler) {

        try {

            List<Contact> contacts = fileHandler.readContacts();
            contactsHandler.setContacts(contacts);

        } catch (Exception e) {
            System.out.println("Error reading contacts from the file: " + e.getMessage());
            e.printStackTrace();
        }

        MenuActions menuAction = null;

        String answer;

        while (menuAction != MenuActions.EXIT) {

            System.out.print("[menu] Enter action (add, list, search, count, exit):");
            answer = scanner.nextLine().trim();
            menuAction = MenuActions.getMenuAnswer(answer);

            switch (menuAction) {
                case ADD:
                    addContact(contactsHandler, scanner);
                    break;
                case LIST:
                    listContacts(contactsHandler);
                    break;
                case SEARCH:
                    searchContacts(contactsHandler);
                    break;
                case COUNT:
                    int count = contactsHandler.countContacts();
                    System.out.printf("The Phone Book has %d records.\n\n", count);
                    break;
            }

        }

        try {
            fileHandler.saveContactsToFile(contactsHandler.getContacts());
        } catch (Exception e) {
            System.out.println("Error writing contacts to the file: " + e.getMessage());
        }

    }

    private void listContacts(Contacts contactsHandler) {

        String answer;

        contactsHandler.list();

        System.out.print("[list] Enter action ([number], back):");

        answer = scanner.nextLine().trim();

        try {

            int number = Integer.parseInt(answer);

            Contact contact = contactsHandler.getContact(number);

            if (contact == null) {
                listContacts(contactsHandler);
            } else {
                contact.showInfo();

                System.out.println();

                recordActions(contactsHandler, contact);
            }

        } catch (Exception e) {

            if (answer.isBlank() || !answer.equals("back")) {
                listContacts(contactsHandler);
            }

        }

        System.out.println();

    }

    private void recordActions(Contacts contactsHandler, Contact contact) {

        System.out.print("[record] Enter action (edit, delete, menu):");
        String answer = scanner.nextLine().trim();
        RecordActions recordAction = RecordActions.getRecordAction(answer);

        if (recordAction == null) {
            recordActions(contactsHandler, contact);
            return;
        }

        switch (recordAction) {
            case EDIT:
                contact.edit();
                System.out.println();
                recordActions(contactsHandler, contact);
                break;
            case DELETE:
                contactsHandler.remove(contact);
                System.out.println();
                recordActions(contactsHandler, contact);
                break;
            case MENU:
//                System.out.println();
                break;
        }

    }

    private void addContact(Contacts contactsHandler, Scanner scanner) {

        System.out.print("Enter the type (person, organization):");

        String answer = scanner.nextLine();

        ContactType contactType = ContactType.getContactType(answer);

        if (contactType == null) {
            System.out.println();
            addContact(contactsHandler, scanner);
            return;
        }

        switch (contactType) {

            case PERSON:

                Contact contactPerson = ContactPerson.BuildContact.build();

                if (contactsHandler.addContact(contactPerson)) {
                    System.out.print("The record added.");
                } else {
                    System.out.print("Error adding contact!");
                }
                System.out.println();

                break;

            case ORGANIZATION:

                Contact contactCompany = ContactCompany.BuildContact.build();

                if (contactsHandler.addContact(contactCompany)) {
                    System.out.print("The record added.");
                } else {
                    System.out.print("Error adding contact!");
                }
                System.out.println();

                break;

        }

        System.out.println();

    }

    private void searchContacts(Contacts contactsHandler) {

        System.out.print("Enter search query:");

        String query = scanner.nextLine().trim().toLowerCase();

        List<Contact> searchResult = contactsHandler.search(query.toLowerCase());

        searchActions(contactsHandler, searchResult);

    }

    private void searchActions(Contacts contactsHandler, List<Contact> searchResults) {

        System.out.print("[search] Enter action ([number], back, again):");
        String answer = scanner.nextLine();

        try {

            int number = Integer.parseInt(answer);
            if (number > searchResults.size() || number < 1) {
                throw new Exception("There is no such record. Number beyond limits.");
            }
            Contact contact = searchResults.get(number - 1);

            if (contact == null) {
                searchActions(contactsHandler, searchResults);
            } else {
                contact.showInfo();

                System.out.println();

                recordActions(contactsHandler, contact);
            }

        } catch (Exception e) {

            switch (answer.toLowerCase()) {
                case "back":
                    System.out.println();
                    break;
                case "again":
                    searchContacts(contactsHandler);
                    break;
                default:
                    searchActions(contactsHandler, searchResults);
                    break;
            }

        }

    }

    public void close() {
        this.scanner.close();
    }

}

