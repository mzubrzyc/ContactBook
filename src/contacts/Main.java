package contacts;

public class Main {

    public static void main(String[] args) {

        String theFile = "myContacts";

        final FileHandler fileHandler = FileHandler.getFileHandler();
        fileHandler.setFilePath(theFile);

        Contacts contactsHandler = Contacts.getContactsInst();

        UserInterface userInterface = new UserInterface();

        userInterface.initActions(contactsHandler, fileHandler);

        userInterface.close();

    }
}