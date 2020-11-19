package contacts;

import java.util.regex.Pattern;

public class Validation {

    private static final String regexpGender = "[mf]";

    private static final String regexPhone = "((\\+?\\([a-z0-9]+\\))?([-\\s][a-z0-9]+?)?(([-\\s][a-z0-9]{2,})+)?)" +
                                              "|(\\+?([a-z0-9]+)?([-\\s]\\([a-z0-9]{2,}\\))?(([-\\s][a-z0-9]{2,})+)?)" +
                                              "|(\\+?([a-z0-9][a-z0-9]{2,}))";

    final static Pattern phonePattern = Pattern.compile(regexPhone, Pattern.CASE_INSENSITIVE);

    final static Pattern genderPattern = Pattern.compile(regexpGender, Pattern.CASE_INSENSITIVE);

    public static boolean validateGender(String genderName) {
        return genderName == null || genderPattern.matcher(genderName).matches();
    }

    public static boolean validatePhone(String phoneNumber) {
        return phoneNumber == null || phonePattern.matcher(phoneNumber).matches();
    }

}
