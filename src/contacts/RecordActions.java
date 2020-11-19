package contacts;

public enum RecordActions {
    EDIT("edit"),
    DELETE("delete"),
    MENU("menu");

    String actionName;

    RecordActions(String actionName) {
        this.actionName = actionName;
    }

    public static RecordActions getRecordAction(String answer) {

        RecordActions actionResult = null;

        for (RecordActions action : values()) {

            if (answer.equals(action.actionName)) {
                actionResult = action;
                break;
            }
        }

        return actionResult;

    }

}
