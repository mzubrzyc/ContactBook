package contacts;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Contacts implements Serializable {

    private final List<Contact> contacts = new ArrayList<>();

    private static final Contacts contactsInst = new Contacts();

    private Contacts() { }

    public static Contacts getContactsInst() {
        return contactsInst;
    }

    public List<Contact> getContacts() {
        return new ArrayList<>(contacts);
    }

    public void setContacts(List<Contact> contacts) {

        this.contacts.addAll(contacts);

    }

    public boolean addContact(Contact contact) {

        if (contacts.contains(contact)) {
            return false;
        }

        contacts.add(contact);
        return true;

    }

    public int countContacts() {
        return contacts.size();
    }

    public void list() {

        printContacts(contacts);

    }

    public Contact edit(int selected) {

        if (selected < 0 || selected > countContacts()) {
            return null;
        } else {
            return contacts.get(selected - 1);
        }

    }

    public void remove(int selected) {
        Contact contact = edit(selected);
        if (contact != null) {
            contacts.remove(contact);
        }
    }

    public void remove(Contact contact) {
        if (contact != null) {
            contacts.remove(contact);
        }
    }

    public List<Contact> search(String query) {

        List<Contact> matchingContacts = contacts.stream()
                                                 .filter(contact -> contact.findQuery(query))
                                                 .collect(Collectors.toList());

        int size = matchingContacts.size();

        if (size == 0) {
            System.out.println("Found 0 results:");
        } else if (size == 1) {
            System.out.println("Found 1 result:");
            printContacts(matchingContacts);
        } else {
            System.out.printf("Found %d results:%n", size);
            printContacts(matchingContacts);
        }

        return matchingContacts;

    }

    public Contact getContact(int index) {

        if (index > contacts.size() || index < 0) {
            return null;
        } else {
            return contacts.get(index - 1);
        }

    }

    private void printContacts(List<Contact> contacts) {
        int i = 0;
        for (Contact contact : contacts) {
            System.out.printf("%d. %s\n", ++i, contact);
        }
        System.out.println();
    }

}
