package contacts;

public enum MenuActions {
    ADD("add"),
    LIST("list"),
    SEARCH("search"),
    COUNT("count"),
    EXIT("exit");

    private final String actionName;

    MenuActions(String actionName) {
        this.actionName = actionName;
    }

    public static MenuActions getMenuAnswer(String answer) {

        MenuActions actionResult = null;

        for (MenuActions menuAction : values()) {

            if (answer.equals(menuAction.actionName)) {
                actionResult = menuAction;
                break;
            }
        }
        return actionResult;
    }

}
