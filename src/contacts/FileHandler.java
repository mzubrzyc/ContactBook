package contacts;

import java.io.*;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class FileHandler {

    private File filePath;

    private final static FileHandler fileHandler = new FileHandler();

    private FileHandler() {
        this.filePath = Path.of("default.txt").toFile();
    }

    public void saveContactsToFile(List<Contact> contacts) throws Exception {

        try (ObjectOutputStream oos = new ObjectOutputStream(new BufferedOutputStream(
                new FileOutputStream(filePath)))) {

            for (Contact contact : contacts) {
                oos.writeObject(contact);
            }

            // to indicate there are no more objects to be read, it ends the reading loop
            oos.writeObject(null);

        }

    }

    public List<Contact> readContacts() throws Exception {

// used to create file for the first time
//        filePath.createNewFile();

        Contact contact;
        List<Contact> contacts = new ArrayList<>();

        try (ObjectInputStream ois = new ObjectInputStream(new BufferedInputStream(new FileInputStream(filePath)))) {

            while ((contact = (Contact) ois.readObject()) != null) {
                contacts.add(contact);
            }

        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }

        return contacts;

    }

    public static FileHandler getFileHandler() {
        return fileHandler;
    }

    public File getFilePath() {
        return filePath;
    }

    public void setFilePath(File filePath) {
        this.filePath = filePath;
    }

    public void setFilePath(String file) {
        this.filePath = Path.of(file).toFile();
    }

}
