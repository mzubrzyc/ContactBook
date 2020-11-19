package contacts;

public enum EditPersonActions {
    NAME("name"),
    SURNAME("surname"),
    BIRTH("birth"),
    GENDER("gender"),
    NUMBER("number");

    String answer;

    EditPersonActions(String answer) {
        this.answer = answer;
    }

    public static EditPersonActions getEditAction(String answer) {

        EditPersonActions actionResult = null;

        for (EditPersonActions action : values()) {
            if (answer.equals(action.answer)) {
                actionResult = action;
                break;
            }
        }

        return actionResult;
    }
}
