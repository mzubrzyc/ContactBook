package contacts;

public enum ContactType {

    PERSON("person"),
    ORGANIZATION("organization");

    String answer;

    ContactType(String answer) {
        this.answer = answer;
    }

    public static ContactType getContactType(String answer) {

        ContactType actionResult = null;

        for (ContactType type : values()) {
            if (answer.equals(type.answer)) {
                actionResult = type;
                break;
            }
        }

        return actionResult;

    }

}
