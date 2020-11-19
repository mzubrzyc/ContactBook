package contacts;

public enum EditOrganizationActions {

    NAME("name"),
    ADDRESS("address"),
    NUMBER("number");

    String answer;

    private EditOrganizationActions(String answer) {
        this.answer = answer;
    }

    public static EditOrganizationActions getEditAction(String ans) {

        for (EditOrganizationActions action : values()) {
            if (action.answer.equals(ans)) {
                return action;
            }
        }

        return null;

    }

}
